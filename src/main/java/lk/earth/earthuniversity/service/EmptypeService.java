package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.EmptypeDao;
import lk.earth.earthuniversity.model.entity.Emptype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmptypeService {

    @Autowired private EmptypeDao emptypedao;

    public List<Emptype> getEmptype(){

        List<Emptype> emptypees = this.emptypedao.findAll();

       return emptypees.stream().map(
                emptype -> {
                    Emptype d = new Emptype();
                    d.setId(emptype.getId());
                    d.setName(emptype.getName());
                    return d;
                }
        ).collect(Collectors.toList());

    }


}
