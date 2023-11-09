package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private String userId;
    private Long orderId;
    private Integer qty;
    private String address;
    private String status;
    private String foodname;
    private Long foodId;
}
