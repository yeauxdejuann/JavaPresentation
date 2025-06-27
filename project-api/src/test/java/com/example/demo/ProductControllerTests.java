// package com.example.demo;

// import com.example.productapi.Product;
// import com.example.productapi.ProductController;
// import com.example.productapi.ProductService;
// import com.fasterxml.jackson.databind.ObjectMapper; // NEW for JSON conversion
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.Arrays;
// import java.util.Optional;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(ProductController.class) // Focuses test on ProductController, loads minimal Spring context
// public class ProductControllerTests {

//     @Autowired
//     private MockMvc mockMvc; // Used to perform simulated HTTP requests

//     @MockBean // Creates a Mockito mock and registers it as a Spring Bean
//     private ProductService productService;

//     @Autowired
//     private ObjectMapper objectMapper; // Helper to convert objects to JSON and vice-versa

//     private Product product1;
//     private Product product2;

//     @BeforeEach
//     void setUp() {
//         product1 = new Product(1L, "Test Laptop", "Desc L", 1000.0);
//         product2 = new Product(2L, "Test Mouse", "Desc M", 20.0);
//     }

//     @Test
//     void testGetAllProducts() throws Exception {
//         when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

//         mockMvc.perform(get("/api/products"))
//                .andExpect(status().isOk()) // Expect 200 OK
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Expect JSON content
//                .andExpect(jsonPath("$[0].name").value("Test Laptop")) // Check first product's name
//                .andExpect(jsonPath("$.length()").value(2)); // Check array size

//         verify(productService, times(1)).getAllProducts();
//     }

//     @Test
//     void testGetProductByIdFound() throws Exception {
//         when(productService.getProductById(1L)).thenReturn(Optional.of(product1));

//         mockMvc.perform(get("/api/products/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name").value("Test Laptop"));

//         verify(productService, times(1)).getProductById(1L);
//     }

//     @Test
//     void testGetProductByIdNotFound() throws Exception {
//         when(productService.getProductById(99L)).thenReturn(Optional.empty());

//         mockMvc.perform(get("/api/products/99"))
//                .andExpect(status().isNotFound()); // Expect 404 Not Found

//         verify(productService, times(1)).getProductById(99L);
//     }

//     @Test
//     void testAddProductValid() throws Exception {
//         Product newProduct = new Product(null, "New Keyboard", "Gaming", 150.0);
//         Product savedProduct = new Product(3L, "New Keyboard", "Gaming", 150.0);

//         when(productService.addProduct(any(Product.class))).thenReturn(savedProduct);

//         mockMvc.perform(post("/api/products")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(newProduct))) // Convert Product to JSON string
//                .andExpect(status().isCreated()) // Expect 201 Created
//                .andExpect(jsonPath("$.id").value(3L))
//                .andExpect(jsonPath("$.name").value("New Keyboard"));

//         verify(productService, times(1)).addProduct(any(Product.class));
//     }

//     @Test
//     void testAddProductInvalidName() throws Exception {
//         Product invalidProduct = new Product(null, "a", "Invalid name short", 10.0); // Name too short

//         mockMvc.perform(post("/api/products")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidProduct)))
//                .andExpect(status().isBadRequest()) // Expect 400 Bad Request due to validation
//                .andExpect(jsonPath("$.errors").exists()); // Spring's default error structure for validation
//     }

//     @Test
//     void testAddProductInvalidPrice() throws Exception {
//         Product invalidProduct = new Product(null, "Valid Name", "Negative Price", -5.0); // Price negative

//         mockMvc.perform(post("/api/products")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidProduct)))
//                .andExpect(status().isBadRequest()); // Expect 400 Bad Request
//     }

//     @Test
//     void testUpdateProductValid() throws Exception {
//         Product updatedDetails = new Product(1L, "Updated Laptop", "Updated Desc", 1200.0);
//         when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(Optional.of(updatedDetails));

//         mockMvc.perform(put("/api/products/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(updatedDetails)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Updated Laptop"));

//         verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
//     }

//     @Test
//     void testUpdateProductInvalidName() throws Exception {
//         Product invalidUpdate = new Product(1L, "", "Invalid name empty", 10.0); // Name empty

//         mockMvc.perform(put("/api/products/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidUpdate)))
//                .andExpect(status().isBadRequest()); // Expect 400 Bad Request
//     }

//     @Test
//     void testDeleteProductExisting() throws Exception {
//         when(productService.deleteProduct(1L)).thenReturn(true);

//         mockMvc.perform(delete("/api/products/1"))
//                .andExpect(status().isNoContent()); // Expect 204 No Content

//         verify(productService, times(1)).deleteProduct(1L);
//     }

//     @Test
//     void testDeleteProductNonExisting() throws Exception {
//         when(productService.deleteProduct(99L)).thenReturn(false);

//         mockMvc.perform(delete("/api/products/99"))
//                .andExpect(status().isNotFound()); // Expect 404 Not Found

//         verify(productService, times(1)).deleteProduct(99L);
//     }
// }
