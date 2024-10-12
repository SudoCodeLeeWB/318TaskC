package JOME.AnalyticsMicroservice.Controller;


import JOME.AnalyticsMicroservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {


    private final AnalyticsService analyticsService;


    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }


    @GetMapping("/getRecentSales")
    public ResponseEntity<Double> getRecentSales() {

        Double response = analyticsService.getSalsesRecentOneMins();

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/getRecentOrders")
    public ResponseEntity<Integer> getRecentOrderNumbers() {

        Integer response = analyticsService.getNumberOfOrdersRecentOneMins();

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



}