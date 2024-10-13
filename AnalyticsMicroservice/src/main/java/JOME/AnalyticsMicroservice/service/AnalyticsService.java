package JOME.AnalyticsMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;



// Exposing Aggregated Data using InteractiveQuery;
@Service
public class  AnalyticsService {

    private final InteractiveQueryService interactiveQueryService;

    @Autowired
    public AnalyticsService(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    // simplify : can extend the method fucntionality by getting the actual time as a parameter, but will not go there
    // ( since it is a demo )

    // Access point for Aggregated Data
    public double getSalsesRecentOneMins(){



        return 0.0;
    }

    public int getNumberOfOrdersRecentOneMins(){




        return 0;
    }



}
