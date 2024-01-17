package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private String orderId;
    private String customerId;
    private String item;
    private String catagory;
    private String date;
    private String description;
    private String status;


}
