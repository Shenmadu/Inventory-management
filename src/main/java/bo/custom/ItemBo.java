package bo.custom;

import bo.SuperBo;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {
    Boolean saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean deletItem(String id) throws SQLException, ClassNotFoundException;
    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
    List<ItemDto> getAllByCategory(String category)throws SQLException, ClassNotFoundException;
}
