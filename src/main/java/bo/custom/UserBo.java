package bo.custom;

import bo.SuperBo;

import dto.UserDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo {
    Boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    List<UserDto> allUsers() throws SQLException, ClassNotFoundException;
    UserDto getUser(String email) throws SQLException, ClassNotFoundException;
    ObservableList<String> getUserType();
    boolean updatePassword(String email, String password)throws SQLException, ClassNotFoundException;
    UserDto searchUser(String email);


}
