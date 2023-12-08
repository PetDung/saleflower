package com.code.orishop.controller.rest.apiAdmin;

import com.code.orishop.model.request.ProductReq;
import com.code.orishop.model.respone.ProductRes;
import com.code.orishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/admin/product")
public class ProductAdminRest {
    @Autowired
    ProductService productService;

    @PostMapping("/create-product")
    public ResponseEntity<?> postProduct(@ModelAttribute ProductReq productReq){
        try {
            System.out.println(productReq.getDescription());
            return ResponseEntity.ok(productService.createProduct(productReq));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/update-product")
    public ResponseEntity<?> updateProduct(@ModelAttribute ProductReq productReq,
                                           @RequestParam(value = "id",defaultValue = "0") Long id){
        try {
            return ResponseEntity.ok(productService.updateProduct(productReq,id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "name") String name){
        try {
            return ResponseEntity.ok(productService.search(name));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}