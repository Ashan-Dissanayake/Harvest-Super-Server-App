package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.ItemstatusDao;
import lk.earth.earthuniversity.model.entity.Itemstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemstatusService {

    @Autowired
    private ItemstatusDao itemstatusdao;

    public List<Itemstatus> getItemstatuses() {

        List<Itemstatus> itemstatuses = this.itemstatusdao.findAll();

        return itemstatuses.stream().map(
                itemstatus -> {
                    Itemstatus d = new Itemstatus();
                    d.setId(itemstatus.getId());
                    d.setName(itemstatus.getName());
                    return d;
                }
        ).collect(Collectors.toList());
    }


}
