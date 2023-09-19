package com.harshet.productcatalog.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.harshet.productcatalog.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitleEquals(String title);

    Product findByTitleEqualsAndPrice_Price(String title, double price);

    List<Product> findAllByPrice_Currency(String currency);

    @Query(value = "select * from product where title = :title", nativeQuery = true)
    List<Product> findAllByTitle(String title);
}
