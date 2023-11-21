package com.code.orishop.controller.rest.apiAdmin;

import com.code.orishop.model.request.UserReq;
import com.code.orishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user")
public class UserAdminRest {

    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@ModelAttribute UserReq userReq){
        try {
            return ResponseEntity.ok(userService.createUser(userReq));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetailById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(userService.getById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,@ModelAttribute UserReq userReq){
        try{
            userService.updateUser(userReq,id);
            System.out.println(1);
            return ResponseEntity.ok(userReq);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
