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

import com.e_com.Dto.ProductCategoryLevel1Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BranchService;
import com.e_com.Service.ProductCategoryLevel1Service;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductCategoryController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 16, 2025
 * @time 11:54:53 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("productCategoryLevel1")
public class ProductCategoryLevel1Controller {

    @Autowired
    ProductCategoryLevel1Service productCategoryLevel1Service;

    @PostMapping("/save")
    public ResponseDto saveProductCategoryLevel1(@RequestBody ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryLevel1Controller.saveProductCategoryLevel1() invoked");
        return productCategoryLevel1Service.saveProductCategoryLevel1(productCategoryLevel1Dto);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateProductCategoryLevel1(@RequestBody ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryLevel1Controller.updateProductCategoryLevel1() invoked");
        return productCategoryLevel1Service.updateProductCategoryLevel1(productCategoryLevel1Dto);
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateProductCategoryLevel1Status(@RequestParam("productCategoryLevel1Id") Integer productCategoryLevel1Id, 
                                                  @RequestParam("status") Boolean status) {
        log.info("ProductCategoryLevel1Controller.updateProductCategoryLevel1Status() invoked with productCategoryLevel1Id: {}, status: {}", 
                 productCategoryLevel1Id, status);
        return productCategoryLevel1Service.updateProductCategoryLevel1Status(productCategoryLevel1Id, status);
    }

    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageProductCategoryLevel1(@RequestParam("pageNumber") int pageNumber, 
                                                @RequestParam("pageSize") int pageSize,
                                                @RequestParam("status") Boolean status,
                                                WebRequest webRequest) {
        log.info("ProductCategoryLevel1Controller.getAllPageProductCategoryLevel1() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productCategoryLevel1Service.getAllPageProductCategoryLevel1(pageNumber, pageSize, status, 
                                                               HttpReqRespUtils.getSearchParameters(webRequest));
    }

    @GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllBySearchProductCategoryLevel1(@RequestParam(value = "name", required = false) String name) {
        log.info("ProductCategoryLevel1Controller.getAllBySearchProductCategoryLevel1() invoked with name: {}", name);
        return productCategoryLevel1Service.getAllBySearchProductCategoryLevel1(name);
    }
}