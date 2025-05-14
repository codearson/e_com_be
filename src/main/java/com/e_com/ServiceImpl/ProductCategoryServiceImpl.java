package com.e_com.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryService;
import com.e_com.Service.BL.ProductCategoryServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryServiceBL productCategoryServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceImpl.saveProductCategory invoked");
        ResponseDto responseDto = null;
        try {
            ProductCategoryDto savedCategoryDto = productCategoryServiceBL.saveProductCategory(productCategoryDto);
            if (savedCategoryDto != null) {
                log.info("Product Category details saved.");
                responseDto = serviceUtil.getServiceResponse(savedCategoryDto);
            } else {
                log.info("Unable to save Product Category details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Product Category details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_CATEGORY_DETAILS);
        }
        return responseDto;
    }

   
}
