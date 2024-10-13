package JOME.ProductService.domain.event;

import JOME.ProductService.domain.entity.Product;

import java.time.LocalDateTime;

public class ProductDeleteEvent {


    private Long Id;
    private LocalDateTime eventCreateTime;

    public ProductDeleteEvent(){}

    public ProductDeleteEvent( Long Id ){

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
