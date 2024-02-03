package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Item {
    @Id
    private String itemCode;
    private String catagory;
    private String name;

    @OneToMany(mappedBy = "item")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Item(String itemCode, String catagory, String name) {
        this.itemCode = itemCode;
        this.catagory = catagory;
        this.name = name;
    }
}
