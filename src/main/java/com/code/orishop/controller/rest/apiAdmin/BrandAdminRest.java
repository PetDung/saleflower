package com.code.orishop.controller.rest.apiAdmin;

import com.code.orishop.model.request.BrandReq;
import com.code.orishop.model.request.CategoryReq;
import com.code.orishop.service.BrandService;
import com.code.orishop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/brand")
public class BrandAdminRest {

    @Autowired
    BrandService brandService;

    @PostMapping("/create-brand")
    public ResponseEntity<?> createBrand(@RequestParam("name") String  name){
        try {
            return ResponseEntity.ok(brandService.createBrand(name));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/update-brand")
    public ResponseEntity<?> updateBrand(@ModelAttribute BrandReq brandReq){
        try {
            System.out.println(brandReq.getName());
            return ResponseEntity.ok(brandService.update(brandReq));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
