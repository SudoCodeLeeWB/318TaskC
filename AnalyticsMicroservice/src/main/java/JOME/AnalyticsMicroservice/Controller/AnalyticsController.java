package JOME.AnalyticsMicroservice.Controller;


import JOME.AnalyticsMicroservice.DTO.GetCountrySalesDTO;
import JOME.AnalyticsMicroservice.DTO.RecentOrderCountDTO;
import JOME.AnalyticsMicroservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
public class AnalyticsController {


    private final AnalyticsService analyticsService;


    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }


    // WORKING!!
    @GetMapping("/getOrderNumbersFromBefore/{mins}")
    public ResponseEntity<RecentOrderCountDTO> getRecentSales(@PathVariable int mins) {

        Instant now = Instant.now();

        // Calculate 'mins' before from now
        Instant startTime = now.minus(mins, ChronoUnit.MINUTES);

        long recentOrderCount = analyticsService.getTotalOrdersForTimeWindow( startTime, now);
        RecentOrderCountDTO response = new RecentOrderCountDTO(recentOrderCount, startTime, now);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/getCountrySales/{country}/{mins}")
    public ResponseEntity<GetCountrySalesDTO> getRecentOrderNumbers(@PathVariable String country, @PathVariable int mins) {

        Instant toTime = Instant.now();
        Instant fromTime = toTime.minus(mins, ChronoUnit.MINUTES);

        double salesCount = analyticsService.getTotalSalesForTimeWindow( country , fromTime , toTime );

        GetCountrySalesDTO response = new GetCountrySalesDTO(country , salesCount , fromTime , toTime);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



}