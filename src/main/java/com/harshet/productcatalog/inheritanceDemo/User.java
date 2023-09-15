package com.harshet.productcatalog.inheritanceDemo;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
}
