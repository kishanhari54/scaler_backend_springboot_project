package com.harshet.productcatalog.services;

import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshet.productcatalog.dtos.FakeStoreProductData;
import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.models.Product;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private String getAllProductRequestURL = "https://fakestoreapi.com/products";
    private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private String postProductRequestURL = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<GenericProductDTO> list = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<List<FakeStoreProductData>> response = restTemplate.exchange(getAllProductRequestURL,
                HttpMethod.GET,
                null, new ParameterizedTypeReference<List<FakeStoreProductData>>() {
                });

        List<FakeStoreProductData> fakeStoreProducts = response.getBody();
        for (FakeStoreProductData product : fakeStoreProducts) {
            GenericProductDTO genericProduct = new GenericProductDTO();
            genericProduct.setImage(product.getImage());
            genericProduct.setDescription(product.getDescription());
            genericProduct.setPrice(product.getPrice());
            genericProduct.setTitle(product.getTitle());
            genericProduct.setCategory(product.getCategory());
            genericProduct.setId(product.getId());
            list.add(genericProduct);
        }
        return list;
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
        // Create the request entity with headers and body if needed
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<FakeStoreProductData> response = restTemplate.exchange(getProductRequestURL, HttpMethod.DELETE,
                requestEntity, FakeStoreProductData.class, Id);
        FakeStoreProductData fakeStoreResponse = response.getBody();
        GenericProductDTO product = new GenericProductDTO();
        product.setImage(fakeStoreResponse.getImage());
        product.setDescription(fakeStoreResponse.getDescription());
        product.setPrice(fakeStoreResponse.getPrice());
        product.setTitle(fakeStoreResponse.getTitle());
        product.setCategory(fakeStoreResponse.getCategory());
        product.setId(fakeStoreResponse.getId());
        return product;
    }

    @Override
    public GenericProductDTO updateProductById(Long Id, GenericProductDTO productInfo) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            ObjectMapper ObjectMapper = new ObjectMapper();
            String requestBody;
            // Create the request entity with headers and body if needed
            HttpHeaders headers = new HttpHeaders();
            requestBody = ObjectMapper.writeValueAsString(productInfo);
            System.out.println(requestBody);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<FakeStoreProductData> response = restTemplate.exchange(getProductRequestURL, HttpMethod.PUT,
                    requestEntity, FakeStoreProductData.class, Id);
            FakeStoreProductData fakeStoreResponse = response.getBody();
            System.out.println(ObjectMapper.writeValueAsString(fakeStoreResponse));
            GenericProductDTO product = new GenericProductDTO();
            product.setImage(fakeStoreResponse.getImage());
            product.setDescription(fakeStoreResponse.getDescription());
            // product.setPrice(fakeStoreResponse.getPrice());
            product.setTitle(fakeStoreResponse.getTitle());
            product.setCategory(fakeStoreResponse.getCategory());
            product.setId(fakeStoreResponse.getId());
            return product;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
