package dao.custom;

import dao.CrudDao;
import entity.Customer;
import entity.Orders;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer> {
    Customer getLast()throws SQLException, ClassNotFoundException;
}
