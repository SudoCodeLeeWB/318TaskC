package JOME.AnalyticsMicroservice.service;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


// Exposing Aggregated Data using InteractiveQuery;
@Service
public class  AnalyticsService {

    private final InteractiveQueryService interactiveQueryService;

    @Autowired
    public AnalyticsService(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    // Storages
    public final static String TOTAL_ORDERS_STORE = "total_orders";
    public final static String TOTAL_SALES_STORE = "total_sales";


    public long getTotalOrdersForTimeWindow(Instant fromTime, Instant toTime) {
        // Access the windowed store for orders count
        ReadOnlyWindowStore<String, Long> store = interactiveQueryService.getQueryableStore(
                TOTAL_ORDERS_STORE, QueryableStoreTypes.windowStore());

        // Fetch all records within the time window
        WindowStoreIterator<Long> orders = store.fetch("totalOrders", fromTime, toTime);

        long totalOrders = 0;
        while (orders.hasNext()) {
            KeyValue<Long, Long> windowedOrder = orders.next();
            totalOrders += windowedOrder.value; // Sum up the total orders
        }

        return totalOrders;
    }



}
