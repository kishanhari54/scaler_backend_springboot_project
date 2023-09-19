package com.harshet.productcatalog.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category extends BaseModel {
    private String name;
    @OneToMany(mappedBy = "category") // We use Mapped By to represent they have same relation and not a new relation
    private List<Product> product;
}