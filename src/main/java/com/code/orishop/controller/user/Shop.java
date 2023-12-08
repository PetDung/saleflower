package com.code.orishop.controller.user;


import com.code.orishop.model.entity.ProductEntity;
import com.code.orishop.model.respone.ProductRes;
import com.code.orishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/shop")
public class Shop {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "0") int page,
                        @RequestParam(value = "size",defaultValue = "12") int size) {
        if(page < 0) page = 0;
        Page<ProductEntity> productEntityPage = productService.getProductResListByPage(page, size);
        model.addAttribute("page","shop.jsp");
        model.addAttribute("totalPage", productEntityPage.getTotalPages());
        model.addAttribute("current", productEntityPage.getNumber());
        model.addAttribute("size", size);
        model.addAttribute("products", productService.convert(productEntityPage.getContent()));
        return "/user/page";
    }

    @GetMapping("/detail")
    public String detail(Model model  ,@RequestParam("id") Long id){
        model.addAttribute("page","detail.jsp");
        model.addAttribute("product",new ProductRes(productService.getProductById(id)));
        model.addAttribute("products", productService.getAll());
        return "/user/page";
    }
}
