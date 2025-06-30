package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.UnittypeDao;
import lk.earth.earthuniversity.model.entity.Unittype;
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
@RequestMapping(value = "/unittypes")
public class UnittypeController {

    @Autowired private UnittypeDao unittypedao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Unittype>>> get() {

        List<Unittype> unittypes = this.unittypedao.findAll();

        unittypes = unittypes.stream().map(
                unittype -> { Unittype i = new Unittype();
                    i.setId(unittype.getId());
                    i.setName(unittype.getName());
                    return i; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(unittypes, unittypes.size());

    }

}


