package bo.custom;

import bo.SuperBo;
import dto.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    Boolean saveCustomer(Customer dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(Customer dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    List<Customer> allCustomers() throws SQLException, ClassNotFoundException;
}
