package com.code.orishop.service;

import com.code.orishop.model.entity.RoleEntity;
import com.code.orishop.model.request.UserReq;
import com.code.orishop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Boot {

    @Autowired
    UserService userService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    RoleRepository roleRepository;

    public  void boot() throws Exception {
        List<RoleEntity> exits = roleRepository.findAllByRoleName("ADMIN");
        if(exits == null || exits.size() == 0){
            RoleEntity admin = new RoleEntity();
            admin.setRoleName("ADMIN");
            try {
                roleRepository.save(admin);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<RoleEntity> exits2 = roleRepository.findAllByRoleName("CUSTOMER");
        if(exits2  == null || exits2.size() == 0){
            RoleEntity customer = new RoleEntity();
            customer.setRoleName("CUSTOMER");
            try {
                roleRepository.save(customer);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            brandService.createBrand("default");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            categoryService.createCategory("default");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        UserReq defaultUser = new UserReq();
        defaultUser.setUserName("default");
        defaultUser.setRole(1L);
        try {
            userService.createUser(defaultUser);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        UserReq adminUser = new UserReq();
        adminUser.setEmail("admin@gmail.com");
        adminUser.setPassword("123");
        adminUser.setUserName("admin");
        adminUser.setRole(1L);
        try {
            userService.createUser(adminUser);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
