package com.griddynamics.ngolovin.mosb.inventory.services;

import com.griddynamics.ngolovin.mosb.inventory.entities.InventoryItem;
import com.griddynamics.ngolovin.mosb.inventory.repositories.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    public List<InventoryItem> findInventoryItemsByUniqIds(List<String> ids) {
        return inventoryItemRepository.findByUniqIdIn(ids);
    }
}
