import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
public class StockServiceImplMockito {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStock() {
        // Arrange
        Stock stockToAdd = new Stock();
        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        // Act
        Stock addedStock = stockService.addStock(stockToAdd);

        // Assert
        assertNotNull(addedStock);
        assertEquals(stockToAdd, addedStock);
    }

    @Test
    public void testRetrieveStock() {
        // Arrange
        Long stockId = 1L;
        Stock expectedStock = new Stock();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(expectedStock));

        // Act
        Stock retrievedStock = stockService.retrieveStock(stockId);

        // Assert
        assertEquals(expectedStock, retrievedStock);
    }

    @Test
    public void testRetrieveAllStock() {
        // Arrange
        List<Stock> expectedStockList = new ArrayList<>();
        when(stockRepository.findAll()).thenReturn(expectedStockList);

        // Act
        List<Stock> retrievedStockList = stockService.retrieveAllStock();

        // Assert
        assertEquals(expectedStockList, retrievedStockList);
    }


}
