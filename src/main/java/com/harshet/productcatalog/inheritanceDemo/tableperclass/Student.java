package com.harshet.productcatalog.inheritanceDemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_student")
public class Student extends User {
    private double PSP;
    private double attendance;
}
