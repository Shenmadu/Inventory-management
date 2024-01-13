package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class CustomerEntity {
    @Id
    private String contactNumber;
    private String customerName;
    private String email;


    @OneToMany(mappedBy = "customer")
    private List<OrdersEntity> orders = new ArrayList<>();

    public CustomerEntity(String contactNumber, String customerName, String email) {
        this.contactNumber = contactNumber;
        this.customerName = customerName;
        this.email = email;

    }
}
