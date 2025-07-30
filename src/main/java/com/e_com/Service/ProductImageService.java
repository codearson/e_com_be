package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.ProductImageDto;
import com.e_com.Dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;
/**
 * Title: ProductImageService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 10:43:19 PM
 * @version 1.0
 **/

public interface ProductImageService {

    ResponseDto saveProductImage(ProductImageDto productImageDto);

    ResponseDto updateProductImage(ProductImageDto productImageDto);

    ResponseDto checkProductImageAvailability(Integer productImageId);

    ResponseDto getAllPageProductImage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);

    ResponseDto getAllProductImage(String ProductImageUrl);

    ResponseDto uploadImageToLocalAndSave(MultipartFile[] files, Integer productId);
    
    ResponseDto getProductImagesByProductId(Integer productId);

    ResponseDto updateProductImageStatus(Integer productImageId, Boolean status);


}