package dao.custom;

import dto.OrderDetailsDto;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao {
    boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException;
     boolean updatePartCost(String orderId, String itemCode, double price) throws SQLException, ClassNotFoundException;
    OrderDetailsDto getOrder(String orderId, String itemCode) throws SQLException, ClassNotFoundException;

}
