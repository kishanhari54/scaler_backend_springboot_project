package com.harshet.productcatalog.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harshet.productcatalog.dtos.FakeStoreProductData;
import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.models.Product;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private String postProductRequestURL = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDTO getProductById(Long Id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductData> response = restTemplate.getForEntity(getProductRequestURL,
                FakeStoreProductData.class, Id); // Id is the variable being
        // passed
        FakeStoreProductData fakeStoreProduct = response.getBody();

        GenericProductDTO product = new GenericProductDTO();
        product.setImage(fakeStoreProduct.getImage());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setCategory(fakeStoreProduct.getCategory());
        product.setId(fakeStoreProduct.getId());
        return product;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productInfo) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(postProductRequestURL, productInfo,
                GenericProductDTO.class);
        return response.getBody();

    }

    @Override
    public GenericProductDTO deleteProductById(Long Id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // return restTemplate.delete(getProductRequestURL,GenericProductDTO.class,Id);
        return null;
    }
}
