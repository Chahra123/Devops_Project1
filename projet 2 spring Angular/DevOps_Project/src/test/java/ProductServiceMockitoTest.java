import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceMockitoTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        // Arrange
        Product product = new Product();
        product.setTitle("Test Product");
        Long idStock = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product addedProduct = productService.addProduct(product, idStock);

        // Assert
        assertEquals(product, addedProduct);
    }

    @Test
    public void testRetrieveProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product retrievedProduct = productService.retrieveProduct(productId);

        // Assert
        assertEquals(product, retrievedProduct);
    }

    @Test
    public void testRetrieveAllProduct() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> retrievedProducts = productService.retreiveAllProduct();

        // Assert
        assertEquals(productList, retrievedProducts);
    }

    @Test
    public void testRetrieveProductByCategory() {
        // Arrange
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> productList = new ArrayList<>();
        when(productRepository.findByCategory(category)).thenReturn(productList);

        // Act
        List<Product> retrievedProducts = productService.retrieveProductByCategory(category);

        // Assert
        assertEquals(productList, retrievedProducts);
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testRetrieveProductStock() {
        // Arrange
        Long stockId = 1L;
        List<Product> productList = new ArrayList<>();
        when(productRepository.findByStockIdStock(stockId)).thenReturn(productList);

        // Act
        List<Product> retrievedProducts = productService.retreiveProductStock(stockId);

        // Assert
        assertEquals(productList, retrievedProducts);
    }
}