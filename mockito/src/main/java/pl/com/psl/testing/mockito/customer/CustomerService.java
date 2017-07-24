package pl.com.psl.testing.mockito.customer;

/**
 * Created by psl on 24.07.17.
 */
public interface CustomerService {

    boolean has(Customer customer);

    boolean canCoverExpense(Customer customer, int expense);
}
