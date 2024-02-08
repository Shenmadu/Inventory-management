package dao.custom;

import dao.CrudDao;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends CrudDao<OrderDto> {

    Orders searchOrder(String orderId) throws SQLException, ClassNotFoundException;
    Orders getLast()throws SQLException, ClassNotFoundException;
    boolean updateStatus(String orderId, String status);

    List<Orders> getOrdersByStatus(String status);
    List<Orders> getAllOrder();

}
