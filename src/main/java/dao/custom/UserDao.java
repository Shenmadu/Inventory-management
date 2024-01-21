package dao.custom;

import dao.CrudDao;
import dto.ItemDto;
import dto.UserDto;
import entity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDao<User> {
    UserDto getUser(String email) throws SQLException, ClassNotFoundException;
}
