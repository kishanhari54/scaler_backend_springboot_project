package com.harshet.productcatalog.models;

import jakarta.persistence.Entity;

@Entity
public class Category extends BaseModel {
    private String name;
}