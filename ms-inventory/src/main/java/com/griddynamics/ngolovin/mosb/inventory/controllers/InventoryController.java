package com.griddynamics.ngolovin.mosb.inventory.controllers;

import com.griddynamics.ngolovin.mosb.inventory.entities.InventoryItem;
import com.griddynamics.ngolovin.mosb.inventory.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/api/inventories")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private static final AtomicLong REQUESTS_COUNT = new AtomicLong(0);
    private static final long NETWORK_DELAY_SECONDS = 5;

    private final InventoryService inventoryService;

    @PostMapping
    public List<InventoryItem> inventoryItemsByUniqIds(@RequestBody List<String> uniqIds) {
        log.info("POST inventoryItemsByUniqId uniqIds={}", uniqIds);
        imitateNetworkDelay();
        return inventoryService.findInventoryItemsByUniqIds(uniqIds);
    }

    private void imitateNetworkDelay() {
        if (REQUESTS_COUNT.incrementAndGet() % 5 == 0) {
            try {
                log.info("Imitating {}s network delay", NETWORK_DELAY_SECONDS);
                TimeUnit.SECONDS.sleep(NETWORK_DELAY_SECONDS);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
