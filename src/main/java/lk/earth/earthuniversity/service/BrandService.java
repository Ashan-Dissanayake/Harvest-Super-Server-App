package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.BrandDao;
import lk.earth.earthuniversity.model.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandDao branddao;

    public List<Brand> getBrands() {

        List<Brand> brandes = this.branddao.findAll();

        return brandes.stream().map(
                brand -> {
                    Brand d = new Brand();
                    d.setId(brand.getId());
                    d.setName(brand.getName());
                    return d;
                }
        ).collect(Collectors.toList());
    }


}
