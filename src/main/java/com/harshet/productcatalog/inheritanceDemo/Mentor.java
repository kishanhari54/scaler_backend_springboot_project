package com.harshet.productcatalog.inheritanceDemo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_mentor")
@DiscriminatorValue(value = "3")
public class Mentor extends User {
    private double averageRating;
}
