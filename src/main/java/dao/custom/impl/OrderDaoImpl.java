package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import dto.Orders;
import entity.CustomerEntity;
import entity.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean save(Orders dto) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        OrdersEntity entity = new OrdersEntity(
                dto.getOrderId(),
                dto.getItem(),
                dto.getCatagory(),
                dto.getDate(),
                dto.getDescription(),
                dto.getStatus()

        );
        entity.setCustomer(session.find(CustomerEntity.class,dto.getCustomerId()));
        session.save(entity);
        transaction.commit();
        session.close();

        return true;

    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
