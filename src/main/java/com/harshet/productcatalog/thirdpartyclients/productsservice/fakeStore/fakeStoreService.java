package com.harshet.productcatalog.thirdpartyclients.productsservice.fakeStore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshet.productcatalog.exceptions.NotFoundException;

@Primary
@Service("fakeStore")
public class fakeStoreService {

    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakestore.baseurl}")
    private String fakeStoreAPIBase;
    @Value("${fakestore.products}")
    private String fakeStoreAPIProductsBase;
    private String productRequestBaseURl = "https://fakestoreapi.com/products";
    private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private String postProductRequestURL = "https://fakestoreapi.com/products";

    public fakeStoreService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductData[] getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductData[]> response = restTemplate.getForEntity(productRequestBaseURl,
                FakeStoreProductData[].class);
        return response.getBody();
    }

    public FakeStoreProductData getProductById(Long Id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductData> response = restTemplate.getForEntity(getProductRequestURL,
                FakeStoreProductData.class, Id); // Id is the variable being
        // passed
        FakeStoreProductData fakeStoreProduct = response.getBody();
        if (fakeStoreProduct == null) {
            throw new NotFoundException("Product With Id: " + Id + "Does Not Exist");
        }
        return fakeStoreProduct;
    }

    public FakeStoreProductData createProduct(FakeStoreProductData productInfo) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductData> response = restTemplate.postForEntity(postProductRequestURL, productInfo,
                FakeStoreProductData.class);

        return response.getBody();

    }

    public FakeStoreProductData deleteProductById(Long Id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // Create the request entity with headers and body if needed
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<FakeStoreProductData> response = restTemplate.exchange(getProductRequestURL, HttpMethod.DELETE,
                requestEntity, FakeStoreProductData.class, Id);
        FakeStoreProductData fakeStoreResponse = response.getBody();
        return fakeStoreResponse;
    }

    public FakeStoreProductData updateProductById(Long Id, FakeStoreProductData productInfo) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            ObjectMapper ObjectMapper = new ObjectMapper();
            String requestBody;
            // Create the request entity with headers and body if needed
            HttpHeaders headers = new HttpHeaders();
            requestBody = ObjectMapper.writeValueAsString(productInfo);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<FakeStoreProductData> response = restTemplate.exchange(getProductRequestURL, HttpMethod.PUT,
                    requestEntity, FakeStoreProductData.class, Id);
            FakeStoreProductData fakeStoreResponse = response.getBody();
            return fakeStoreResponse;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
