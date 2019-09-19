package com.griddynamics.ngolovin.mosb.catalog.repositories;

import com.griddynamics.ngolovin.mosb.catalog.entities.CatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, String> {

    Optional<CatalogItem> findByUniqId(String uniqId);

    List<CatalogItem> findBySku(String sku);
}
