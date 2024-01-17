package bo.custom;

import bo.SuperBo;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    Boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrderDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    List<OrderDto> allOrders() throws SQLException, ClassNotFoundException;

}
