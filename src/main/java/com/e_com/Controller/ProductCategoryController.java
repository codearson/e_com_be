package com.e_com.Controller;

import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product-category")
@CrossOrigin(origins = "*")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/save")
    public ResponseDto saveCategory(@RequestBody ProductCategoryDto dto) {
        log.info("ProductCategoryController.saveCategory invoked");
        return productCategoryService.saveCategory(dto);
    }

    @PutMapping("/update")
    public ResponseDto updateCategory(@RequestBody ProductCategoryDto dto) {
        log.info("ProductCategoryController.updateCategory invoked");
        return productCategoryService.updateCategory(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto getCategoryById(@PathVariable Long id) {
        log.info("ProductCategoryController.getCategoryById invoked");
        return productCategoryService.getCategoryById(id);
    }

    @GetMapping("/getAll")
    public ResponseDto getAllCategories() {
        log.info("ProductCategoryController.getAllCategories invoked");
        return productCategoryService.getAllCategories();
    }

    @GetMapping("/getAllPage")
    public ResponseDto getAllCategoriesPage(@RequestParam int pageNumber,
                                            @RequestParam int pageSize,
                                            @RequestParam(required = false) Integer level,
                                            @RequestParam(required = false) Long parentId) {
        log.info("ProductCategoryController.getAllCategoriesPage invoked");
        return productCategoryService.getAllCategoriesPage(pageNumber, pageSize, level, parentId);
    }

    @GetMapping("/tree")
    public ResponseDto getCategoryTree() {
        log.info("ProductCategoryController.getCategoryTree invoked");
        return productCategoryService.getCategoryTree();
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseDto updateStatus(@PathVariable Long id, @RequestParam Boolean isActive) {
        log.info("ProductCategoryController.updateStatus invoked");
        return productCategoryService.updateStatus(id, isActive);
    }

    @GetMapping("/getAllPageBySearch")
    public ResponseDto getAllCategoriesPageBySearch(@RequestParam int pageNumber,
                                                    @RequestParam int pageSize,
                                                    @RequestParam(required = false) String search,
                                                    @RequestParam(required = false) Integer level,
                                                    @RequestParam(required = false) Long parentId) {
        log.info("ProductCategoryController.getAllCategoriesPageBySearch invoked");
        return productCategoryService.getAllCategoriesPageBySearch(pageNumber, pageSize, search, level, parentId);
    }
} 