package com.harshet.productcatalog.thirdpartyclients.productsservice.fakeStore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductData {
    private Long id;
    private String title;
    private Double price;
    private String image;
    private String category;
    private String description;
}
