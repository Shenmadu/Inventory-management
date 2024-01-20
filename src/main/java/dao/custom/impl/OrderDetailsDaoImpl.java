package dao.custom.impl;

import dao.custom.OrderDetailsDao;
import dao.util.HibernateUtil;
import dto.OrderDetailsDto;
import entity.Item;

import entity.OrderDetails;
import entity.OrderDetailsKey;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
                    dto.getPartsCost(),
                    dto.getStatus()
            );

            session.save(orderDetail);
        }

        transaction.commit();

        return isDetailsSaved;

    }
}
