package dao.custom.impl;

import dao.custom.UserDao;
import dao.util.HibernateUtil;
import dto.ItemDto;
import dto.UserDto;
import entity.Item;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserDto getUser(String email) {
        try (Session session = HibernateUtil.getSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);

            User user = query.uniqueResult();

            if (user != null) {
                return new UserDto(
                        user.getEmail(),
                        user.getPassword(),
                        user.getType()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
