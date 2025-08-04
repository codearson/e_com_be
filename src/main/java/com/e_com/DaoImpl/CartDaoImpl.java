package com.e_com.DaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.CartDao;
import com.e_com.Domain.Cart;
import com.e_com.Dto.CartDto;
import com.e_com.Transformer.CartTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: CartDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed wazeem
 * @date 01 August 2025
 * @time 8:45:57 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class CartDaoImpl extends BaseDaoImpl<Cart> implements CartDao {

    @Autowired
    CartTransformer cartTransformer;

    @Transactional
    public CartDto saveCart(CartDto cartDto) {
        log.info("CartDaoImpl.saveCart() invoked.");
        try {
            Cart cart = cartTransformer.reverseTransform(cartDto);
            Cart savedCart = saveOrUpdate(cart);
            return cartTransformer.transform(savedCart);
        } catch (Exception e) {
            log.error("Error saving Cart: {}", e.getMessage());
            throw e;
        }
    }


    @Override
    @Transactional
    public CartDto findById(Integer cartId) {
        log.info("CartDaoImpl.findById() invoked with cartId: {}", cartId);
        Criteria criteria = getCurrentSession().createCriteria(Cart.class, "cart");
        criteria.add(Restrictions.eq("id", cartId));
        Cart cart = (Cart) criteria.uniqueResult();
        return cart != null ? cartTransformer.transform(cart) : null;
    }

    @Override
    @Transactional
    public List<CartDto> findByUserId(Integer userId) {
        log.info("CartDaoImpl.findByUserId() invoked with userId: {}", userId);
        Criteria criteria = getCurrentSession().createCriteria(Cart.class, "cart");
        criteria.createAlias("cart.user", "userAlias");
        criteria.add(Restrictions.eq("userAlias.id", userId));
        List<Cart> cartList = criteria.list();
        return cartList.stream().map(cartTransformer::transform).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Cart findByUserIdAndProductId(Integer userId, Integer productId) {
        log.info("CartDaoImpl.findByUserIdAndProductId() invoked with userId: {}, productId: {}", userId, productId);
        Criteria criteria = getCurrentSession().createCriteria(Cart.class, "cart");
        criteria.createAlias("cart.user", "userAlias");
        criteria.createAlias("cart.product", "productAlias");
        criteria.add(Restrictions.eq("userAlias.id", userId));
        criteria.add(Restrictions.eq("productAlias.id", productId));
        criteria.add(Restrictions.eq("cart.isActive", true));
        return (Cart) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public void deleteByUserIdAndProductId(Integer userId, Integer productId) {
        log.info("CartDaoImpl.deleteByUserIdAndProductId() invoked with userId: {}, productId: {}", userId, productId);
        Cart cart = findByUserIdAndProductId(userId, productId);
        if (cart != null) {
            cart.setIsActive(false); 
            saveOrUpdate(cart);      
            log.info("Soft deleted cart item by setting isActive = false for cartId: {}", cart.getId());
        }
    }

    @Override
    @Transactional
    public List<Cart> findActiveEntitiesByUserId(Integer userId) {
        log.info("CartDaoImpl.findActiveEntitiesByUserId() invoked with userId: {}", userId);
        Criteria criteria = getCurrentSession().createCriteria(Cart.class, "cart");
        criteria.createAlias("cart.user", "userAlias");
        criteria.createAlias("cart.product", "productAlias");
        criteria.setFetchMode("product", FetchMode.JOIN);
        criteria.add(Restrictions.eq("userAlias.id", userId));
        criteria.add(Restrictions.eq("cart.isActive", true));
        return criteria.list();
    }


    @Override
    @Transactional
    public List<CartDto> findActiveByUserId(Integer userId) {
        log.info("CartDaoImpl.findActiveByUserId() invoked with userId: {}", userId);
        Criteria criteria = getCurrentSession().createCriteria(Cart.class, "cart");
        criteria.createAlias("cart.user", "userAlias");
        criteria.add(Restrictions.eq("userAlias.id", userId));
        criteria.add(Restrictions.eq("cart.isActive", true));
        List<Cart> cartList = criteria.list();

        return cartList.stream()
                       .map(cartTransformer::transform)
                       .collect(Collectors.toList());
    }


    
} 