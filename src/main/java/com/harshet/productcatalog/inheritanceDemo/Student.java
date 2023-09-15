package com.harshet.productcatalog.inheritanceDemo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_student")
@DiscriminatorValue(value = "1")
public class Student extends User {
    private double PSP;
    private double attendance;
}
