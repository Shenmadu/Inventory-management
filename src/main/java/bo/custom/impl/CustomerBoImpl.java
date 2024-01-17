package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dto.Customer;
import entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao=new CustomerDaoImpl();
    @Override
    public Boolean saveCustomer(Customer dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new CustomerEntity(
                dto.getContactNumber(),
                dto.getCustomerName(),
                dto.getEmail()

        ));
    }

    @Override
    public boolean updateCustomer(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Customer> allCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }
}
