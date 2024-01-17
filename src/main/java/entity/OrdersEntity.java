package entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity


public class OrdersEntity{
    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;


    private String item;
    private String catagory;
    private String date;
    private String description;
    private String status;


    public OrdersEntity(String orderId, String customerId, String item, String catagory, String date, String description, String status) {
        this.orderId = orderId;
//        this.customer = new CustomerEntity(customerId);
        this.item = item;
        this.catagory = catagory;
        this.date = date;
        this.description = description;
        this.status = status;
    }
}
