package com.learn.ecom.service.impl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecom.model.Product;
import com.learn.ecom.repository.ProductRepository;
import com.learn.ecom.service.impl.ProductServiceImpl;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setId(1);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);
        assertNotNull(savedProduct);
        assertEquals(1, savedProduct.getId());
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Boolean isDeleted = productService.deleteProduct(1);
        assertTrue(isDeleted);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1);
        assertNotNull(foundProduct);
        assertEquals(1, foundProduct.getId());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(1);
        product.setPrice(100.0);
        product.setDiscount(10);

        MultipartFile image = mock(MultipartFile.class);
        when(image.isEmpty()).thenReturn(true);

        Product dbProduct = new Product();
        dbProduct.setId(1);
        dbProduct.setPrice(100.0);

        when(productRepository.findById(1)).thenReturn(Optional.of(dbProduct));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product, image);
        assertNotNull(updatedProduct);
        assertEquals(1, updatedProduct.getId());
    }
}
