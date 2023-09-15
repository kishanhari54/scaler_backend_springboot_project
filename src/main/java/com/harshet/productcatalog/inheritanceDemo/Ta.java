package com.harshet.productcatalog.inheritanceDemo;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_ta")
public class Ta extends User {
    private double averageRating;
}
