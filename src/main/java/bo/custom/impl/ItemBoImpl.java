package bo.custom.impl;

import bo.custom.ItemBo;
import dto.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    @Override
    public Boolean saveItem(Item dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateItem(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Item> allItems() throws SQLException, ClassNotFoundException {
        return null;
    }
}
