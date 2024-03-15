package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.ItemDao;
import lk.earth.earthuniversity.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private ItemDao itemdao;

    @GetMapping(produces = "application/json")
//  @PreAuthorize("hasAuthority('item-select')")p
    public List<Item> get(@RequestParam HashMap<String, String> params) {

        List<Item> items = this.itemdao.findAll();

        if(params.isEmpty())  return items;

        String itemname = params.get("itemname");
        String itemstatusid= params.get("itemstatusid");
        String categoryid= params.get("categoryid");
       
        Stream<Item> istream = items.stream();

        if(itemname!=null)istream = istream.filter(i->i.getName().contains(itemname));
        if(itemstatusid!=null)istream = istream.filter(i->i.getItemstatus().getId()==Integer.parseInt(itemstatusid));
        if(categoryid!=null)istream = istream.filter(i->i.getSubcategory().getCategory().getId()==Integer.parseInt(categoryid));

        return istream.collect(Collectors.toList());

    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//  @PreAuthorize("hasAuthority('Item-Insert')")
    public HashMap<String,String> add(@RequestBody Item item){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(itemdao.findByItemCode(item.getCode())!=null)
            errors = errors+"<br> Existing Code";
        if(itemdao.findByItemName(item.getName())!=null)
            errors = errors+"<br> Existing Name";

        if(errors.isEmpty())
        itemdao.save(item);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(item.getId()));
        responce.put("url","/items/"+item.getId());
        responce.put("errors",errors);

        return responce;
    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
//  @PreAuthorize("hasAuthority('Item-Update')")
    public HashMap<String,String> update(@RequestBody Item item){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Item itm1 = itemdao.findByItemCode(item.getCode());
        Item itm2 = itemdao.findByItemName(item.getName());

        if(itm1!=null && !Objects.equals(item.getId(), itm1.getId()))
            errors = errors+"<br> Existing Code";
        if(itm2!=null && !Objects.equals(item.getId(), itm2.getId()))
            errors = errors+"<br> Existing Name";

        if(errors.isEmpty()) itemdao.save(item);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(item.getId()));
        responce.put("url","/items/"+item.getId());
        responce.put("errors",errors);

        return responce;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Item itm = itemdao.findByMyId(id);

        if(itm==null)
            errors = errors+"<br> Item Does Not Existed";

        if(errors.isEmpty()) itemdao.delete(itm);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/items/"+id);
        responce.put("errors",errors);

        return responce;
    }

}




