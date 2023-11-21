package com.code.orishop.service;

import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.CategoryEntity;
import com.code.orishop.model.entity.ProductEntity;
import com.code.orishop.model.request.BrandReq;
import com.code.orishop.model.request.CategoryReq;
import com.code.orishop.repository.BrandRepository;
import com.code.orishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    public List<BrandEntity> getAll(){
        return brandRepository.findAll();
    }
    public BrandEntity getById(Long id){
        return brandRepository.findById(id)
                .orElseThrow(()->{
                    try {
                        throw new Exception("Not found category!");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public BrandEntity createBrand(String name) throws Exception {
        BrandEntity entity = brandRepository.findByName(name);
        if(entity != null){
            throw new Exception("Category already exists");
        }
        BrandEntity  brand = new BrandEntity();
        brand.setName(name);
        brandRepository.save(brand);
        return brand;
    }

    public BrandEntity update(BrandReq brandReq) throws Exception {
        BrandEntity entity = getById(brandReq.getId());

        BrandEntity entity2 = brandRepository.findByName(brandReq.getName());
        if(entity2 != null && !Objects.equals(entity2.getId(), brandReq.getId())){
            throw new Exception("Category already exists");
        }
        entity.setName(brandReq.getName());
        for(int i = 0; i < entity.getProductList().size(); i++) {
            ProductEntity productEntity = entity.getProductList().get(i);
            productEntity.setBrand(getById(1L));
            productRepository.save(productEntity);
        }
        entity.getProductList().clear();
        brandReq.getProducts().forEach(product ->{
            try {
                ProductEntity productEntity = productService.getProductById(product);
                productEntity.setBrand(entity);
                entity.getProductList().add(productEntity);
                productRepository.save(productEntity);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        brandRepository.save(entity);
        return entity;
    }
}
