package pl.com.psl.testing.mockito.order;

import pl.com.psl.testing.mockito.customer.Customer;
import pl.com.psl.testing.mockito.customer.CustomerService;
import pl.com.psl.testing.mockito.item.Item;
import pl.com.psl.testing.mockito.item.ItemService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by psl on 24.07.17.
 */
public class OrderService {

    private OrderRepository orderRepository;
    private CustomerService customerService;
    private ItemService itemService;
    private OrderEventPublisher orderEventPublisher;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, ItemService itemService, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.itemService = itemService;
        this.orderEventPublisher = orderEventPublisher;
    }

    public void createOrder(Customer customer, List<Item> items) throws OrderServiceException{
        validateCustomer(customer);
        validateItems(items);
        canCoverExpense(customer, items);
        Order order = new Order(customer, items);
        try {
            order = orderRepository.write(order);
        } catch (OrderRepository.OrderRepositoryException e) {
            throw new OrderServiceException("Failed to write order into order repository!", e);
        }
        orderEventPublisher.publishOrderCreated(order.getId());
    }

    private void validateCustomer(Customer customer) throws OrderServiceException{
        if(customer.getAddress() == null || customer.getAddress().trim().isEmpty()){
            throw new OrderServiceException("Missing customer address!");
        }
        if(customer.getName() == null || customer.getName().trim().isEmpty()){
            throw new OrderServiceException("Missing customer address!");
        }
        if(!customerService.has(customer)){
            throw new OrderServiceException(String.format("Customer with id=%s and name=%s does not exist", customer.getId(), customer.getName()));
        }
    }

    private void validateItems(List<Item> items) throws OrderServiceException {
        if(items == null || items.isEmpty()){
            throw new OrderServiceException("Missing items!");
        }
        List<Long> missingItemsIds = items.stream()
                .filter(item -> !itemService.has(item))
                .collect(Collectors.mapping(Item::getId, Collectors.toList()));
        if(!missingItemsIds.isEmpty()){
            throw new OrderServiceException(String.format("Items with ids=[%s] do not exist", missingItemsIds));
        }
    }

    private void canCoverExpense(Customer customer, List<Item> items) throws OrderServiceException {
        int totalCost = items.stream().collect(Collectors.summingInt(Item::getPrice));
        if(!customerService.canCoverExpense(customer, totalCost)){
            throw new OrderServiceException("Customer can't cover the expense");
        }
    }

    public static class OrderServiceException extends Exception{

        public OrderServiceException(String message) {
            super(message);
        }

        public OrderServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
