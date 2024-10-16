package JOME.AnalyticsMicroservice.DTO;

import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class GetCountrySalesDTO {

    public String country;
    public double salesForCountry;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public GetCountrySalesDTO(String country, double salesForCountry, Instant startTime, Instant endTime) {
        this.country = country;
        this.salesForCountry = salesForCountry;
        this.startTime = LocalDateTime.ofInstant(startTime , ZoneId.systemDefault()) ;
        this.endTime = LocalDateTime.ofInstant(endTime , ZoneId.systemDefault()) ;
    }

}
