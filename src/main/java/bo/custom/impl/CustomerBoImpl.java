package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dto.CustomerDto;
import entity.Customer;
import entity.Orders;

import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao=new CustomerDaoImpl();
    @Override
    public Boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                dto.getCustomerId(),
                dto.getContactNumber(),
                dto.getCustomerName(),
                dto.getEmail()

        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        try {
            Customer dto = customerDao.getLast();
            if(dto!=null){
                String id=dto.getCustomerId();

                int num=Integer.parseInt(id.split("[S]")[1]);
                num++;
                return String.format("CUS%04d",num);
            }else{
                return "CUS0001";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
