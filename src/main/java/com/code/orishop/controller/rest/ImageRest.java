package com.code.orishop.controller.rest;

import com.code.orishop.model.entity.ImageEntity;
import com.code.orishop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/image/file")
public class ImageRest {

    @Autowired
    private ImageService imageService;

    @GetMapping("/product/{id}")
    public ResponseEntity<?> readImageByProductId(@PathVariable Long id) {
        try {
            ImageEntity image = imageService.readImageById(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getType()))
                    .body(image.getData());
        }catch (Exception e){
            e.printStackTrace();
            Error error = new Error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("/files")
    public ResponseEntity<?> uploadImage(@PathVariable Long productId,
                                         @ModelAttribute("file") MultipartFile file) {
       try{
           return ResponseEntity.ok(imageService.uploadImageByProductId(productId,file));
       }catch (Exception e){
           e.printStackTrace();
           Error error = new Error(e.getMessage());
           return ResponseEntity.badRequest().body(error);
       }
    }
}
