package dao.custom.impl;

import dao.custom.ItemDao;
import dao.util.HibernateUtil;
import entity.Item;
import entity.Orders;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getSession()){
            Query<Item> query = session.createQuery("FROM Item");
            List<Item> items = query.list();
            return items;
        }catch (HibernateException e){
            return null;
        }
    }

    @Override
    public List<Item> getAllByCategory(String category) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item WHERE catagory = :category", Item.class);
        query.setParameter("category", category);
        List<Item> list = query.list();
        session.close();
        return list;


    }
}
