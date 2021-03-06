package pl.com.psl.testing.mockito.item;

/**
 * Created by psl on 24.07.17.
 */
public class Item {

    private long id;
    private String name;
    private String description;
    private int price;

    public Item(long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }


}
