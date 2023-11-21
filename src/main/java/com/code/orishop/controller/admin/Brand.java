package com.code.orishop.controller.admin;

import com.code.orishop.service.BrandService;
import com.code.orishop.service.CategoryService;
import com.code.orishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/brand")
public class Brand {
    @Autowired
    BrandService brandService;
    @Autowired
    ProductService productService;

    @GetMapping("")
    public String getCategories(Model model) {
        model.addAttribute("title", "Admin Brands");
        model.addAttribute("page", "brand.jsp");
        model.addAttribute("brands",brandService.getAll());
        return "/admin/index";
    }
    @GetMapping("/detail")
    public String getCategory(Model model, @RequestParam(value = "id",defaultValue = "0") Long id){
        model.addAttribute("title", "Admin Brands");
        model.addAttribute("page", "brand-detail.jsp");
        try {
            model.addAttribute("b",brandService.getById(id));
            model.addAttribute("productListNotBrand",productService.getProductsNotBrand(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/index";
    }
}
