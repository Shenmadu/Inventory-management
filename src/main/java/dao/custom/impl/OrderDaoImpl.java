package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.OrderDetailsDto;
import dto.OrderDto;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
//
//        List<OrderDetailsDto> list = dto.getList();
//        for (OrderDetailsDto detailDto:list) {
//            OrderDetails orderDetail = new OrderDetails(
//                    new OrderDetailsKey(detailDto.getOrderId(), detailDto.getItemCode()),
//                    orders,
//                    session.find(Item.class, detailDto.getItemCode()),
//                    detailDto.getPartsCost(),
//                    detailDto.getStatus()
//            );
//            session.save(orderDetail);
//        }

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
        String sql="SELECT * FROM orders";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        List<OrderDto>list=new ArrayList<>();

        while (result.next()){
            list.add(new OrderDto(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    null
            ));
        }
        return list;

    }

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    null
            );
        }
        return null;
    }

    @Override
    public OrderDto searchOrder(String orderId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders WHERE orderId = ?";


        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, orderId);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    return new OrderDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            null
                    );
                }
            }
        }

        return null;
    }
}
