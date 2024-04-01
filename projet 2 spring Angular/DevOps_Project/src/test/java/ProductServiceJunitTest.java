import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceJunitTest {
    private ProductRepository productRepository;
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        stockRepository = mock(StockRepository.class);
    }

    @Test
    public void testAddProductSuccess() {
        // Arrange
        Product productToAdd = new Product();
        Long idStock = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));
        when(productRepository.save(productToAdd)).thenReturn(productToAdd);

        // Act
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);
        Product addedProduct = productService.addProduct(productToAdd, idStock);

        // Assert
        assertNotNull(addedProduct);
        assertEquals(stock, addedProduct.getStock());
    }
    @Test
    public void testAddProductStockNotFound() {
        // Arrange
        Product productToAdd = new Product();
        Long idStock = 1L;

        // Act & Assert
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);
        assertThrows(NullPointerException.class, () -> productService.addProduct(productToAdd, idStock));
    }
    @Test
    public void testRetrieveProduct() {
        // Arrange
        Long productId = 1L;
        Product expectedProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(expectedProduct));

        // Act
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);
        Product retrievedProduct = productService.retrieveProduct(productId);

        // Assert
        assertEquals(expectedProduct, retrievedProduct);
    }

    @Test
    public void testRetrieveAllProduct() {
        // Arrange
        List<Product> expectedProductList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(expectedProductList);

        // Act
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);
        List<Product> retrievedProductList = productService.retreiveAllProduct();

        // Assert
        assertEquals(expectedProductList, retrievedProductList);
    }

    @Test
    public void testRetrieveProductByCategory() {
        // Arrange
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> expectedProductList = new ArrayList<>();
        when(productRepository.findByCategory(category)).thenReturn(expectedProductList);

        // Act
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);
        List<Product> retrievedProductList = productService.retrieveProductByCategory(category);

        // Assert
        assertEquals(expectedProductList, retrievedProductList);
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }

}

