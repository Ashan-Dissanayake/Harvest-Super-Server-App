package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Item;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.ItemService;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(produces = "application/json")
    //  @PreAuthorize("hasAuthority('item-select')")
    public ResponseEntity<APISuccessResponse<List<Item>>> get(@RequestParam HashMap<String, String> params) {
        List<Item> items = this.itemService.getItems(params);
        return APIResponseBuilder.getResponse(items, items.size());
    }

    @GetMapping(path = "/list", produces = "application/json")
    //  @PreAuthorize("hasAuthority('item-select-list')")
    public ResponseEntity<APISuccessResponse<List<Item>>> get() {
        List<Item> items = this.itemService.getItemsLsit();
        return APIResponseBuilder.getResponse(items, items.size());
    }

    @PostMapping
    //  @PreAuthorize("hasAuthority('Item-Insert')")
    public ResponseEntity<APISuccessResponse<Item>> add(@RequestBody Item item) {
        Item savedItem = itemService.createItem(item);
        return APIResponseBuilder.postResponse(savedItem, savedItem.getId());
    }

    @PutMapping
    //  @PreAuthorize("hasAuthority('Item-Update')")
    public ResponseEntity<APISuccessResponse<Item>> update(@RequestBody Item item) {
        Item updatedItem = itemService.updateItem(item);
        return APIResponseBuilder.putResponse(updatedItem, updatedItem.getId());
    }

    @DeleteMapping("/{id}")
    //  @PreAuthorize("hasAuthority('Item-Delete')")
    public ResponseEntity<APISuccessResponse<Item>> delete(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return APIResponseBuilder.deleteResponse(id);
    }

}




