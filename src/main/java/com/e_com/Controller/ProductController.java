package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Domain.Product;
import com.e_com.Dto.ProductDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 19:03:49
 * @version 1.0
 **/
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public ResponseDto saveProduct(@RequestBody ProductDto productDto) {
        log.info("ProductController.saveProduct() invoked");
        return productService.saveProduct(productDto);
    }

    @PostMapping("/update")
    public ResponseDto updateProduct(@RequestBody ProductDto productDto) {
        log.info("ProductController.updateProduct() invoked");
        return productService.updateProduct(productDto);
    }

    @PutMapping("/updateStatus")
    public ResponseDto updateProductStatus(@RequestParam("productId") Integer productId, @RequestParam("status") Boolean status) {
        log.info("ProductController.updateProductStatus() invoked with productId: {}, status: {}", productId, status);
        return productService.updateProductStatus(productId, status);
    }

    @GetMapping("/getAllPage")
    public ResponseDto getAllPageProduct(@RequestParam("pageNumber") int pageNumber, 
                                        @RequestParam("pageSize") int pageSize,
                                        @RequestParam("status") Boolean status,
                                        WebRequest webRequest) {
        log.info("ProductController.getAllPageProduct() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productService.getAllPageProduct(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }
    @GetMapping("/getAllPageBySearch")
    public ResponseDto getAllPageProductBySearch(@RequestParam("pageNumber") int pageNumber, 
                                        @RequestParam("pageSize") int pageSize,
                                        @RequestParam("status") Boolean status,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "description", required = false) String description,
                                        WebRequest webRequest) {
        log.info("ProductController.getAllPageProductBySearch() invoked with pageNumber: {}, pageSize: {}, status: {},title: {},description: {}", 
                 pageNumber, pageSize, status, title, description);
        return productService.getAllPageProductBySearch(pageNumber, pageSize, status, title, description, HttpReqRespUtils.getSearchParameters(webRequest));
    }
    
    @GetMapping("/getAllPageFilter")
    public ResponseDto getAllPageFilter(@RequestParam("pageNumber") int pageNumber,
                                        @RequestParam("pageSize") int pageSize,
                                        @RequestParam("status") Boolean status,
                                        @RequestParam(value = "category", required = false) String category,
                                        @RequestParam(value = "size", required = false) String size,
                                        @RequestParam(value = "brandName", required = false) String brandName,
                                        @RequestParam(value = "conditionType", required = false) String conditionType,
                                        @RequestParam(value = "color", required = false) String color,
                                        WebRequest webRequest) {
        log.info("ProductController.getAllPageFilter() invoked with pageNumber: {}, pageSize: {}, status: {}, category: {}, size: {}, brandName: {}, condition: {}, color: {}",
                 pageNumber, pageSize, status, category, size, brandName, conditionType, color);
        return productService.getAllPageFilter(pageNumber, pageSize, status, category, size, brandName, conditionType, color, HttpReqRespUtils.getSearchParameters(webRequest));
    }

    @GetMapping("/getAllBySearch")
    public ResponseDto getAllBySearchProduct(@RequestParam(value = "categoryName", required = false) String categoryName,
                                            @RequestParam(value = "brandName", required = false) String brandName,
                                            @RequestParam(value = "conditionType", required = false) String conditionType,
                                            @RequestParam(value = "type", required = false) String type,
                                            @RequestParam(value = "title", required = false) String title) {
        log.info("ProductController.getAllBySearchProduct() invoked with categoryName: {}, brandName: {}, conditionType: {}, type: {}, title: {}", 
                 categoryName, brandName, conditionType, type, title);
        return productService.getAllBySearchProduct(categoryName, brandName, conditionType, type, title);
    }
    
    
    @GetMapping("/getAllPageSortByPrice")
    public ResponseDto getAllPageSortByPrice(@RequestParam("pageNumber") int pageNumber, 
                                             @RequestParam("pageSize") int pageSize,
                                             @RequestParam("status") Boolean status,
                                             @RequestParam("asc") Boolean asc) {
        log.info("ProductController.getAllPageSortByPrice() invoked with pageNumber: {}, pageSize: {}, status: {}, asc: {}", 
                 pageNumber, pageSize, status, asc);

        return productService.getAllPageSortByPrice(pageNumber, pageSize, status, asc); 
    }

    @GetMapping("/getAll")
    public ResponseDto getAllProducts() {
        log.info("ProductController.getAllProducts() invoked");
        return productService.getAllProducts();
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseDto getProductsByCategoryAndDescendants(@PathVariable Long categoryId) {
        log.info("ProductController.getProductsByCategoryAndDescendants() invoked with categoryId: {}", categoryId);
        return productService.getProductsByCategoryAndDescendants(categoryId);
    }
    
    @GetMapping("/getById/{id}")
    public ResponseDto getProductById(@PathVariable Integer id) {
        log.info("ProductController.getProductById() invoked with id: {}", id);
        return productService.getProductById(id);
    }

}