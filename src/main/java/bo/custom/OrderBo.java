package bo.custom;

import bo.SuperBo;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    Boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;
    boolean updateStatus(String orderId, String status);

    List<OrderDto> getOrdersByStatus(String status);



}
