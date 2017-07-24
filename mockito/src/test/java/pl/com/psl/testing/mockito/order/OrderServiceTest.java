package pl.com.psl.testing.mockito.order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.psl.testing.mockito.customer.Customer;
import pl.com.psl.testing.mockito.customer.CustomerService;
import pl.com.psl.testing.mockito.item.Item;
import pl.com.psl.testing.mockito.item.ItemService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

/**
 * Created by psl on 24.07.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    private static final String SOME_ADDRESS = "some address";
    private static final String SOME_CUSTOMER_NAME = "some customer name";
    private static final long SOME_ORDER_ID = 1L;

    @Mock
    private Order order;
    @Mock
    private Customer customer;
    @Mock
    private Item item1;
    @Mock
    private Item item2;
    @Mock
    private Item item3;
    private List<Item> items;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private ItemService itemService;
    @Mock
    private OrderEventPublisher orderEventPublisher;
    @InjectMocks
    private OrderService orderService;

    @Before
    public void init() {
        items = Arrays.asList(item1, item2, item3);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenCustomerAddressIsNull() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(null);
        orderService.createOrder(customer, items);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenCustomerAddressIsEmpty() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn("   ");
        orderService.createOrder(customer, items);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionCustomerNameIsNull() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(null);
        orderService.createOrder(customer, items);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionCustomerNameIsEmpty() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn("   ");
        orderService.createOrder(customer, items);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenCustomerDoesNotExist() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(SOME_CUSTOMER_NAME);
        when(customerService.has(customer)).thenReturn(Boolean.FALSE);
        orderService.createOrder(customer, items);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenItemsListIsNull() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(SOME_CUSTOMER_NAME);
        when(customerService.has(customer)).thenReturn(Boolean.TRUE);
        orderService.createOrder(customer, null);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenItemsListIsEmpty() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(SOME_CUSTOMER_NAME);
        when(customerService.has(customer)).thenReturn(Boolean.TRUE);
        orderService.createOrder(customer, Collections.EMPTY_LIST);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenItemDoesNotExist() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(SOME_CUSTOMER_NAME);
        when(customerService.has(customer)).thenReturn(Boolean.TRUE);
        when(item3.getId()).thenReturn(3L);
        when(itemService.has(item1)).thenReturn(Boolean.TRUE);
        when(itemService.has(item2)).thenReturn(Boolean.TRUE);
        when(itemService.has(item3)).thenReturn(Boolean.FALSE);
        orderService.createOrder(customer, items);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenCustomerCantCoverExpense() throws OrderService.OrderServiceException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(SOME_CUSTOMER_NAME);
        when(customerService.has(customer)).thenReturn(Boolean.TRUE);
        when(item1.getPrice()).thenReturn(10);
        when(item2.getPrice()).thenReturn(20);
        when(item3.getPrice()).thenReturn(30);
        when(itemService.has(item1)).thenReturn(Boolean.TRUE);
        when(itemService.has(item2)).thenReturn(Boolean.TRUE);
        when(itemService.has(item3)).thenReturn(Boolean.TRUE);
        when(customerService.canCoverExpense(customer, item1.getPrice() + item2.getPrice() + item3.getPrice())).thenReturn(Boolean.FALSE);
        orderService.createOrder(customer, items);
    }

    @Test(expected = OrderService.OrderServiceException.class)
    public void shouldThrowExceptionWhenCantWriteToOrderRepository() throws OrderService.OrderServiceException, OrderRepository.OrderRepositoryException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(SOME_CUSTOMER_NAME);
        when(customerService.has(customer)).thenReturn(Boolean.TRUE);
        when(item1.getPrice()).thenReturn(10);
        when(item2.getPrice()).thenReturn(20);
        when(item3.getPrice()).thenReturn(30);
        when(itemService.has(item1)).thenReturn(Boolean.TRUE);
        when(itemService.has(item2)).thenReturn(Boolean.TRUE);
        when(itemService.has(item3)).thenReturn(Boolean.TRUE);
        when(customerService.canCoverExpense(customer, item1.getPrice() + item2.getPrice() + item3.getPrice())).thenReturn(Boolean.TRUE);
        when(orderRepository.write(argThat(order -> order.getCustomer() == customer && order.getItems() == items))).thenThrow(OrderRepository.OrderRepositoryException.class);
        orderService.createOrder(customer, items);
    }

    @Test
    public void shouldSuccessfullyCreateOrder() throws OrderService.OrderServiceException, OrderRepository.OrderRepositoryException {
        when(customer.getAddress()).thenReturn(SOME_ADDRESS);
        when(customer.getName()).thenReturn(SOME_CUSTOMER_NAME);
        when(customerService.has(customer)).thenReturn(Boolean.TRUE);
        when(item1.getPrice()).thenReturn(10);
        when(item2.getPrice()).thenReturn(20);
        when(item3.getPrice()).thenReturn(30);
        when(itemService.has(item1)).thenReturn(Boolean.TRUE);
        when(itemService.has(item2)).thenReturn(Boolean.TRUE);
        when(itemService.has(item3)).thenReturn(Boolean.TRUE);
        when(customerService.canCoverExpense(customer, item1.getPrice() + item2.getPrice() + item3.getPrice())).thenReturn(Boolean.TRUE);
        when(order.getId()).thenReturn(SOME_ORDER_ID);
        when(orderRepository.write(argThat(order -> order.getCustomer() == customer && order.getItems() == items))).thenReturn(order);
        orderService.createOrder(customer, items);
        verify(orderEventPublisher, times(1)).publishOrderCreated(SOME_ORDER_ID);
    }
}
