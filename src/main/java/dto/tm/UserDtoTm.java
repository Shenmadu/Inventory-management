package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDtoTm extends RecursiveTreeObject<UserDtoTm> {
    private String email;
    private String password;
    private String type;
    private Button btn;

    public UserDtoTm(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
