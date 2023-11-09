package fooddelivery.domain;

import fooddelivery.OrderApplication;
import fooddelivery.domain.OrderCanceled;
import fooddelivery.domain.Ordered;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long orderId;

    private String foodname;

    private Integer qty;

    private Date orderDate;

    private String address;

    private String status;

    private Long foodid;

    private Long price;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        fooddelivery.external.PaymentHistory paymentHistory = new fooddelivery.external.PaymentHistory();
        // mappings goes here
        OrderApplication.applicationContext
            .getBean(fooddelivery.external.PaymentHistoryService.class)
            .payment(paymentHistory);

        Ordered ordered = new Ordered(this);
        ordered.publishAfterCommit();

        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
//>>> DDD / Aggregate Root
