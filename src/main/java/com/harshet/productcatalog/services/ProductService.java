package com.harshet.productcatalog.services;

import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.models.Product;

public interface ProductService {

    GenericProductDTO getProductById(Long Id);

    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO deleteProductById(Long Id);
}
