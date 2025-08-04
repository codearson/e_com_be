package com.e_com.Dao;

import java.util.List;

import com.e_com.Domain.Cart;
import com.e_com.Dto.CartDto;

/**
 * Title: CartDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 01 August 2025
 * @time 8:05:45 PM
 * @version 1.0
 **/

public interface CartDao extends BaseDao<Cart> {
    
    CartDto saveCart(CartDto cartDto);
        
    CartDto findById(Integer cartId);
    
    List<CartDto> findByUserId(Integer userId);
    
    Cart findByUserIdAndProductId(Integer userId, Integer productId);
        
    void deleteByUserIdAndProductId(Integer userId, Integer productId);
    
    List<CartDto> findActiveByUserId(Integer userId);
    
    List<Cart> findActiveEntitiesByUserId(Integer userId);

    
} 