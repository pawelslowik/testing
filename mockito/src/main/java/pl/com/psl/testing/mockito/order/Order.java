package pl.com.psl.testing.mockito.order;

import pl.com.psl.testing.mockito.customer.Customer;
import pl.com.psl.testing.mockito.item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by psl on 24.07.17.
 */
public class Order {

    private long id;
    private Customer customer;
    private List<Item> items = new ArrayList<>();

    public Order(Customer customer, List<Item> items) {
        this.customer = customer;
        this.items = items;
    }

    public Order(long id, Customer customer, List<Item> items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
}
