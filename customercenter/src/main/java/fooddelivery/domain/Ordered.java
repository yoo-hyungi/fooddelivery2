package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Ordered extends AbstractEvent {

    private Long id;
    private String userId;
    private String orderId;
    private String foodname;
    private Integer qty;
    private Date orderDate;
    private String address;
    private String status;
    private Long foodid;
}
