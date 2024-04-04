import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class StockServiceImplJunit {
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        stockRepository = mock(StockRepository.class);
    }

    @Test
    public void testAddStock() {
        // Arrange
        Stock stockToAdd = new Stock();
        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        // Act
        StockServiceImpl stockService = new StockServiceImpl(stockRepository);
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
        StockServiceImpl stockService = new StockServiceImpl(stockRepository);
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
        StockServiceImpl stockService = new StockServiceImpl(stockRepository);
        List<Stock> retrievedStockList = stockService.retrieveAllStock();

        // Assert
        assertEquals(expectedStockList, retrievedStockList);
    }

}