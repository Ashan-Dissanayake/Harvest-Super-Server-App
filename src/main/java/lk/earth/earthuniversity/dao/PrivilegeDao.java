package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.model.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PrivilegeDao extends JpaRepository<Privilege,Integer> {

    @Query("select e from Privilege e where e.id = :id")
    Privilege findByMyId(@Param("id") Integer id);

    Privilege findByRoleIdAndModuleIdAndOperationId(Integer rid,Integer mid,Integer oid);

}
