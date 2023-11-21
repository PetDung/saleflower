package com.code.orishop.service;

import com.code.orishop.controller.admin.Product;
import com.code.orishop.model.entity.ImageEntity;
import com.code.orishop.model.entity.ProductEntity;
import com.code.orishop.model.request.ProductReq;
import com.code.orishop.model.respone.ProductRes;
import com.code.orishop.repository.BrandRepository;
import com.code.orishop.repository.CategoryRepository;
import com.code.orishop.repository.ImageRepository;
import com.code.orishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoryRepository  categoryRepository;

    public List<ProductRes> getAll(){
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductRes> productResList = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            productResList.add(new ProductRes(productEntity));
        });
        return productResList;
    }

    public ProductEntity getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() ->{
                    try {
                        throw new Exception("Product dont exists");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    public ProductEntity getProductByName(String name) throws Exception {
        ProductEntity productSys = productRepository.findByName(name);
        if(productSys == null) throw new Exception("Product dont exists");
        return productSys;
    }

    public ProductRes createProduct(ProductReq productReq) throws Exception {
        ProductEntity productSys = productRepository.findByName(productReq.getName());
        if(productSys != null) throw new Exception("Product name already exists");
        ProductEntity product = ProductEntity.builder()
                .name(productReq.getName())
                .price(productReq.getPrice())
                .quantityInStock(productReq.getQuantityInStock())
                .brand(brandRepository.findById(productReq.getBrand()).get())
                .category(categoryRepository.findById(productReq.getCategory()).get())
                .build();
        if(productReq.getImage() != null){
            ImageEntity imageEntity = imageService.saveImage(productReq.getImage());
            product.setImage(imageEntity);
        }
        productRepository.save(product);
        return new ProductRes(product);
    }
    public ProductRes updateProduct(ProductReq product,Long id) throws Exception {
        ProductEntity productSys2 = productRepository.findByName(product.getName());
        if(productSys2 != null && !Objects.equals(productSys2.getId(), id))
            throw new Exception("Product name already exists");

        ProductEntity productSys = productRepository.findById(id)
                .orElseThrow(() -> {
                    try {
                        throw new Exception("Product dont exists");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        productSys.setName(product.getName());
        productSys.setPrice(product.getPrice());
        productSys.setQuantityInStock(product.getQuantityInStock());
        productSys.setBrand(brandRepository.findById(product.getBrand()).get());
        productSys.setCategory(categoryRepository.findById(product.getCategory()).get());

        if(product.getImage() != null){
           imageService.uploadImageByProductId(id, product.getImage());
        }
        productRepository.save(productSys);
        return new ProductRes(productSys);
    }

    public List<ProductEntity> getProductsNotCategory(Long categoryId){
            return productRepository.findAllProductsNotCategories(categoryId);
    }
    public List<ProductEntity> getProductsNotBrand(Long brandId){
        return productRepository.findAllProductsNotBrand(brandId);
    }
}
