package pl.com.psl.testing.mockito.order;

/**
 * Created by psl on 25.07.17.
 */
public interface OrderProcessor {

    void processOrder(Order order) throws OrderProcessorException;

    class OrderProcessorException extends Exception{}
}
