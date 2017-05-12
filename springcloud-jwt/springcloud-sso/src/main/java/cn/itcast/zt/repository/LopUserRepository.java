package cn.itcast.zt.repository;

import cn.itcast.zt.domain.LopUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户操作
 * Created by zhangtian on 2017/5/12.
 */
public interface LopUserRepository extends CrudRepository<LopUser, String> {
    /**
     * 通过username查找用户信息
     **/
    LopUser findByUsername(String username);

    List<LopUser> findAll();

    /**
     * 更新激活码
     * @param username
     * @return
     */
    @Transactional
    @Modifying
    @Query("update LopUser u set u.activeCode = ?1 where u.username = ?2")
    int updateActiveCodeByUsername(String activeCode,String username);

    /**
     * 更新激活状态
     */
    @Transactional
    @Modifying
    @Query("update LopUser u set u.enabled = ?1 where u.username = ?2")
    int updateUserEnabledByUsername(boolean enabledFlag,String username);
}
