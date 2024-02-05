package bo.custom.impl;

import bo.custom.UserBo;
import dao.custom.UserDao;
import dao.custom.impl.UserDaoImpl;
import dto.UserDto;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao=new UserDaoImpl();

    @Override
    public Boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        userDao.save(new User(
                dto.getEmail(),
                dto.getPassword(),
                dto.getType()
        ));
        return true;
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDao.delete(id);
    }

    @Override
    public List<UserDto> allUsers() throws SQLException, ClassNotFoundException {
        List<User> usersList = userDao.getAll();
        List<UserDto> list = new ArrayList<>();
        if(usersList!=null){
            for(User users:usersList){
                list.add(
                        new UserDto(
                                users.getEmail(),
                                users.getPassword(),
                                users.getType()
                        )
                );
            }
            return list;
        }
        return null;
    }

    @Override
    public UserDto getUser(String email) throws SQLException, ClassNotFoundException {
        return userDao.getUser(email);
    }
    public ObservableList<String> getUserType() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Admin");
        list.add("User");
        return list;
    }

    @Override
    public boolean updatePassword(String email, String password) throws SQLException, ClassNotFoundException {
        return userDao.updatePassword(email,password);
    }

    @Override
    public UserDto searchUser(String email){
        User user = userDao.searchUser(email);
        if(user!=null){
        return new UserDto(
                user.getEmail(),
                user.getPassword(),
                user.getType()
        );
        }
        return null;
    }
}

