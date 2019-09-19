package com.griddynamics.ngolovin.mosb.inventory.repositories;

import com.griddynamics.ngolovin.mosb.inventory.entities.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {

    List<InventoryItem> findByUniqIdIn(List<String> uniqIds);
}