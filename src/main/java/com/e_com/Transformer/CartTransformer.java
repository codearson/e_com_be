package com.e_com.Transformer;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Dao.ProductDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.Cart;
import com.e_com.Domain.Product;
import com.e_com.Domain.User;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.CartDto;
import com.e_com.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

/**
* Title: CartTransformer.java. Company: www.codearson.com
* Author: Mohomed Wazeem
* Date: 01 August 2025
* Time: 7:02:32 PM
* Version: 1.1 
**/

@Slf4j
@Component
public class CartTransformer implements BaseTransformer<Cart, CartDto> {

    @Autowired
    private ProductTransformer productTransformer;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public CartDto transform(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setCreatedAt(cart.getCreatedAt());
        cartDto.setUpdatedAt(cart.getUpdatedAt());
        cartDto.setIsActive(cart.getIsActive());

        if (cart.getUser() != null) {
            cartDto.setUserId(cart.getUser().getId());
        }

        if (cart.getProduct() != null) {
            cartDto.setProductId(cart.getProduct().getId());

            try {
                ProductDto productDto = productTransformer.transform(cart.getProduct());
                cartDto.setProduct(productDto);
                
                log.info("Product info for CartId {}: Price = {}, Quantity = {}", 
                	    cart.getId(), 
                	    cart.getProduct().getPrice(), 
                	    cart.getQuantity());


                if (cart.getQuantity() != null && cart.getProduct().getPrice() != null) {
                    cartDto.setTotalPrice(cart.getQuantity() * cart.getProduct().getPrice());
                } else {
                    log.warn("Cannot calculate total price: quantity or price is null. ProductId: {}", cart.getProduct().getId());
                    cartDto.setTotalPrice(0.0);
                }
            } catch (Exception e) {
                log.error("Error transforming product with ID {}: {}", cart.getProduct().getId(), e.getMessage());
                cartDto.setTotalPrice(0.0);
            }
        }

        return cartDto;
    }

    @Override
    public Cart reverseTransform(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }

        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedAt(cartDto.getCreatedAt() != null ? cartDto.getCreatedAt() : LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        cart.setIsActive(cartDto.getIsActive() != null ? cartDto.getIsActive() : true);

        if (cartDto.getUserId() != null) {
            User user = userDao.findUserById(cartDto.getUserId());
            cart.setUser(user);
        }

        if (cartDto.getProductId() != null) {
            Product product = productDao.findById(cartDto.getProductId());
            cart.setProduct(product);
        }

        return cart;
    }
}