package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.DesignationDao;
import lk.earth.earthuniversity.model.entity.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignationService {

    @Autowired private DesignationDao designationdao;

    public List<Designation> getDesignation(){

        List<Designation> designationes = this.designationdao.findAll();

       return designationes.stream().map(
                designation -> {
                    Designation d = new Designation();
                    d.setId(designation.getId());
                    d.setName(designation.getName());
                    return d;
                }
        ).collect(Collectors.toList());

    }


}
