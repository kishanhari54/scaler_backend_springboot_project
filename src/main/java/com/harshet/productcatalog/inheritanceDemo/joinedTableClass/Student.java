package com.harshet.productcatalog.inheritanceDemo.joinedTableClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "jtc_student")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    private double PSP;
    private double attendance;
}
