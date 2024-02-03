package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity

public class Customer {
    @Id
    private String contactNumber;
    private String customerName;
    private String email;


    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();


    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Customer(String contactNumber, String customerName, String email) {
        this.contactNumber = contactNumber;
        this.customerName = customerName;
        this.email = email;

    }
}
