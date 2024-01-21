package bo.custom.impl;

import bo.custom.UserBo;
import dao.custom.UserDao;
import dao.custom.impl.UserDaoImpl;
import dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao=new UserDaoImpl();

    @Override
    public Boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<UserDto> allUsers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserDto getUser(String email) throws SQLException, ClassNotFoundException {

        return userDao.getUser(email);
    }
}

