package pl.com.psl.testing.mockito.customer;

/**
 * Created by psl on 24.07.17.
 */
public class Customer {

    private long id;
    private String name;
    private String address;

    public Customer(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
