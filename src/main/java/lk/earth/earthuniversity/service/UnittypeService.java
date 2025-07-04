package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.UnittypeDao;
import lk.earth.earthuniversity.model.entity.Unittype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnittypeService {

    @Autowired
    private UnittypeDao unittypedao;

    public List<Unittype> getUnittypes() {

        List<Unittype> unittypeies = this.unittypedao.findAll();

        return unittypeies.stream().map(
                unittypey -> {
                    Unittype d = new Unittype();
                    d.setId(unittypey.getId());
                    d.setName(unittypey.getName());
                    return d;
                }
        ).collect(Collectors.toList());
    }

}
