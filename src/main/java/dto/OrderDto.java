package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private String orderId;
    private String item;
    private String catagory;
    private String date;
    private String description;
    private String status;
    private String contactNumber;
    private List<OrderDetailsDto> list;


}
