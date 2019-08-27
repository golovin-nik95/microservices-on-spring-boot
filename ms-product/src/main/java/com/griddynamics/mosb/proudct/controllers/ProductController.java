package com.griddynamics.mosb.proudct.controllers;

import com.griddynamics.mosb.proudct.exceptions.ProductNotFoundException;
import com.griddynamics.mosb.proudct.models.Product;
import com.griddynamics.mosb.proudct.services.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{uniqId}")
    @HystrixCommand(commandKey = "product-by-uniq-id", fallbackMethod = "productByUniqIdFallback")
    public ResponseEntity<Product> productByUniqId(@PathVariable String uniqId) {
        log.info("GET productsByUniqId uniqId={}", uniqId);
        return productService.findProductByUniqId(uniqId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product with uniqId [" + uniqId + "] doesn't exist"));
    }

    @GetMapping
    @HystrixCommand(commandKey = "products-by-sku", fallbackMethod = "productsBySkuFallback")
    public ResponseEntity<List<Product>> productsBySku(@RequestParam String sku) {
        log.info("GET productsBySku sku={}", sku);
        return ResponseEntity.ok(productService.findProductsBySku(sku));
    }

    @SuppressWarnings("unused")
    private ResponseEntity<Product> productByUniqIdFallback(String uniqId, Throwable throwable) {
        log.error("productByUniqIdFallback uniqId={}", uniqId, throwable);
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @SuppressWarnings("unused")
    private ResponseEntity<List<Product>> productsBySkuFallback(String sku, Throwable throwable) {
        log.error("productsBySkuFallback sku={}", sku, throwable);
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
