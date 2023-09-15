package com.harshet.productcatalog.inheritanceDemo.joinedTableClass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
// @MappedSuperclass -- This is to not have a seperate table for User Class

// The below code is to create a single table with parent and child attributes.
@Entity(name = "jtc_table_user")
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
}
