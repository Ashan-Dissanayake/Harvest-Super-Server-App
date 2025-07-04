package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.ItemDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public List<Item> getItems(HashMap<String, String> params) {

        List<Item> items = itemDao.findAll();

        if (params.isEmpty()) return items;

        String itemname = params.get("itemname");
        String itemstatusid= params.get("itemstatusid");
        String categoryid= params.get("categoryid");

        Stream<Item> istream = items.stream();

        if(itemname!=null)istream = istream.filter(i->i.getName().contains(itemname));
        if(itemstatusid!=null)istream = istream.filter(i->i.getItemstatus().getId()==Integer.parseInt(itemstatusid));
        if(categoryid!=null)istream = istream.filter(i->i.getSubcategory().getCategory().getId()==Integer.parseInt(categoryid));

        return istream.collect(Collectors.toList());

    }

    public List<Item> getItemsLsit() {
        return itemDao.findAllNameId().stream()
                .map(itm -> new Item(itm.getId(), itm.getName()))
                .collect(Collectors.toList());
    }

    public Item createItem(Item item) {
        validateNewItem(item);
        return itemDao.save(item);
    }

    public Item updateItem(Item item) {
        validateItemUpdate(item);
        return itemDao.save(item);
    }

    public void deleteItem(Integer id) {
        Item item = itemDao.findByMyId(id);
        if (item == null) {
            throw new ResourceNotFoundException("Item not exists with this id: " + id);
        }
        itemDao.delete(item);
    }

    private void validateNewItem(Item item) {
        if(itemDao.findByItemCode(item.getCode())!=null)
            throw new ResourceExistsException("Item already exists with this Code: " + item.getCode());
        if(itemDao.findByItemName(item.getName())!=null)
            throw new ResourceExistsException("Item already exists with this Name: " + item.getName());
    }

    private void validateItemUpdate(Item item) {
        Item itm1 = itemDao.findByItemCode(item.getCode());
        Item itm2 = itemDao.findByItemName(item.getName());

        if(itm1!=null && !Objects.equals(item.getId(), itm1.getId()))
            throw new ResourceExistsException("Item already exists with this Code: " + item.getCode());
        if(itm2!=null && !Objects.equals(item.getId(), itm2.getId()))
            throw new ResourceExistsException("Item already exists with this Name: " + item.getName());
    }

}
