package bo.custom.impl;

import bo.custom.OrderBo;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.ItemDto;
import dto.OrderDto;
import dto.UserDto;
import entity.Item;
import entity.Orders;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
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
//            OrderDto dto = orderDao.lastOrder();
            Orders dto = orderDao.getLast();
            if(dto!=null){
                String id=dto.getOrderId();

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

    @Override
    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
        return orderDao.delete(code);
    }

    @Override
    public boolean updateStatus(String orderId, String status) {
        return orderDao.updateStatus(orderId,status);
    }

    @Override
    public List<OrderDto> getOrdersByStatus(String status) {
        List<Orders> entityList=orderDao.getOrdersByStatus(status);
        List<OrderDto> list=new ArrayList<>();
        for (Orders orders:entityList) {
            list.add(new OrderDto(
                    orders.getOrderId(),
                    orders.getItem(),
                    orders.getCatagory(),
                    orders.getDate(),
                    orders.getDescription(),
                    orders.getStatus(),
                   null,
                    null
            ));
        }
        return list;
    }

    @Override
    public List<OrderDto> getAllOrder() {
        List<Orders> ordersList = orderDao.getAllOrder();
        List<OrderDto> list = new ArrayList<>();
        if(ordersList!=null){
            for(Orders orders:ordersList){
                list.add(
                        new OrderDto(
                                orders.getOrderId(),
                                orders.getItem(),
                                orders.getCatagory(),
                                orders.getDate(),
                                orders.getDescription(),
                                orders.getStatus(),
                                orders.getCustomer().getCustomerId(),
                                null
                        )
                );
            }
            return list;
        }
        return null;
    }

    @Override
    public OrderDto searchOrder(String orderId) throws SQLException, ClassNotFoundException {
        Orders orders = orderDao.searchOrder(orderId);
        if(orders!=null){
            return new OrderDto(
                    orders.getOrderId(),
                    orders.getItem(),
                    orders.getCatagory(),
                    orders.getDate(),
                    orders.getDescription(),
                    orders.getStatus(),
                    null,
                    null
            );
        }
        return null;
    }
}
