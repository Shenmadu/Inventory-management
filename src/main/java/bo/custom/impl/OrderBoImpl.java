package bo.custom.impl;

import bo.custom.OrderBo;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    private OrderDao orderDao=new OrderDaoImpl();

    @Override
    public Boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.save(dto);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        try {
            OrderDto dto = orderDao.lastOrder();
            if(dto!=null){
                String id=dto.getOrderId();
                System.out.println(id);
                int num=Integer.parseInt(id.split("[R]")[1]);
                num++;
                return String.format("ODR%04d",num);
            }else{
                return "ODR0001";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
