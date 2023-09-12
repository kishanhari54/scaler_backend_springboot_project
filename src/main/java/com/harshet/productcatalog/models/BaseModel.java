package com.harshet.productcatalog.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass // Parent table will not have any super class
public class BaseModel {
    @Id
    // @GeneratedValue(generator = "uuidgenerator")
    // @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    @UuidGenerator
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;
}