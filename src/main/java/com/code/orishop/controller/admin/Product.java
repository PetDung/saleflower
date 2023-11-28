package com.code.orishop.controller.admin;

import com.code.orishop.model.respone.ProductRes;
import com.code.orishop.service.BrandService;
import com.code.orishop.service.CategoryService;
import com.code.orishop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/products")
public class Product {

    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;

    @Autowired
    ProductService productService;


    @GetMapping("")
    public String getProducts(Model model){
        model.addAttribute("title", "Admin Products");
        model.addAttribute("page", "product.jsp");
        model.addAttribute("products",productService.getAll());
        return "/admin/index";
    }
    @GetMapping("/hidden")
    public String getProductsHidden(Model model){
        model.addAttribute("title", "Admin Products");
        model.addAttribute("page", "product-hidden.jsp");
        model.addAttribute("products",productService.getAllHidden());
        return "/admin/index";
    }

    @GetMapping("/detail")
    public String getProductDetails(Model model, @RequestParam(value = "id",defaultValue = "0") Long id){

        boolean isExits;
        model.addAttribute("title", "Admin Products Details");
        model.addAttribute("page", "product-details.jsp");
        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("brandList", brandService.getAll());
        try {
            model.addAttribute("product",new ProductRes(productService.getProductById(id)));
            isExits = true;
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            isExits = false;
        }
        model.addAttribute("isExits",isExits);
        return "/admin/index";
    }

    @GetMapping("/change")
    public String removeProduct(Model model,
                                @RequestParam(value = "id",defaultValue = "0") Long id,
                                @RequestParam(value = "status",defaultValue = "0") int status)
    {
        try {
            productService.changeStatus(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(status ==1){
            return "redirect:/admin/products/hidden" ;
        }
        return "redirect:/admin/products" ;
    }

    @GetMapping("/create-product")
    public String addProduct(Model model){
        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("brandList", brandService.getAll());
        model.addAttribute("title", "Admin Add Products");
        model.addAttribute("page", "add-product.jsp");
        return "/admin/index";
    }
}
