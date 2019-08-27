package com.griddynamics.mosb.proudct.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String uniqId;
    private String sku;
    private String nameTitle;
    private String description;
    private String listPrice;
    private String salePrice;
    private String category;
    private String categoryTree;
    private String averageRating;
    private String url;
    private String imageUrls;
    private String brand;
    private String totalNumberReviews;
    private String reviews;
    private Long quantity;
}
