// package com.example.demo;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.example.productapi.Product;
// import com.example.productapi.ProductRepository;
// import com.example.productapi.ProductService;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class) // Integrates Mockito with JUnit 5
// public class ProductServiceTests {

//     @Mock // This will be a mock object for ProductRepository
//     private ProductRepository productRepository;

//     @InjectMocks // Inject the mocked repository into ProductService
//     private ProductService productService;

//     private Product product1;
//     private Product product2;

//     @BeforeEach // This method runs before each test
//     void setUp() {
//         // Initialize dummy products for testing
//         product1 = new Product(1L, "Test Laptop", "Description 1", 1000.0);
//         product2 = new Product(2L, "Test Mouse", "Description 2", 20.0);
//     }

//     @Test
//     void testGetAllProducts() {
//         // Define behavior for the mock repository
//         when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

//         List<Product> products = productService.getAllProducts();

//         // Assertions
//         assertNotNull(products);
//         assertEquals(2, products.size());
//         assertEquals("Test Laptop", products.get(0).getName());
//         // Verify that findAll() was called on the mock
//         verify(productRepository, times(1)).findAll();
//     }

//     @Test
//     void testGetProductByIdFound() {
//         when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

//         Optional<Product> foundProduct = productService.getProductById(1L);

//         assertTrue(foundProduct.isPresent());
//         assertEquals("Test Laptop", foundProduct.get().getName());
//         verify(productRepository, times(1)).findById(1L);
//     }

//     @Test
//     void testGetProductByIdNotFound() {
//         when(productRepository.findById(3L)).thenReturn(Optional.empty());

//         Optional<Product> foundProduct = productService.getProductById(3L);

//         assertFalse(foundProduct.isPresent());
//         verify(productRepository, times(1)).findById(3L);
//     }

//     @Test
//     void testAddProduct() {
//         Product newProduct = new Product(null, "New Product", "New Desc", 50.0);
//         // Mock the save operation to return the product with an ID after saving
//         when(productRepository.save(any(Product.class))).thenReturn(new Product(3L, "New Product", "New Desc", 50.0));

//         Product createdProduct = productService.addProduct(newProduct);

//         assertNotNull(createdProduct.getId());
//         assertEquals("New Product", createdProduct.getName());
//         verify(productRepository, times(1)).save(any(Product.class));
//     }

//     @Test
//     void testUpdateProductFound() {
//         Product existingProduct = new Product(1L, "Old Name", "Old Desc", 10.0);
//         Product updatedDetails = new Product(null, "Updated Name", "Updated Desc", 20.0);

//         when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
//         when(productRepository.save(any(Product.class))).thenReturn(updatedDetails); // Mock save returns the updated product

//         Optional<Product> result = productService.updateProduct(1L, updatedDetails);

//         assertTrue(result.isPresent());
//         assertEquals("Updated Name", result.get().getName());
//         assertEquals(20.0, result.get().getPrice());

//         // Verify findById was called, then save was called with the modified product
//         verify(productRepository, times(1)).findById(1L);
//         verify(productRepository, times(1)).save(existingProduct); // Mockito will check if save was called with 'existingProduct' after its fields were changed
//     }


//     @Test
//     void testUpdateProductNotFound() {
//         when(productRepository.findById(99L)).thenReturn(Optional.empty());

//         Optional<Product> result = productService.updateProduct(99L, new Product());

//         assertFalse(result.isPresent());
//         verify(productRepository, times(1)).findById(99L);
//         verify(productRepository, never()).save(any(Product.class)); // save should not be called
//     }

//     @Test
//     void testDeleteProductExisting() {
//         when(productRepository.existsById(1L)).thenReturn(true);
//         doNothing().when(productRepository).deleteById(1L); // Configure void method mock

//         boolean result = productService.deleteProduct(1L);

//         assertTrue(result);
//         verify(productRepository, times(1)).existsById(1L);
//         verify(productRepository, times(1)).deleteById(1L);
//     }

//     @Test
//     void testDeleteProductNonExisting() {
//         when(productRepository.existsById(99L)).thenReturn(false);

//         boolean result = productService.deleteProduct(99L);

//         assertFalse(result);
//         verify(productRepository, times(1)).existsById(99L);
//         verify(productRepository, never()).deleteById(anyLong()); // deleteById should not be called
//     }
// }