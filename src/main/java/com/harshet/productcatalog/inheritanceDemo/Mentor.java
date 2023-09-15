package com.harshet.productcatalog.inheritanceDemo;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_mentor")
public class Mentor extends User {
    private double averageRating;
}
