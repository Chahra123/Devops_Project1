import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InvoiceServiceJunitTest {

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private OperatorRepository operatorRepository;

    @MockBean
    private SupplierRepository supplierRepository;

    @Test
    public void testRetrieveAllInvoices() {
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(invoiceRepository, operatorRepository, null, null);
        when(invoiceRepository.findAll()).thenReturn(Collections.singletonList(new Invoice()));
        List<Invoice> invoices = invoiceService.retrieveAllInvoices();
        assertEquals(1, invoices.size());
    }

    @Test
    public void testCancelInvoice() {
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(anyLong())).thenReturn(java.util.Optional.of(invoice));

        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(invoiceRepository, null, null, null);
        invoiceService.cancelInvoice(1L);

        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    public void testRetrieveInvoice() {
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(anyLong())).thenReturn(java.util.Optional.of(invoice));

        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(invoiceRepository, null, null, null);
        Invoice result = invoiceService.retrieveInvoice(1L);

        assertEquals(invoice, result);
    }

    @Test
    public void testGetInvoicesBySupplier() {
        Supplier supplier = new Supplier();
        supplier.setInvoices((Set<Invoice>) Collections.singletonList(new Invoice()));
        when(supplierRepository.findById(anyLong())).thenReturn(java.util.Optional.of(supplier));

        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(null, null, null, supplierRepository);
        List<Invoice> result = invoiceService.getInvoicesBySupplier(1L);

        assertEquals(1, result.size());
    }

    @Test
    public void testAssignOperatorToInvoice() {
        Invoice invoice = new Invoice();
        Operator operator = new Operator();
        when(invoiceRepository.findById(anyLong())).thenReturn(java.util.Optional.of(invoice));
        when(operatorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(operator));

        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(invoiceRepository, operatorRepository, null, null);
        invoiceService.assignOperatorToInvoice(1L, 1L);

        assertEquals(1, operator.getInvoices().size());
    }

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(invoiceRepository, null, null, null);
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(any(Date.class), any(Date.class))).thenReturn(100.0f);

        float result = invoiceService.getTotalAmountInvoiceBetweenDates(new Date(), new Date());

        assertEquals(100.0f, result);
    }
}
