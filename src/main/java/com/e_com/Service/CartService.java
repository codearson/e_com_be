package com.e_com.Service;

import java.util.List;

import com.e_com.Dto.CartDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: CartService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 01 August 2025
 * @time 7:40:24
 * @version 1.0
 **/

public interface CartService {
    
    ResponseDto addToCart(Integer userId, Integer productId, Integer quantity);
        
    ResponseDto removeFromCart(Integer userId, Integer productId);
    
    ResponseDto getCartByUserId(Integer userId);
        
    ResponseDto getCartItemCount(Integer userId);
    
    ResponseDto getCartTotal(Integer userId);
} 