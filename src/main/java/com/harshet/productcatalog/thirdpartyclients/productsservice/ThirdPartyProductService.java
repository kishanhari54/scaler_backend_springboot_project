package com.harshet.productcatalog.thirdpartyclients.productsservice;

import java.util.List;
import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.exceptions.NotFoundException;

public interface ThirdPartyProductService {

    GenericProductDTO getProductById(Long Id) throws NotFoundException;

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO updateProductById(Long Id, GenericProductDTO product);

    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO deleteProductById(Long Id);

}
