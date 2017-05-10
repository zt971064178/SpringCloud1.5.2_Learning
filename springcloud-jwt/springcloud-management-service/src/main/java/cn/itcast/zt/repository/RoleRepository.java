package cn.itcast.zt.repository;

import cn.itcast.zt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 操作Role
 * Created by Administrator on 2017/5/9/009.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
