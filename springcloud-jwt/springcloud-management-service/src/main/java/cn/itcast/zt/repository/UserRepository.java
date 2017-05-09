package cn.itcast.zt.repository;

import cn.itcast.zt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 操作User
 * Created by Administrator on 2017/5/9/009.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmail(String email);
}
