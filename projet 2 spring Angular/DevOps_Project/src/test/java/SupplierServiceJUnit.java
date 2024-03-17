import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupplierServiceJUnit {

    private SupplierRepository supplierRepository;
    private SupplierServiceImpl supplierService;

    @BeforeEach
    public void setUp() {
        supplierRepository = mock(SupplierRepository.class);
        supplierService = new SupplierServiceImpl(supplierRepository);
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
        assertEquals(expectedSuppliers.size(), result.size());
        assertEquals(expectedSuppliers.get(0).getIdSupplier(), result.get(0).getIdSupplier());
        assertEquals(expectedSuppliers.get(1).getIdSupplier(), result.get(1).getIdSupplier());
    }

    @Test
    public void testAddSupplier() {
        // Given
        Supplier supplier = new Supplier(1L, "Supplier 1");

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // When
        Supplier addedSupplier = supplierService.addSupplier(supplier);

        // Then
        assertEquals(supplier, addedSupplier);
    }

    @Test
    public void testUpdateSupplier() {
        // Given
        Supplier supplier = new Supplier(1L, "Supplier 1");

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // When
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        // Then
        assertEquals(supplier, updatedSupplier);
    }

    @Test
    public void testDeleteSupplier() {
        // Given
        long supplierId = 1L;

        // No need to mock behavior for delete operation

        // When
        supplierService.deleteSupplier(supplierId);

        // Then
        assertThrows(IllegalArgumentException.class, () -> supplierService.retrieveSupplier(supplierId));
    }

    @Test
    public void testRetrieveSupplier() {
        // Given
        long supplierId = 1L;
        Supplier expectedSupplier = new Supplier(supplierId, "Supplier 1");

        when(supplierRepository.findById(supplierId)).thenReturn(java.util.Optional.of(expectedSupplier));

        // When
        Supplier retrievedSupplier = supplierService.retrieveSupplier(supplierId);

        // Then
        assertEquals(expectedSupplier, retrievedSupplier);
    }
}
