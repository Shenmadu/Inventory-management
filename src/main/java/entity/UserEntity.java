package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String userId;
    private String type;
    private String email;
    private String password;

}
