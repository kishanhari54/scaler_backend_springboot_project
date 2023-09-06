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

    private String productRequestBaseURl = "https://fakestoreapi.com/products";
    private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private String postProductRequestURL = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDTO convertFakeStoretoGenericProduct(FakeStoreProductData fakeStoreProduct) {
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
    public List<GenericProductDTO> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        // The Below wont work , because of "Erasure" - the type is lost during runtime
        // and only checked in compile time.
        // ResponseEntity<List<FakeStoreProductData>> response =
        // restTemplate.getForEntity(productRequestBaseURl,
        // List<FakeStoreProductData.class>);
        ResponseEntity<FakeStoreProductData[]> response = restTemplate.getForEntity(productRequestBaseURl,
                FakeStoreProductData[].class);

        List<GenericProductDTO> list = new ArrayList<>();

        for (FakeStoreProductData fakeStoreProduct : response.getBody()) {
            list.add(this.convertFakeStoretoGenericProduct(fakeStoreProduct));
        }

        return list;

        /*
         * List<GenericProductDTO> list = new ArrayList<>();
         * RestTemplate restTemplate = restTemplateBuilder.build();
         * 
         * /*
         * ResponseEntity<List<FakeStoreProductData>> response =
         * restTemplate.exchange(getAllProductRequestURL,
         * HttpMethod.GET,
         * null, new ParameterizedTypeReference<List<FakeStoreProductData>>() {
         * });
         * /
         * ResponseEntity<FakeStoreProductData[]> response =
         * restTemplate.getForEntity(productRequestBaseURl,
         * FakeStoreProductData[].class);
         * 
         * FakeStoreProductData[] fakeStoreProducts = response.getBody();
         * for (FakeStoreProductData product : fakeStoreProducts) {
         * GenericProductDTO genericProduct = new GenericProductDTO();
         * genericProduct.setImage(product.getImage());
         * genericProduct.setDescription(product.getDescription());
         * genericProduct.setPrice(product.getPrice());
         * genericProduct.setTitle(product.getTitle());
         * genericProduct.setCategory(product.getCategory());
         * genericProduct.setId(product.getId());
         * list.add(genericProduct);
         * }
         * return list;
         */
    }

    @Override
    public GenericProductDTO getProductById(Long Id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductData> response = restTemplate.getForEntity(getProductRequestURL,
                FakeStoreProductData.class, Id); // Id is the variable being
        // passed
        FakeStoreProductData fakeStoreProduct = response.getBody();
        return this.convertFakeStoretoGenericProduct(fakeStoreProduct);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productInfo) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductData> response = restTemplate.postForEntity(postProductRequestURL, productInfo,
                FakeStoreProductData.class);

        return this.convertFakeStoretoGenericProduct(response.getBody());

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
        return this.convertFakeStoretoGenericProduct(fakeStoreResponse);
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
            // System.out.println(ObjectMapper.writeValueAsString(fakeStoreResponse));
            return this.convertFakeStoretoGenericProduct(fakeStoreResponse);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
