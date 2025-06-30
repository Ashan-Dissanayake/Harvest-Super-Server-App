package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.ItemstatusDao;
import lk.earth.earthuniversity.model.entity.Itemstatus;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/itemstatuses")
public class ItemstatusController {

    @Autowired
    private ItemstatusDao itemstatusdao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Itemstatus>>> get() {

        List<Itemstatus> itemstatuses = this.itemstatusdao.findAll();

        itemstatuses = itemstatuses.stream().map(
                itemstatus -> { Itemstatus i = new Itemstatus();
                    i.setId(itemstatus.getId());
                    i.setName(itemstatus.getName());
                    return i; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(itemstatuses, itemstatuses.size());

    }

}


