package JOME.AnalyticsMicroservice.Controller;


import JOME.AnalyticsMicroservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AnalyticsController {


    private final AnalyticsService analyticsService;


    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // Only for testing

    @GetMapping("/getOrderNumbersFromBefore/{mins}")
    public ResponseEntity<Long> getRecentSales(@PathVariable int mins) {

        Instant now = Instant.now();

        // Calculate 'mins' before from now
        Instant startTime = now.minus(mins, ChronoUnit.MINUTES);

        Long recentOrderCount = analyticsService.getTotalOrdersForTimeWindow( startTime, now);
        return new ResponseEntity<>(recentOrderCount, HttpStatus.OK);
    }


//    @GetMapping("/getRecentOrders")
//    public ResponseEntity<Integer> getRecentOrderNumbers() {
//
//        Integer response = analyticsService.getNumberOfOrdersRecentOneMin();
//
//        if (response != null) {
//            return ResponseEntity.ok(response);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//    }
//


}