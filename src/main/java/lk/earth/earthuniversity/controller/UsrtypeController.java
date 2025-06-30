package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.UsrtypeDao;
import lk.earth.earthuniversity.model.entity.Usestatus;
import lk.earth.earthuniversity.model.entity.Usetype;
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
@RequestMapping(value = "/usrtypes")
public class UsrtypeController {

    @Autowired
    private UsrtypeDao usrtypeDao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Usetype>>> get() {

        List<Usetype> usrtypes = this.usrtypeDao.findAll();

        usrtypes = usrtypes.stream().map(
                usrtype -> { Usetype d = new Usetype();
                    d.setId(usrtype.getId());
                    d.setName(usrtype.getName());
                    return d; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(usrtypes, usrtypes.size());

    }

}


