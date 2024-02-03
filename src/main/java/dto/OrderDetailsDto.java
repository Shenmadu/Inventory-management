package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetailsDto {
    private String orderId;
    private String itemCode;
    private double partsCost;




}
