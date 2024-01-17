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
    private String item;
    private String catagory;
    private String date;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "contactNumber")
    private CustomerEntity customer;





    public OrdersEntity(String orderId,String item, String catagory, String date, String description, String status) {
        this.orderId = orderId;
        this.item = item;
        this.catagory = catagory;
        this.date = date;
        this.description = description;
        this.status = status;
    }
}
