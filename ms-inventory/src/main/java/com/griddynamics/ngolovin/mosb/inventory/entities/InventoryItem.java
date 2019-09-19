package com.griddynamics.ngolovin.mosb.inventory.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY_ITEM")
@Data
public class InventoryItem {

    @Id
    @Column(name = "uniq_id")
    private String uniqId;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
}
