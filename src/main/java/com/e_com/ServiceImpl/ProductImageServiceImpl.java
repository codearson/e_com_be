package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.e_com.Dto.ResponseDto;
import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductImageDto;
import com.e_com.Service.ProductImageService;
import com.e_com.Service.BL.ProductImageServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import org.springframework.web.multipart.MultipartFile;
import com.e_com.Service.BL.ProductServiceBL;
import com.e_com.Dao.ProductDao;
import com.e_com.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductImageServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 10:47:53 PM
 * @version 1.0
 **/




@Component
@Slf4j
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageServiceBL productImageServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private ProductServiceBL productServiceBL;

    @Autowired
    private ProductDao productDao;


    @Override
    public ResponseDto saveProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageServiceImpl.saveProductImage invoked");
        ResponseDto responseDto = null;
        try {
            ProductImageDto savedDto = productImageServiceBL.saveProductImage(productImageDto);
            if (savedDto != null) {
                log.info("ProductImage saved.");
                responseDto = serviceUtil.getServiceResponse(savedDto);
            } else {
                log.info("Unable to save ProductImage.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_IMAGE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving ProductImage.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_IMAGE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageServiceImpl.updateProductImage invoked");
        ResponseDto responseDto = null;
        try {
            ProductImageDto updatedDto = productImageServiceBL.updateProductImage(productImageDto);
            if (updatedDto != null) {
                log.info("ProductImage updated.");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Unable to update ProductImage.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_IMAGE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductImage.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_IMAGE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto checkProductImageAvailability(Integer productImageId) {
        log.info("ProductImageServiceImpl.checkProductImageAvailability invoked with productImageId: {}", productImageId);
        ResponseDto responseDto = null;
        try {
            ProductImageDto availableDto = productImageServiceBL.checkProductImageAvailability(productImageId);
            if (availableDto != null) {
                log.info("ProductImage is available.");
                responseDto = serviceUtil.getServiceResponse(availableDto);
            } else {
                log.info("ProductImage not found.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_PRODUCT_IMAGE_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while checking ProductImage availability.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_PRODUCT_IMAGE_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageProductImage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductImageServiceImpl.getAllPageProductImage invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedResponseDto = productImageServiceBL.getAll(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated ProductImage list.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve ProductImage list.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_IMAGE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated ProductImage list.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_IMAGE_DETAILS);
        }
        try {
        	   // DAO call
        	} catch (Exception e) {
        	   log.info("Unable to retrieve ProductImage list.");
        	}

        return responseDto;
    }
    
    public ResponseDto getAllProductImage(String productImageUrl) {
        log.info("BrandServiceImpl.getAllBrand() invoked with brandName: {}", productImageUrl);
        ResponseDto responseDto = null;
        try {
            List<ProductImageDto> productImageList = productImageServiceBL.getAllProductImage(productImageUrl);
            if (productImageList != null && !productImageList.isEmpty()) {
                log.info("Retrieved all ProductImage details.");
                responseDto = serviceUtil.getServiceResponse(productImageList);
            } else {
                log.info("Unable to retrieve all Brand details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRAND_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all Brand details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRAND_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto uploadImageToLocalAndSave(MultipartFile[] files, Integer productId) {
        log.info("ProductImageServiceImpl.uploadImageToDriveAndSave() invoked with productId: {}", productId);
        try {
            if (productId == null) {
                log.warn("No productId provided for upload.");
                return serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_PRODUCT_IMAGE_PRODUCT_ID_REQUIRED
                );
            }
            if (files == null || files.length == 0) {
                log.warn("No files provided for upload.");
                return serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPLOAD_AND_SAVE_PRODUCT_IMAGE
                );
            }

            String uploadDir = "uploads/"; // You can make this configurable
            List<ProductImageDto> savedImages = new ArrayList<>();
            boolean hasValidFile = false;
            for (MultipartFile file : files) {
                if (file == null || file.isEmpty()) {
                    log.warn("Skipping empty file in upload.");
                    continue;
                }
                hasValidFile = true;
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                try {
                    com.e_com.Service.Utils.FileUploadUtil.saveFile(uploadDir, fileName, file);
                } catch (Exception e) {
                    log.error("Failed to save file to local disk: {}", fileName, e);
                    continue;
                }
                String url = "/uploads/" + fileName; // This assumes static resource mapping

                ProductImageDto dto = new ProductImageDto();
                dto.setUrl(url);

                ProductDto productDto = new ProductDto();
                productDto.setId(productId);
                dto.setProductDto(productDto);

                dto.setIsActive(true);

                ProductImageDto savedDto = productImageServiceBL.saveProductImage(dto);
                savedImages.add(savedDto);
            }
            if (!hasValidFile) {
                log.warn("All provided files were empty.");
                return serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPLOAD_AND_SAVE_PRODUCT_IMAGE
                );
            }
            log.info("Successfully uploaded and saved {} product images.", savedImages.size());
            return serviceUtil.getServiceResponse(savedImages);

        } catch (Exception e) {
            log.error("Exception occurred while uploading images and saving to ProductImage table.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_PRODUCT_IMAGE_PRODUCT_ID_REQUIRED
            );
        }
    }

        

}