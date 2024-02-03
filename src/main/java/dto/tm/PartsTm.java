package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartsTm extends RecursiveTreeObject<PartsTm> {
    private String partName;
    private int qty;
    private double amount;
}
