package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.OrderDetailsDto;
import dto.OrderDto;
import entity.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Orders orders = new Orders(
                dto.getOrderId(),
                dto.getItem(),
                dto.getCatagory(),
                dto.getDate(),
                dto.getDescription(),
                dto.getStatus()
        );

        orders.setCustomer(session.find(Customer.class,dto.getCustomerId()));

        session.save(orders);

        List<OrderDetailsDto> list = dto.getList();
        for (OrderDetailsDto detailDto:list) {
            OrderDetails orderDetail = new OrderDetails(
                    new OrderDetailsKey(detailDto.getOrderId(),detailDto.getItemCode()),
                    orders,
                    session.find(Item.class, detailDto.getItemCode()),
                    detailDto.getPartsCost()

            );
            session.save(orderDetail);
        }

        transaction.commit();
        session.close();

        return true;

    }

    @Override
    public boolean update(OrderDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Orders.class,value));
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;

    }




//    @Override
//    public OrderDto searchOrder(String orderId) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT * FROM orders WHERE orderId = ?";
//
//
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, orderId);
//            try (ResultSet resultSet = pstm.executeQuery()) {
//                if (resultSet.next()) {
//                    return new OrderDto(
//                            resultSet.getString(1),
//                            resultSet.getString(2),
//                            resultSet.getString(3),
//                            resultSet.getString(4),
//                            resultSet.getString(5),
//                            resultSet.getString(6),
//                            resultSet.getString(7),
//                            null
//                    );
//                }
//            }
//        }
//
//        return null;
//    }

    @Override
    public Orders searchOrder(String orderId) throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getSession()) {
            Criteria criteria = session.createCriteria(Orders.class);
            criteria.add(Restrictions.eq("orderId", orderId));
            return (Orders) ((Criteria) criteria).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orders getLast() throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();


            String hql = "FROM Orders ORDER BY id DESC";
            Query<Orders> query = session.createQuery(hql, Orders.class);
            query.setMaxResults(1);

            List<Orders> orders = query.list();
            transaction.commit();


            return !orders.isEmpty() ? new Orders(
                    orders.get(0).getOrderId(),
                    orders.get(0).getItem(),
                    orders.get(0).getCatagory(),
                    orders.get(0).getDate(),
                    orders.get(0).getDescription(),
                    orders.get(0).getStatus()
            ) : null;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean updateStatus(String orderId, String status) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Orders order = session.get(Orders.class, orderId);

        if (order != null) {
            order.setStatus(status);
            session.update(order);
            transaction.commit();
            return true;
        } else {
            return false;
        }

    }
    public List<Orders> getOrdersByStatus(String status){
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();


            String hql = "FROM Orders WHERE status = :status";


            Query<Orders> query = session.createQuery(hql, Orders.class);
            query.setParameter("status", status);


            List<Orders> ordersList = query.list();
            return ordersList;

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Orders> getAllOrder() {
        try (Session session = HibernateUtil.getSession()){
            Query<Orders> query = session.createQuery("FROM Orders");
            List<Orders> list = query.list();
            return list;
        }catch (HibernateException e){
            return null;
        }
    }
}
