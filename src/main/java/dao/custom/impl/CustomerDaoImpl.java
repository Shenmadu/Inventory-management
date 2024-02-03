package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.HibernateUtil;
import entity.Customer;
import entity.Orders;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Customer getLast() throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();


            String hql = "FROM Customer ORDER BY id DESC";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setMaxResults(1);

            List<Customer> customers = query.list();
            transaction.commit();


            return !customers.isEmpty() ? new Customer(
                    customers.get(0).getCustomerId(),
                    customers.get(0).getContactNumber(),
                    customers.get(0).getCustomerName(),
                    customers.get(0).getEmail()
            ) :null;

        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
