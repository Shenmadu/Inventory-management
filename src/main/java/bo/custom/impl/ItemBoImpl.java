package bo.custom.impl;

import bo.custom.ItemBo;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    private ItemDao itemDao=new ItemDaoImpl();

    @Override
    public Boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ItemDto> getAllByCategory(String category) throws SQLException, ClassNotFoundException {
        List<Item> entityList=itemDao.getAllByCategory(category);
        List<ItemDto> list=new ArrayList<>();
        for (Item item:entityList) {
            list.add(new ItemDto(
                    item.getItemCode(),
                    item.getCatagory(),
                    item.getName()
            ));
        }
        return list;
    }
}

