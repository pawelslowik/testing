package pl.com.psl.testing.mockito.order;

/**
 * Created by psl on 24.07.17.
 */
public interface OrderEventPublisher {

    void publishOrderCreated(long id);
}
