package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.EmpstatusDao;
import lk.earth.earthuniversity.model.entity.Empstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpstatusService {

    @Autowired private EmpstatusDao empstatusdao;

    public List<Empstatus> getEmpStatus(){

        List<Empstatus> empstatuses = this.empstatusdao.findAll();

       return empstatuses.stream().map(
                empstatus -> {
                    Empstatus d = new Empstatus();
                    d.setId(empstatus.getId());
                    d.setName(empstatus.getName());
                    return d;
                }
        ).collect(Collectors.toList());

    }


}
