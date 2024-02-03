package entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderDetails {

    @EmbeddedId
    private OrderDetailsKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    Orders orders;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "itemCode")
    Item item;



    private double partsCost;



}
