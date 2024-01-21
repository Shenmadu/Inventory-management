package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import dto.OrderDetailsDto;
import dto.OrderDto;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
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
        return false;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {

        return null;
    }
}
