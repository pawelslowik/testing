package pl.com.psl.testing.mockito.order;

/**
 * Created by psl on 24.07.17.
 */
public interface OrderRepository {

    Order write(Order order) throws OrderRepositoryException;
    Order read(long id) throws OrderRepositoryException;

    class OrderRepositoryException extends Exception {}
}
