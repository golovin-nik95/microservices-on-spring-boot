package com.griddynamics.mosb.proudct.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

    private String uniqId;
    private Long quantity;
}
