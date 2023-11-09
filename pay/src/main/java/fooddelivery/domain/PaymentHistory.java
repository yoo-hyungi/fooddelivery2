package fooddelivery.domain;

import fooddelivery.PayApplication;
import fooddelivery.domain.PaymentApproved;
import fooddelivery.domain.PaymentCanceled;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PaymentHistory_table")
@Data
//<<< DDD / Aggregate Root
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long orderId;

    private Long price;

    private String status;

    @PostPersist
    public void onPostPersist() {
        PaymentApproved paymentApproved = new PaymentApproved(this);
        paymentApproved.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.publishAfterCommit();
    }

    public static PaymentHistoryRepository repository() {
        PaymentHistoryRepository paymentHistoryRepository = PayApplication.applicationContext.getBean(
            PaymentHistoryRepository.class
        );
        return paymentHistoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void paymentCancellation(OrderCanceled orderCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        PaymentHistory paymentHistory = new PaymentHistory();
        repository().save(paymentHistory);

        PaymentCanceled paymentCanceled = new PaymentCanceled(paymentHistory);
        paymentCanceled.publishAfterCommit();
        PaymentCanceled paymentCanceled = new PaymentCanceled(paymentHistory);
        paymentCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(paymentHistory->{
            
            paymentHistory // do something
            repository().save(paymentHistory);

            PaymentCanceled paymentCanceled = new PaymentCanceled(paymentHistory);
            paymentCanceled.publishAfterCommit();
            PaymentCanceled paymentCanceled = new PaymentCanceled(paymentHistory);
            paymentCanceled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
