package dao.custom.impl;

import dao.custom.OrderDetailsDao;
import dao.util.HibernateUtil;
import dto.OrderDetailsDto;
import dto.UserDto;
import entity.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException {
        boolean isDetailsSaved=true;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (OrderDetailsDto dto : list) {
            Orders orderid = session.get(Orders.class, dto.getOrderId());
            Item itemcode = session.get(Item.class, dto.getItemCode());


            OrderDetails orderDetail = new OrderDetails(
                    new OrderDetailsKey(dto.getOrderId(), dto.getItemCode()),
                    orderid,
                    itemcode,
                    dto.getPartsCost()

            );

            session.save(orderDetail);
        }

        transaction.commit();

        return isDetailsSaved;

    }
    public boolean updatePartCost(String orderId, String itemCode, double price) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();


        OrderDetailsKey orderDetailsKey = new OrderDetailsKey(orderId, itemCode);

        OrderDetails orderDetails = session.get(OrderDetails.class, orderDetailsKey);

        if (orderDetails != null) {
            orderDetails.setPartsCost(price);
            session.update(orderDetails);
            transaction.commit();
            return true;
        } else {
            return false;
        }
    }
//    public OrderDetails getPartCost(String orderId, String itemCode) {
//        try (Session session = HibernateUtil.getSession()) {
//            Query<OrderDetails> query = session.createQuery("FROM OrderDetails WHERE id.orderId = :orderId AND id.itemCode = :itemCode", OrderDetails.class);
//            query.setParameter("orderId", orderId);
//            query.setParameter("itemCode", itemCode);
//
//            OrderDetails orderDetails = query.uniqueResult();
//
//            if (orderDetails != null) {
//                return new OrderDetails(
//                        orderDetails.getPartsCost()
//                );
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
public OrderDetailsDto getOrder(String orderId, String itemCode) {
    try (Session session = HibernateUtil.getSession()) {
        Query<OrderDetails> query = session.createQuery("FROM OrderDetails WHERE id.orderId = :orderId AND id.itemCode = :itemCode", OrderDetails.class);
        query.setParameter("orderId", orderId);
        query.setParameter("itemCode", itemCode);

        OrderDetails orderDetails = query.uniqueResult();

        if (orderDetails != null) {
            return new OrderDetailsDto(
                    orderDetails.getPartsCost()
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}



}
