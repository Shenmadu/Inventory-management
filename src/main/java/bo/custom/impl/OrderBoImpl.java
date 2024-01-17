package bo.custom.impl;

import bo.custom.OrderBo;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.Orders;
import entity.OrdersEntity;

import javax.persistence.criteria.Order;
import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    private OrderDao orderDao=new OrderDaoImpl();
    @Override
    public Boolean saveOrder(Orders dto) throws SQLException, ClassNotFoundException {
        return orderDao.save(new OrdersEntity(
                dto.getOrderId(),
                dto.getItem(),
                dto.getCustomerId(),
                dto.getCatagory(),
                dto.getDate(),
                dto.getDescription(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean updateOrder(Orders dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Orders> allOrders() throws SQLException, ClassNotFoundException {
        return null;
    }
}
