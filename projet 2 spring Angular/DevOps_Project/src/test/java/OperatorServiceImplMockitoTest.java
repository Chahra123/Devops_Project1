
  import org.junit.jupiter.api.BeforeEach;
  import org.junit.jupiter.api.Test;
  import org.mockito.InjectMocks;
  import org.mockito.Mock;
  import org.mockito.MockitoAnnotations;
  import tn.esprit.devops_project.entities.Operator;
  import tn.esprit.devops_project.repositories.OperatorRepository;
  import tn.esprit.devops_project.services.OperatorServiceImpl;

  import java.util.ArrayList;
  import java.util.List;
  import java.util.Optional;

  import static org.junit.jupiter.api.Assertions.assertEquals;
  import static org.junit.jupiter.api.Assertions.assertThrows;
  import static org.mockito.Mockito.*;

public class OperatorServiceImplMockitoTest {

  @Mock
  private OperatorRepository operatorRepository;

  @InjectMocks
  private OperatorServiceImpl operatorService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testRetrieveAllOperators() {
    // Create a list of operators
    List<Operator> operators = new ArrayList<>();
    operators.add(new Operator());
    operators.add(new Operator());

    // Stub the repository method to return the list
    when(operatorRepository.findAll()).thenReturn(operators);

    // Call the service method
    List<Operator> retrievedOperators = operatorService.retrieveAllOperators();

    // Verify the result
    assertEquals(2, retrievedOperators.size());

    // Verify that the repository method was called
    verify(operatorRepository, times(1)).findAll();
  }

  @Test
  public void testAddOperator() {
    // Create an operator
    Operator operator = new Operator();

    // Stub the repository method to return the saved operator
    when(operatorRepository.save(operator)).thenReturn(operator);

    // Call the service method
    Operator addedOperator = operatorService.addOperator(operator);

    // Verify the result
    assertEquals(operator, addedOperator);

    // Verify that the repository method was called
    verify(operatorRepository, times(1)).save(operator);
  }

  @Test
  public void testDeleteOperator() {
    // Setup
    Long id = 1L;

    // Call the service method
    operatorService.deleteOperator(id);

    // Verify that the repository method was called
    verify(operatorRepository, times(1)).deleteById(id);
  }

  @Test
  public void testUpdateOperator() {
    // Create an operator
    Operator operator = new Operator();

    // Stub the repository method to return the saved operator
    when(operatorRepository.save(operator)).thenReturn(operator);

    // Call the service method
    Operator updatedOperator = operatorService.updateOperator(operator);

    // Verify the result
    assertEquals(operator, updatedOperator);

    // Verify that the repository method was called
    verify(operatorRepository, times(1)).save(operator);
  }

  @Test
  public void testRetrieveOperator_Exists() {
    // Setup
    Long id = 1L;
    Operator operator = new Operator();

    // Stub the repository method to return the operator
    when(operatorRepository.findById(id)).thenReturn(Optional.of(operator));

    // Call the service method
    Operator retrievedOperator = operatorService.retrieveOperator(id);

    // Verify the result
    assertEquals(operator, retrievedOperator);

    // Verify that the repository method was called
    verify(operatorRepository, times(1)).findById(id);
  }

  @Test
  public void testRetrieveOperator_NotExists() {
    // Setup
    Long id = 1L;

    // Stub the repository method to return empty optional
    when(operatorRepository.findById(id)).thenReturn(Optional.empty());

    // Verify that the service throws NullPointerException
    assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(id));

    // Verify that the repository method was called
    verify(operatorRepository, times(1)).findById(id);
  }
}
