package com.griddynamics.ngolovin.mosb.catalog.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATALOG_ITEM")
@Data
public class CatalogItem {

    @Id
    @Column(name = "uniq_id")
    private String uniqId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name_title")
    private String nameTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "list_price")
    private String listPrice;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "category")
    private String category;

    @Column(name = "category_tree")
    private String categoryTree;

    @Column(name = "average_rating")
    private String averageRating;

    @Column(name = "url")
    private String url;

    @Column(name = "image_urls")
    private String imageUrls;

    @Column(name = "brand")
    private String brand;

    @Column(name = "total_number_reviews")
    private String totalNumberReviews;

    @Column(name = "reviews")
    private String reviews;
}
