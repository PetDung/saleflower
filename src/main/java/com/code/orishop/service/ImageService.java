package com.code.orishop.service;

import com.code.orishop.model.entity.ImageEntity;
import com.code.orishop.model.entity.ProductEntity;
import com.code.orishop.repository.ImageRepository;
import com.code.orishop.repository.ProductRepository;
import com.code.orishop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    @Autowired
    private ProductRepository productRepository;

    public ImageEntity readImageById(Long id) {
        Optional<ImageEntity> image = imageRepository.findById(id);
        if (!image.isPresent()) {
            throw new RuntimeException("id not found");
        }
        return image.get();
    }

    public ImageEntity saveImage(MultipartFile file) throws IOException {
        ImageEntity image2upload = ImageEntity.builder()
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        imageRepository.save(image2upload);
        return image2upload;
    }
    public ImageEntity uploadImageByProductId(Long productId, MultipartFile file) {
        validateFile(file);
        ProductEntity product2upload = productRepository.findById(productId)
                .orElseThrow(() -> {
                    throw new RuntimeException("Product id not found");
                });
        try {
            List<ImageEntity> image = getImageListByProductId(productId);
            if (!image.isEmpty()) {
                ImageEntity image1 = image.get(0);
                image1.setType(file.getContentType());
                image1.setData(file.getBytes());
                imageRepository.save(image1);
                return image1;
            }

            ImageEntity image2upload = ImageEntity.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .product(product2upload)
                    .build();
            imageRepository.save(image2upload);
            product2upload.setImage(image2upload);
            productRepository.save(product2upload);
            return image2upload;
        } catch (IOException e) {
            System.out.println(e.toString());
            throw new RuntimeException("Upload error");
        }
    }
    public List<ImageEntity> getImageListByProductId(Long id) {
        ProductEntity product2upload = productRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("user id not found");
                });
        return imageRepository.findByProduct_Id(id);
    }

    public void deleteImageById(Long imageId, Integer productId) {
        ImageEntity image2find = imageRepository.findById(imageId).orElseThrow(() -> {
            throw new RuntimeException("file id not found");
        });

        if (!image2find.getProduct().getId().equals(productId)) {
            throw new RuntimeException("cant delete other user's file");
        }
        imageRepository.delete(image2find);
    }


    public void validateFile(MultipartFile file) {
        String[] IMAGE_TYPES = {"image/jpeg", "image/png", "image/gif"};

        boolean typeValidate = false;
        for (String type : IMAGE_TYPES) {
            if (file.getContentType().equals(type)) {
                typeValidate = true;
            }
        }

        if (!typeValidate) {
            throw new RuntimeException("File type is not allowed");
        }
    }
}
