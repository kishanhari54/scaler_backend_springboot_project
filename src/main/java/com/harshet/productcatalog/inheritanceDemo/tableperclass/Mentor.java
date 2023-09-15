package com.harshet.productcatalog.inheritanceDemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_mentor")
public class Mentor extends User {
    private double averageRating;
}
