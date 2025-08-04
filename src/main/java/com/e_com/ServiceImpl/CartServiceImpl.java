package com.e_com.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dao.CartDao;
import com.e_com.Dao.ProductDao;
import com.e_com.Domain.Cart;
import com.e_com.Domain.Product;
import com.e_com.Dto.CartDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.CartService;
import com.e_com.Service.Utils.ServiceUtil;
import com.e_com.Transformer.CartTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: CartServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 01 August 2025
 * @time 7:55:34 PM
 * @version 1.0
 **/

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private CartTransformer cartTransformer;

    @Override
    public ResponseDto addToCart(Integer userId, Integer productId, Integer quantity) {
        log.info("CartServiceImpl.addToCart() invoked with userId: {}, productId: {}, quantity: {}", 
                 userId, productId, quantity);
        
        ResponseDto responseDto = null;
        
        try {
            // Validate product exists and is active
            Product product = productDao.findById(productId);
            if (product == null) {
                log.error("Product not found with id: {}", productId);
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_PRODUCT_NOT_FOUND);
            }
            
            if (!product.getIsActive()) {
                log.error("Product is not active with id: {}", productId);
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_PRODUCT_NOT_ACTIVE);
            }
            
            // Check if product is already in cart
            Cart existingCart = cartDao.findByUserIdAndProductId(userId, productId);
            
            if (existingCart != null) {
                // Update quantity if product already exists in cart
                int newQuantity = existingCart.getQuantity() + quantity;
                existingCart.setQuantity(newQuantity);
                cartDao.saveOrUpdate(existingCart);
                log.info("Updated cart quantity for product {} to {}", productId, newQuantity);
                responseDto = serviceUtil.getServiceResponse(cartTransformer.transform(existingCart));
            } else {
                // Add new product to cart
                CartDto cartDto = new CartDto();
                cartDto.setUserId(userId);
                cartDto.setProductId(productId);
                cartDto.setQuantity(quantity);
                cartDto.setIsActive(true);
                
                CartDto savedCartDto = cartDao.saveCart(cartDto);
                log.info("Added product {} to cart for user {}", productId, userId);
                responseDto = serviceUtil.getServiceResponse(savedCartDto);
            }
            
        } catch (Exception e) {
            log.error("Exception occurs while adding product to cart.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_ADD_PRODUCT_TO_CART);
        }
        
        return responseDto;
    }

   
    @Override
    public ResponseDto removeFromCart(Integer userId, Integer productId) {
        log.info("CartServiceImpl.removeFromCart() invoked with userId: {}, productId: {}", userId, productId);
        
        ResponseDto responseDto = null;
        
        try {
            Cart existingCart = cartDao.findByUserIdAndProductId(userId, productId);
            
            if (existingCart == null) {
                log.error("Product not found in cart for user {} and product {}", userId, productId);
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_PRODUCT_NOT_IN_CART);
            }
            
            cartDao.deleteByUserIdAndProductId(userId, productId);
            log.info("Removed product {} from cart for user {}", productId, userId);
            responseDto = serviceUtil.getServiceResponse("Product removed from cart");
            
        } catch (Exception e) {
            log.error("Exception occurs while removing product from cart.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_REMOVE_PRODUCT_FROM_CART);
        }
        
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto getCartByUserId(Integer userId) {
        log.info("CartServiceImpl.getCartByUserId() invoked with userId: {}", userId);
        
        ResponseDto responseDto = null;
        
        try {
            List<Cart> cartItems = cartDao.findActiveByUserId(userId).stream()
                .map(cartDto -> cartTransformer.reverseTransform(cartDto))
                .collect(Collectors.toList());
            
            if (cartItems != null && !cartItems.isEmpty()) {
                log.info("Retrieved {} cart items for user {}", cartItems.size(), userId);
                List<CartDto> cartDtos = cartItems.stream().map(cartTransformer::transform).collect(Collectors.toList());
                responseDto = serviceUtil.getServiceResponse(cartDtos);
            } else {
                log.info("No cart items found for user {}", userId);
                responseDto = serviceUtil.getServiceResponse("Cart is empty");
            }
            
        } catch (Exception e) {
            log.error("Exception occurs while retrieving cart items.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_PRODUCTS_IN_CART);
        }
        
        return responseDto;
    }


    @Override
    public ResponseDto getCartItemCount(Integer userId) {
        log.info("CartServiceImpl.getCartItemCount() invoked with userId: {}", userId);
        
        ResponseDto responseDto = null;
        
        try {
            List<Cart> cartItems = cartDao.findActiveByUserId(userId).stream()
                .map(cartDto -> cartTransformer.reverseTransform(cartDto))
                .collect(Collectors.toList());
            int itemCount = cartItems != null ? cartItems.size() : 0;
            
            log.info("Cart item count for user {}: {}", userId, itemCount);
            responseDto = serviceUtil.getServiceResponse(itemCount);
            
        } catch (Exception e) {
            log.error("Exception occurs while getting cart item count.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_CART_ITEM_COUNT);
        }
        
        return responseDto;
    }

    @Override
    public ResponseDto getCartTotal(Integer userId) {
        log.info("CartServiceImpl.getCartTotal() invoked with userId: {}", userId);

        ResponseDto responseDto;

        try {
            List<Cart> cartItems = cartDao.findActiveEntitiesByUserId(userId); // fetch full entity list
            double total = 0.0;

            if (cartItems != null) {
                for (Cart cart : cartItems) {
                    if (cart.getQuantity() != null && cart.getProduct() != null && cart.getProduct().getPrice() != null) {
                        double itemTotal = cart.getQuantity() * cart.getProduct().getPrice();
                        total += itemTotal;
                        log.info("Product info for CartId {}: Price = {}, Quantity = {}, ItemTotal = {}",
                                cart.getId(), cart.getProduct().getPrice(), cart.getQuantity(), itemTotal);
                    } else {
                        log.warn("Cannot calculate total for cartId {}. Quantity or price is null.", cart.getId());
                    }
                }
            }

            log.info("Cart total for user {}: {}", userId, total);
            responseDto = serviceUtil.getServiceResponse(total);

        } catch (Exception e) {
            log.error("Exception occurs while calculating cart total.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_CART_TOTAL);
        }

        return responseDto;
    }
}