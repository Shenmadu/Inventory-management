package bo.custom;

import bo.SuperBo;
import dto.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {
    Boolean saveItem(Item dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(Item dto) throws SQLException, ClassNotFoundException;
    boolean deletItem(String id) throws SQLException, ClassNotFoundException;
    List<Item > allItems() throws SQLException, ClassNotFoundException;
}
