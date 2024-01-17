package bo.custom;

import bo.SuperBo;

import dto.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo {
    Boolean saveUser(User dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(User dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    List<User > allUsers() throws SQLException, ClassNotFoundException;
}
