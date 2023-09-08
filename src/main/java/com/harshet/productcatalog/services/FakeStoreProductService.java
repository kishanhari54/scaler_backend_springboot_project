package com.harshet.productcatalog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.exceptions.NotFoundException;

import com.harshet.productcatalog.thirdpartyclients.productsservice.fakeStore.FakeStoreProductData;
import com.harshet.productcatalog.thirdpartyclients.productsservice.fakeStore.fakeStoreService;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private fakeStoreService fakeStoreService;
    private FakeStoreProductData[] allProducts;

    public FakeStoreProductService(fakeStoreService fakeStoreService) {
        this.fakeStoreService = fakeStoreService;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        allProducts = this.fakeStoreService.getAllProducts();
        List<GenericProductDTO> genericProduct = new ArrayList();
        for (FakeStoreProductData product : allProducts) {
            GenericProductDTO gProduct = new GenericProductDTO();
            gProduct.setCategory(product.getCategory());
            gProduct.setDescription(product.getDescription());
            gProduct.setImage(product.getImage());
            gProduct.setPrice(product.getPrice());
            gProduct.setTitle(product.getTitle());
            gProduct.setId(product.getId());
            genericProduct.add(gProduct);
        }

        return genericProduct;
    }

    @Override
    public GenericProductDTO getProductById(Long Id) throws NotFoundException {
        return this.convertFakeStoretoGenericProduct(this.fakeStoreService.getProductById(Id));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productInfo) {
        return this.convertFakeStoretoGenericProduct(
                this.fakeStoreService.createProduct(this.convertFakeStoretoGenericProduct(productInfo)));
    }

    @Override
    public GenericProductDTO deleteProductById(Long Id) {
        return this.convertFakeStoretoGenericProduct(this.fakeStoreService.deleteProductById(Id));
    }

    @Override
    public GenericProductDTO updateProductById(Long Id, GenericProductDTO productInfo) {
        return this.convertFakeStoretoGenericProduct(
                this.fakeStoreService.updateProductById(Id, this.convertFakeStoretoGenericProduct(productInfo)));
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

    private FakeStoreProductData convertFakeStoretoGenericProduct(GenericProductDTO fakeStoreProduct) {
        FakeStoreProductData product = new FakeStoreProductData();
        product.setImage(fakeStoreProduct.getImage());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setCategory(fakeStoreProduct.getCategory());
        product.setId(fakeStoreProduct.getId());
        return product;
    }
}
