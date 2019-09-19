package com.griddynamics.ngolovin.mosb.proudct.services;

import com.griddynamics.ngolovin.mosb.proudct.models.Product;
import com.griddynamics.ngolovin.mosb.proudct.models.ProductInventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private static final String CATALOG_SERVICE_API_URL = "http://catalog-service/v1/api/catalogs";
    private static final String INVENTORY_SERVICE_API_URL = "http://inventory-service/v1/api/inventories";

    private final RestTemplate restTemplate;

    public Optional<Product> findProductByUniqId(String uniqId) {
        ResponseEntity<Product> catalogItemResponse = restTemplate.getForEntity(
                CATALOG_SERVICE_API_URL + "/{uniqId}", Product.class, uniqId);

        Product catalogItem = catalogItemResponse.getBody();
        if (catalogItemResponse.getStatusCode() == HttpStatus.OK && catalogItem != null) {
            List<Product> productsInStock = getProductsInStock(Collections.singletonList(catalogItem));
            return productsInStock.stream().findFirst();
        } else {
            log.error("Unable to get product for uniqId: {}, statusCode: {}",
                    uniqId, catalogItemResponse.getStatusCode());
            return Optional.empty();
        }
    }

    public List<Product> findProductsBySku(String sku) {
        ParameterizedTypeReference<List<Product>> typeReference = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Product>> catalogItemsResponse = restTemplate.exchange(
                CATALOG_SERVICE_API_URL + "?sku={sku}", HttpMethod.GET, null, typeReference, sku);

        List<Product> catalogItems = catalogItemsResponse.getBody();
        if (catalogItemsResponse.getStatusCode() == HttpStatus.OK && !CollectionUtils.isEmpty(catalogItems)) {
            return getProductsInStock(catalogItems);
        } else {
            log.error("Unable to get product for sku: {}, statusCode: {}",
                    sku, catalogItemsResponse.getStatusCode());
            return Collections.emptyList();
        }
    }

    private List<Product> getProductsInStock(List<Product> catalogItems) {
        Map<String, Product> productMap = catalogItems.stream()
                .collect(Collectors.toMap(Product::getUniqId, Function.identity()));

        ParameterizedTypeReference<List<ProductInventory>> typeReference = new ParameterizedTypeReference<>() {
        };
        Set<String> uniqIds = productMap.keySet();
        ResponseEntity<List<ProductInventory>> inventoryItemsResponse = restTemplate.exchange(
                INVENTORY_SERVICE_API_URL, HttpMethod.POST, new HttpEntity<>(uniqIds), typeReference);

        List<ProductInventory> productInventories = inventoryItemsResponse.getBody();
        if (inventoryItemsResponse.getStatusCode() == HttpStatus.OK && productInventories != null) {
            productInventories.forEach(productInventory -> {
                if (productInventory.getQuantity() > 0) {
                    Product product = productMap.get(productInventory.getUniqId());
                    product.setQuantity(productInventory.getQuantity());
                } else {
                    productMap.remove(productInventory.getUniqId());
                }
            });
        } else {
            log.error("Unable to get inventory level for uniqIds: {}, statusCode: {}",
                    uniqIds, inventoryItemsResponse.getStatusCode());
        }

        return new ArrayList<>(productMap.values());
    }
}
