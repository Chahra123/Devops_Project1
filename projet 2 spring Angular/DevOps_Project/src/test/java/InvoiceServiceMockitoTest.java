import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceMockitoTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    public void testRetrieveAllInvoices() {
        when(invoiceRepository.findAll()).thenReturn(Collections.singletonList(new Invoice()));

        invoiceService.retrieveAllInvoices();

        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    public void testCancelInvoice() {
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        invoiceService.cancelInvoice(1L);

        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    public void testRetrieveInvoice() {
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        invoiceService.retrieveInvoice(1L);

        verify(invoiceRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetInvoicesBySupplier() {

    }

    @Test
    public void testAssignOperatorToInvoice() {

    }

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        invoiceService.getTotalAmountInvoiceBetweenDates(new Date(), new Date());

        verify(invoiceRepository, times(1)).getTotalAmountInvoiceBetweenDates(any(Date.class), any(Date.class));
    }
}
