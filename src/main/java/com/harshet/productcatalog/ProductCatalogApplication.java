package com.harshet.productcatalog;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.harshet.productcatalog.models.Category;
import com.harshet.productcatalog.models.Price;
import com.harshet.productcatalog.models.Product;
import com.harshet.productcatalog.repositories.ProductRepository;

@SpringBootApplication
public class ProductCatalogApplication implements CommandLineRunner {

	private ProductRepository productRepository;

	public ProductCatalogApplication(ProductRepository productRepo) {
		this.productRepository = productRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category();
		category.setName("Apple Devices");
		// Category savedCategory = categoryRepository.save(category);

		Price price = new Price("Rupee", 10);
		// Price savedPrice = priceRepository.save(price);

		Product product = new Product();
		product.setTitle("iPhone 15 Pro");
		product.setDescription("The best iPhone Ever");
		product.setCategory(category);
		product.setPrice(price);

		productRepository.save(product);

		List<Product> products = productRepository.findAllByTitle("iPhone 15 Pro");
		System.out.print(products);
	}
}
