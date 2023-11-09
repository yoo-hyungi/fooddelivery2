package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.Canceled;
import fooddelivery.domain.DeliveryStarted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderManagement_table")
@Data
//<<< DDD / Aggregate Root
public class OrderManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long orderId;

    private Integer qty;

    private String address;

    private String status;

    private String foodname;

    private Long foodId;

    @PostPersist
    public void onPostPersist() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();

        Canceled canceled = new Canceled(this);
        canceled.publishAfterCommit();
    }

    public static OrderManagementRepository repository() {
        OrderManagementRepository orderManagementRepository = StoreApplication.applicationContext.getBean(
            OrderManagementRepository.class
        );
        return orderManagementRepository;
    }

    //<<< Clean Arch / Port Method
    public static void receivedOrderInformation(
        PaymentApproved paymentApproved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        OrderManagement orderManagement = new OrderManagement();
        repository().save(orderManagement);

        DeliveryStarted deliveryStarted = new DeliveryStarted(orderManagement);
        deliveryStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(orderManagement->{
            
            orderManagement // do something
            repository().save(orderManagement);

            DeliveryStarted deliveryStarted = new DeliveryStarted(orderManagement);
            deliveryStarted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void receievcedCancleOrderInformation(
        PaymentCanceled paymentCanceled
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        OrderManagement orderManagement = new OrderManagement();
        repository().save(orderManagement);

        Canceled canceled = new Canceled(orderManagement);
        canceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(orderManagement->{
            
            orderManagement // do something
            repository().save(orderManagement);

            Canceled canceled = new Canceled(orderManagement);
            canceled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
