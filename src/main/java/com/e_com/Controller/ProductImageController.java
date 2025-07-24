package com.e_com.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.e_com.Dto.ProductImageDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductImageService;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductImageController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 * Author: Asjath Musharrif Abusalif
 * Date: May 28, 2025
 * Time: 11:00 PM
 * Version: 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("productImage")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;



    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto saveProductImage(@RequestBody ProductImageDto productImageDto) {
        log.info("ProductImageController.saveProductImage() invoked");
        return productImageService.saveProductImage(productImageDto);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateProductImage(@RequestBody ProductImageDto productImageDto) {
        log.info("ProductImageController.updateProductImage() invoked");
        return productImageService.updateProductImage(productImageDto);
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto checkProductImageAvailability(@RequestParam("productImageId") Integer productImageId) {
        log.info("ProductImageController.checkProductImageAvailability() invoked with ID: {}", productImageId);
        return productImageService.checkProductImageAvailability(productImageId);
    }

    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageProductImage(@RequestParam("pageNumber") int pageNumber,
                                              @RequestParam("pageSize") int pageSize,
                                              @RequestParam("status") Boolean status,
                                              WebRequest webRequest) {
        log.info("ProductImageController.getAllPageProductImage() invoked");
        Map<String, String> searchParams = HttpReqRespUtils.getSearchParameters(webRequest);
        return productImageService.getAllPageProductImage(pageNumber, pageSize, status, searchParams);
    }

    @GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllProductImages(@RequestParam(value = "productImageUrl", required = false) String productImageUrl) {
        log.info("ProductImageController.getAllProductImages() invoked with productImageUrl: {}", productImageUrl);
        return productImageService.getAllPageProductImage(1, 0, null, Map.of("ProductImageUrl", productImageUrl));
    }

    @PostMapping("/uploadToLocal")
    public ResponseDto uploadImageToLocal(@RequestParam("files") MultipartFile[] files,
                                          @RequestParam(value = "productId", required = false) Integer productId) {
        log.info("ProductImageController.uploadImageToLocal() invoked");
        return productImageService.uploadImageToLocalAndSave(files, productId);
    }
    
    @GetMapping("/getByProductId")
   // @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getImagesByProductId(@RequestParam("productId") Integer productId) {
        log.info("ProductImageController.getProductImagesByProductId() invoked with productId: {}", productId);
        return productImageService.getProductImagesByProductId(productId);
    }


}
