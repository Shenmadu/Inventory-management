package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private String customerId;
    private String item;
    private String catagory;
    private String date;
    private String description;
    private String status;
//    private List<OrderDetailsDto> list;


}
