package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.GenderDao;
import lk.earth.earthuniversity.model.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderService {

    @Autowired private GenderDao genderdao;

    public List<Gender> getGender(){

        List<Gender> genderes = this.genderdao.findAll();

       return genderes.stream().map(
                gender -> {
                    Gender d = new Gender();
                    d.setId(gender.getId());
                    d.setName(gender.getName());
                    return d;
                }
        ).collect(Collectors.toList());

    }


}
