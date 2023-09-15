package com.harshet.productcatalog.inheritanceDemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_ta")

public class Ta extends User {
    private double averageRating;
}
