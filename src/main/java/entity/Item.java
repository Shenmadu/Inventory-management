package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor

@Getter
@Setter
@ToString
@Entity
public class Item {
    @Id
    private String itemCode;
    private String catagory;
    private String name;

    public Item(String itemCode, String catagory, String name) {
        this.itemCode = itemCode;
        this.catagory = catagory;
        this.name = name;
    }
}
