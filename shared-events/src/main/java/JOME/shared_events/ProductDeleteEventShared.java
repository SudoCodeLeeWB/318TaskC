package JOME.shared_events;
import java.time.LocalDateTime;

public class ProductDeleteEventShared {


    private Long Id;
    private LocalDateTime eventCreateTime;

    public ProductDeleteEventShared(){}

    public ProductDeleteEventShared(Long Id ){

        this.Id = Id;
        this.eventCreateTime = LocalDateTime.now();

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getEventCreateTime() {
        return eventCreateTime;
    }

    public void setEventCreateTime(LocalDateTime eventCreateTime) {
        this.eventCreateTime = eventCreateTime;
    }
}
