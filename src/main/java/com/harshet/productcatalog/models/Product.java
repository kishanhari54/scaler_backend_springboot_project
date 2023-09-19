package com.harshet.productcatalog.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    // 1: 1 --> Left to Right...
    // M: 1 --> Right to Left...
    // M:1 --> Answer
    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "category")
    private Category category;
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private Price price;
}