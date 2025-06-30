package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.ItemDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Employee;
import lk.earth.earthuniversity.model.entity.Item;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired private ItemDao itemdao;

    @GetMapping(produces = "application/json")
//  @PreAuthorize("hasAuthority('item-select')")
    public ResponseEntity<APISuccessResponse<List<Item>>> get(@RequestParam HashMap<String, String> params) {

        List<Item> items = this.itemdao.findAll();

        if(params.isEmpty())  return APIResponseBuilder.getResponse(items, items.size());

        String itemname = params.get("itemname");
        String itemstatusid= params.get("itemstatusid");
        String categoryid= params.get("categoryid");
       
        Stream<Item> istream = items.stream();

        if(itemname!=null)istream = istream.filter(i->i.getName().contains(itemname));
        if(itemstatusid!=null)istream = istream.filter(i->i.getItemstatus().getId()==Integer.parseInt(itemstatusid));
        if(categoryid!=null)istream = istream.filter(i->i.getSubcategory().getCategory().getId()==Integer.parseInt(categoryid));

        items = istream.collect(Collectors.toList());

        return APIResponseBuilder.getResponse(items, items.size());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//  @PreAuthorize("hasAuthority('Item-Insert')")
    public ResponseEntity<APISuccessResponse<Item>> add(@RequestBody Item item){

        if(itemdao.findByItemCode(item.getCode())!=null)
            throw new ResourceExistsException("Item already exists with this Code: " + item.getCode());
        if(itemdao.findByItemName(item.getName())!=null)
            throw new ResourceExistsException("Item already exists with this Name: " + item.getName());

        Item savedItem = itemdao.save(item);

        return APIResponseBuilder.postResponse(savedItem,savedItem.getId());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
//  @PreAuthorize("hasAuthority('Item-Update')")
    public ResponseEntity<APISuccessResponse<Item>> update(@RequestBody Item item){

        Item itm1 = itemdao.findByItemCode(item.getCode());
        Item itm2 = itemdao.findByItemName(item.getName());

        if(itm1!=null && !Objects.equals(item.getId(), itm1.getId()))
            throw new ResourceExistsException("Item already exists with this Code: " + item.getCode());
        if(itm2!=null && !Objects.equals(item.getId(), itm2.getId()))
            throw new ResourceExistsException("Item already exists with this Name: " + item.getName());

       Item updatedItem = itemdao.save(item);

       return APIResponseBuilder.putResponse(updatedItem,updatedItem.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<APISuccessResponse<Item>> delete(@PathVariable Integer id){

        Item itm = itemdao.findByMyId(id);

        if(itm==null)
            throw new ResourceNotFoundException("Item not exists with this id: " + id);

        itemdao.delete(itm);

        return APIResponseBuilder.deleteResponse(id);

    }

}




