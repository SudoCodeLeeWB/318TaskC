package JOME.AnalyticsMicroservice.service;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.KeyValueIterator;
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


    // Use case 1

    /**
     * REFERENCE :  https://kafka.apache.org/10/documentation/streams/developer-guide/interactive-queries.html#querying-local-window-stores
     * @return number of orders made during time window
     * */
    public long getTotalOrdersForTimeWindow(Instant fromTime, Instant toTime) {

        // Accessing Storage ( TOTAL_ORDERS_STORE )
        ReadOnlyWindowStore<String, Long> storage = interactiveQueryService.getQueryableStore(
                TOTAL_ORDERS_STORE, QueryableStoreTypes.windowStore());

        long totalOrders = 0;

        // Iterate customer id in the store ( TOTAL_ORDERS_STORE )  &  sum up the order counts
        try (KeyValueIterator<Windowed<String>, Long> iterator = storage.fetchAll(fromTime, toTime)) {
            while (iterator.hasNext()) {
                KeyValue<Windowed<String>, Long> entry = iterator.next();
                totalOrders += entry.value;
            }
        } catch (Exception e) {
            e.printStackTrace(); // for error stack check
        }

        return totalOrders;
    }


    public double getTotalSalesForTimeWindow( String country, Instant fromTime, Instant toTime) {

        ReadOnlyWindowStore<String, Double> storage = interactiveQueryService.getQueryableStore(
                TOTAL_SALES_STORE, QueryableStoreTypes.windowStore());

        double totalSales = 0;

        try (WindowStoreIterator<Double> iterator = storage.fetch(country, fromTime,toTime)){
            while (iterator.hasNext()) {
                KeyValue<Long,Double> timeFrameSale = iterator.next();
                totalSales += timeFrameSale.value;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return totalSales;

    }




}
