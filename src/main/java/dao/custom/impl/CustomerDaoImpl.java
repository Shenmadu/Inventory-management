package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.HibernateUtil;
import entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(CustomerEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<CustomerEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
