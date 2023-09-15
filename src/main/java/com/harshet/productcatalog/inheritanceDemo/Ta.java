package com.harshet.productcatalog.inheritanceDemo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_ta")
@DiscriminatorValue(value = "2")
public class Ta extends User {
    private double averageRating;
}
