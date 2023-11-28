package com.code.orishop.controller.rest.apiAdmin;

import com.code.orishop.model.request.OrderRes;
import com.code.orishop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/order")
public class OrderRest {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderRes orderRes){
        try {
            orderService.create(orderRes);
            return ResponseEntity.ok(new  Exception("success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/update")
    public ResponseEntity<?> create(@RequestBody OrderRes orderRes,
                                    @RequestParam("id") Long id){
        try {
            orderService.update(orderRes,id);
            return ResponseEntity.ok(new  Exception("success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/revenue")
    public ResponseEntity<?> getRevenue(@RequestParam(value = "year",defaultValue = "2023") int year){
        try {
            return ResponseEntity.ok(orderService.getRevenueByMonth(year));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
