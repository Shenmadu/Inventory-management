package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    private String itemCode;
    private String catagory;
    private String name;


}
