package com.harshet.productcatalog.services;

import java.util.List;

import com.harshet.exceptions.NotFoundException;
import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.models.Product;

public interface ProductService {

    GenericProductDTO getProductById(Long Id) throws NotFoundException;

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO updateProductById(Long Id, GenericProductDTO product);

    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO deleteProductById(Long Id);
}
