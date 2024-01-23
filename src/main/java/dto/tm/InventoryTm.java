package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class InventoryTm extends RecursiveTreeObject<InventoryTm> {
    private String itemCode;
    private String status;
    private String category;
    private String date;
    private Button btn;

    public InventoryTm(String itemCode, String status, String category, String date) {
        this.itemCode = itemCode;
        this.status = status;
        this.category = category;
        this.date = date;
    }
}
