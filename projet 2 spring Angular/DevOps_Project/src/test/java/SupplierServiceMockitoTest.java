import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class SupplierServiceMockitoTest {

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        // Given
        List<Supplier> expectedSuppliers = new ArrayList<>();
        expectedSuppliers.add(new Supplier(1L, "Supplier 1"));
        expectedSuppliers.add(new Supplier(2L, "Supplier 2"));

        when(supplierRepository.findAll()).thenReturn(expectedSuppliers);

        // When
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Then
        verify(supplierRepository).findAll();
    }

    @Test
    public void testAddSupplier() {
        // Given
        Supplier supplier = new Supplier(1L, "Supplier 1");

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // When
        supplierService.addSupplier(supplier);

        // Then
        verify(supplierRepository).save(supplier);
    }

    @Test
    public void testUpdateSupplier() {
        // Given
        Supplier supplier = new Supplier(1L, "Supplier 1");

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // When
        supplierService.updateSupplier(supplier);

        // Then
        verify(supplierRepository).save(supplier);
    }

    @Test
    public void testDeleteSupplier() {
        // Given
        long supplierId = 1L;

        // No need to mock behavior for delete operation

        // When
        supplierService.deleteSupplier(supplierId);

        // Then
        verify(supplierRepository).deleteById(supplierId);
    }

    public void testRetrieveSupplier() {
        // Given
        long supplierId = 1L;
        Supplier expectedSupplier = new Supplier(supplierId, "Supplier 1");

        // Mocking behavior of repository
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(expectedSupplier));

        // When
        supplierService.retrieveSupplier(supplierId);

        // Then
        verify(supplierRepository).findById(supplierId);
    }
}
