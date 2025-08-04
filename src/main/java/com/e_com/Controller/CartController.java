package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.Dto.ResponseDto;
import com.e_com.Service.CartService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: CartController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeen
 * @date 01 August 2025
 * @time 7:15:25 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseDto addToCart(@RequestParam("userId") Integer userId,
                                @RequestParam("productId") Integer productId,
                                @RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {
        log.info("CartController.addToCart() invoked with userId: {}, productId: {}, quantity: {}", 
                 userId, productId, quantity);
        return cartService.addToCart(userId, productId, quantity);
    }

    @DeleteMapping("/remove")
    public ResponseDto removeFromCart(@RequestParam("userId") Integer userId,
                                    @RequestParam("productId") Integer productId) {
        log.info("CartController.removeFromCart() invoked with userId: {}, productId: {}", userId, productId);
        return cartService.removeFromCart(userId, productId);
    }

    @GetMapping("/getCartByUserId")
    public ResponseDto getCartItems(@RequestParam("userId") Integer userId) {
        log.info("CartController.getCartItems() invoked with userId: {}", userId);
        return cartService.getCartByUserId(userId);
    }

    @GetMapping("/count")
    public ResponseDto getCartItemCount(@RequestParam("userId") Integer userId) {
        log.info("CartController.getCartItemCount() invoked with userId: {}", userId);
        return cartService.getCartItemCount(userId);
    }

    @GetMapping("/total")
    public ResponseDto getCartTotal(@RequestParam("userId") Integer userId) {
        log.info("CartController.getCartTotal() invoked with userId: {}", userId);
        return cartService.getCartTotal(userId);
    }
} 