package cn.itcast.zt;

import cn.itcast.zt.dto.RoleDTO;
import cn.itcast.zt.dto.UserDTO;
import cn.itcast.zt.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 初始化数据
 * Created by Administrator on 2017/5/9/009.
 */
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserManagementService service;

    private Collection<String> roleDTOsToIdCollection(RoleDTO... roles) {
        Collection<String> idCollection = new ArrayList<>();
        for (RoleDTO role : roles) {
            idCollection.add(role.getId());
        }
        return idCollection;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Create Roles
        RoleDTO adminRole = service.createRole(new RoleDTO("admin"));
        RoleDTO managerRole = service.createRole(new RoleDTO("manager"));

        // Create Users
        UserDTO userA = service.createUser(new UserDTO("a@alpha.org", "password-a", roleDTOsToIdCollection(adminRole)));
        UserDTO userB = service
                .createUser(new UserDTO("b@alpha.org", "password-b", roleDTOsToIdCollection(managerRole)));
        UserDTO userC = service
                .createUser(new UserDTO("c@alpha.org", "password-c", roleDTOsToIdCollection(adminRole, managerRole)));

        service.findAllRoles().forEach(r -> System.out.println(r));
        service.findAllUsers().forEach(u -> System.out.println(u));
    }
}
