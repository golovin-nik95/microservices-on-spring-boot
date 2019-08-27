package com.griddynamics.mosb.catalog.controllers;

import com.griddynamics.mosb.catalog.entities.CatalogItem;
import com.griddynamics.mosb.catalog.exceptions.CatalogItemNotFoundException;
import com.griddynamics.mosb.catalog.services.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/api/catalogs")
@RequiredArgsConstructor
@Slf4j
public class CatalogController {

    private static final AtomicLong REQUESTS_COUNT = new AtomicLong(0);
    private static final long NETWORK_DELAY_SECONDS = 3;

    private final CatalogService catalogService;

    @GetMapping("/{uniqId}")
    public CatalogItem catalogItemByUniqId(@PathVariable String uniqId) {
        log.info("GET catalogItemByUniqId uniqId={}", uniqId);
        imitateNetworkDelay();
        return catalogService.findCatalogItemByUniqId(uniqId)
                .orElseThrow(() -> new CatalogItemNotFoundException(
                        "CatalogItem with uniqId [" + uniqId + "] doesn't exist"));
    }

    @GetMapping
    public List<CatalogItem> catalogItemsBySku(@RequestParam String sku) {
        log.info("GET catalogItemsBySku sku={}", sku);
        imitateNetworkDelay();
        return catalogService.findCatalogItemBySku(sku);
    }

    private void imitateNetworkDelay() {
        if (REQUESTS_COUNT.incrementAndGet() % 3 == 0) {
            try {
                log.info("Imitating {}s network delay", NETWORK_DELAY_SECONDS);
                TimeUnit.SECONDS.sleep(NETWORK_DELAY_SECONDS);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
