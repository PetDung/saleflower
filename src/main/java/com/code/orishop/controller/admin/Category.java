package com.code.orishop.controller.admin;

import com.code.orishop.model.entity.CategoryEntity;
import com.code.orishop.service.CategoryService;
import com.code.orishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class Category {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("")
    public String getCategories(Model model) {
        model.addAttribute("title", "Admin Categories");
        model.addAttribute("page", "category.jsp");
        model.addAttribute("categories",categoryService.getAll());
        return "/admin/index";
    }
    @GetMapping("/detail")
    public String getCategory(Model model, @RequestParam(value = "id",defaultValue = "0") Long id){
        model.addAttribute("title", "Admin Categories");
        model.addAttribute("page", "category-detail.jsp");
        try {
            model.addAttribute("c",categoryService.getById(id));
            model.addAttribute("productListNotCategory",productService.getProductsNotCategory(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/index";
    }
}
