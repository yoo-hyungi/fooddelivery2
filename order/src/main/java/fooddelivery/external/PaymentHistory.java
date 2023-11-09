package fooddelivery.external;

import java.util.Date;
import lombok.Data;

@Data
public class PaymentHistory {

    private Long id;
    private String userId;
    private Long orderId;
    private Long price;
    private String status;
    private Integer qty;
    private String address;
    private String foodId;
    private String foodname;
}
