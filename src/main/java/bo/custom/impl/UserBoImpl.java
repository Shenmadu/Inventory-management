package bo.custom.impl;

import bo.custom.UserBo;
import dto.User;

import java.sql.SQLException;
import java.util.List;

public class UserBoImpl implements UserBo {

    @Override
    public Boolean saveUser(User dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateUser(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> allUsers() throws SQLException, ClassNotFoundException {
        return null;
    }
}

