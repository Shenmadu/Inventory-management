package dao.custom;

import dao.CrudDao;
import entity.Item;

import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    public List<Item> getAllByCategory(String category);
}
