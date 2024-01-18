package dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto extends RecursiveTreeObject<ItemDto> {
    private String itemCode;
    private String catagory;
    private String name;


}
