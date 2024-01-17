package bo.custom;

import bo.SuperBo;
import dto.Orders;
import dto.User;

import javax.persistence.criteria.Order;
import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    Boolean saveOrder(Orders dto) throws SQLException, ClassNotFoundException;
    boolean updateOrder(Orders dto) throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    List<Orders> allOrders() throws SQLException, ClassNotFoundException;

}
