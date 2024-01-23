package dao.custom;

import dao.CrudDao;
import dto.OrderDto;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrderDto> {
    OrderDto lastOrder() throws SQLException, ClassNotFoundException;
    OrderDto searchOrder(String orderId) throws SQLException, ClassNotFoundException;

}
