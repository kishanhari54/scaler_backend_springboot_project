package com.harshet.productcatalog.inheritanceDemo.joinedTableClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "jtc_mentor")
public class Mentor extends User {
    private double averageRating;
}
