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

import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BrandService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BrandController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:05:35
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto saveBrand(@RequestBody BrandDto brandDto) {
        log.info("BrandController.saveBrand() invoked");
        return brandService.saveBrand(brandDto);
    }
    
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateBrand(@RequestBody BrandDto brandDto) {
        log.info("BrandController.updateBrand() invoked");
        return brandService.updateBrand(brandDto);
    }
    
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateBrandStatus(@RequestParam("brandId") Integer brandId, @RequestParam("status") Boolean status) {
        log.info("BrandController.updateBrandStatus() invoked with brandId: {}, status: {}", brandId, status);
        return brandService.updateBrandStatus(brandId, status);
    }
    
    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageBrand(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                                      @RequestParam("status") Boolean status, WebRequest webRequest) {
        log.info("BrandController.getAllPageBrand() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return brandService.getAllPageBrand(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }
}
