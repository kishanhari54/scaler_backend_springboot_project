package com.harshet.productcatalog.inheritanceDemo.joinedTableClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "jtc_ta")
public class Ta extends User {
    private double averageRating;
}
