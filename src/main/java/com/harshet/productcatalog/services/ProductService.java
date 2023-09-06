package com.harshet.productcatalog.services;

import java.util.List;

import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.models.Product;

public interface ProductService {

    GenericProductDTO getProductById(Long Id);

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO updateProductById(Long Id, GenericProductDTO product);

    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO deleteProductById(Long Id);
}
