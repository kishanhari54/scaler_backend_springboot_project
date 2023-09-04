package com.harshet.productcatalog.services;

import org.springframework.stereotype.Service;

import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.models.Product;

@Service("SelfProductService")
public class SelfProductService implements ProductService {

    @Override
    public GenericProductDTO getProductById(Long Id) {
        return null;
        // return new Product();
    }
}
