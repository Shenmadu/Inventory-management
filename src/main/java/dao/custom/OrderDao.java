package dao.custom;

import dao.CrudDao;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrderDto> {

    OrderDto searchOrder(String orderId) throws SQLException, ClassNotFoundException;
    Orders getLast()throws SQLException, ClassNotFoundException;

}
