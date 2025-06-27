package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Dto.ProductCategoryLevel4Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryLevel4Service;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductCategoryLevel4Controller.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date Jan 27, 2025
 * @time 5:40 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("productCategoryLevel4")
public class ProductCategoryLevel4Controller {

    @Autowired
    ProductCategoryLevel4Service productCategoryLevel4Service;

    @PostMapping("/save")
    public ResponseDto saveProductCategoryLevel4(@RequestBody ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4Controller.saveProductCategoryLevel4() invoked");
        return productCategoryLevel4Service.saveProductCategoryLevel4(productCategoryLevel4Dto);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateProductCategoryLevel4(@RequestBody ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4Controller.updateProductCategoryLevel4() invoked");
        return productCategoryLevel4Service.updateProductCategoryLevel4(productCategoryLevel4Dto);
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateProductCategoryLevel4Status(@RequestParam("productCategoryLevel4Id") Integer productCategoryLevel4Id, 
                                                  @RequestParam("status") Boolean status) {
        log.info("ProductCategoryLevel4Controller.updateProductCategoryLevel4Status() invoked with productCategoryLevel4Id: {}, status: {}", 
                 productCategoryLevel4Id, status);
        return productCategoryLevel4Service.updateProductCategoryLevel4Status(productCategoryLevel4Id, status);
    }

    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageProductCategoryLevel4(@RequestParam("pageNumber") int pageNumber, 
                                                @RequestParam("pageSize") int pageSize,
                                                @RequestParam("status") Boolean status,
                                                WebRequest webRequest) {
        log.info("ProductCategoryLevel4Controller.getAllPageProductCategoryLevel4() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productCategoryLevel4Service.getAllPageProductCategoryLevel4(pageNumber, pageSize, status, 
                                                               HttpReqRespUtils.getSearchParameters(webRequest));
    }

    @GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllBySearchProductCategoryLevel4(@RequestParam(value = "name", required = false) String name) {
        log.info("ProductCategoryLevel4Controller.getAllBySearchProductCategoryLevel4() invoked with name: {}", name);
        return productCategoryLevel4Service.getAllBySearchProductCategoryLevel4(name);
    }
} 