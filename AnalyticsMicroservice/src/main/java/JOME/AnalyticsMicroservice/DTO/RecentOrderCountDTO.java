package JOME.AnalyticsMicroservice.DTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RecentOrderCountDTO {

    public long orderedTotal;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public RecentOrderCountDTO(long ordered , Instant start , Instant end) {

        this.orderedTotal = ordered;
        this.startTime = LocalDateTime.ofInstant(start , ZoneId.systemDefault());
        this.endTime = LocalDateTime.ofInstant(end , ZoneId.systemDefault());
    }
}
