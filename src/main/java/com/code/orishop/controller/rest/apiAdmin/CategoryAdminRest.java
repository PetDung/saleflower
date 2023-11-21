package com.code.orishop.controller.rest.apiAdmin;

import com.code.orishop.model.request.CategoryReq;
import com.code.orishop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/category")
public class CategoryAdminRest {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestParam("name") String  name){
        try {
            return ResponseEntity.ok(categoryService.createCategory(name));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/update-category")
    public ResponseEntity<?> updateCategory(@ModelAttribute CategoryReq categoryReq){
        try {
            return ResponseEntity.ok(categoryService.update(categoryReq));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
