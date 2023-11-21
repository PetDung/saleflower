package com.code.orishop.service;

import com.code.orishop.model.entity.CategoryEntity;
import com.code.orishop.model.entity.ProductEntity;
import com.code.orishop.model.request.CategoryReq;
import com.code.orishop.repository.CategoryRepository;
import com.code.orishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    public List<CategoryEntity> getAll(){
        return categoryRepository.findAll();
    }

    public CategoryEntity getById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->{
                    try {
                        throw new Exception("Not found category!");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public CategoryEntity update(CategoryReq categoryReq) throws Exception {
        CategoryEntity entity = getById(categoryReq.getId());

        CategoryEntity entity2 = categoryRepository.findByName(categoryReq.getName());
        if(entity2 != null && !Objects.equals(entity2.getId(), categoryReq.getId())){
            throw new Exception("Category already exists");
        }
        entity.setName(categoryReq.getName());
        for(int i = 0; i < entity.getProductList().size(); i++) {
            ProductEntity productEntity = entity.getProductList().get(i);
            productEntity.setCategory(getById(1L));
            productRepository.save(productEntity);
        }
        entity.getProductList().clear();
        categoryReq.getProducts().forEach(product ->{
            try {
                ProductEntity productEntity = productService.getProductById(product);
                productEntity.setCategory(entity);
                entity.getProductList().add(productEntity);
                productRepository.save(productEntity);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        categoryRepository.save(entity);
        return entity;
    }

    public CategoryEntity createCategory(String name) throws Exception {
        CategoryEntity entity = categoryRepository.findByName(name);
        if(entity != null){
            throw new Exception("Category already exists");
        }
        CategoryEntity  category = new CategoryEntity();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }
}
