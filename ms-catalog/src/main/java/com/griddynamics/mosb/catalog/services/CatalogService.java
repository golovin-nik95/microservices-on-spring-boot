package com.griddynamics.mosb.catalog.services;

import com.griddynamics.mosb.catalog.entities.CatalogItem;
import com.griddynamics.mosb.catalog.repositories.CatalogItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogItemRepository catalogItemRepository;

    public Optional<CatalogItem> findCatalogItemByUniqId(String uniqId) {
        return catalogItemRepository.findByUniqId(uniqId);
    }

    public List<CatalogItem> findCatalogItemBySku(String sku) {
        return catalogItemRepository.findBySku(sku);
    }
}
