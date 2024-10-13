package JOME.shared_events;



import java.time.LocalDateTime;

public class AddNewProductEventShared {

    private Long Id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private LocalDateTime recentUpdateTime;
    private LocalDateTime eventCreateTime;


    // constructor for translating the Product
    public AddNewProductEventShared() {}

    public AddNewProductEventShared(Long id, String name, String description, double price, int stock, LocalDateTime recentUpdateTime) {
        this.Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.recentUpdateTime = recentUpdateTime;
        this.eventCreateTime = LocalDateTime.now();
    }



    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public LocalDateTime getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(LocalDateTime recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }


    public LocalDateTime getEventCreateTime() {
        return eventCreateTime;
    }

    public void setEventCreateTime(LocalDateTime eventCreateTime) {
        this.eventCreateTime = eventCreateTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

