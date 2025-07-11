package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.Favourite;
import com.e_com.Dto.FavouriteDto;

/**
 * Title: FavouriteTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 6:47:24 PM
 * @version 1.0
 **/

@Component
public class FavouriteTransformer implements BaseTransformer<Favourite, FavouriteDto> {

    @Autowired
    private ProductTransformer productTransformer;

    @Autowired
    private UserTransfomer userTransfomer;

    @Override
    public FavouriteDto transform(Favourite favourite) {
        FavouriteDto dto = null;
        if (favourite != null) {
            dto = new FavouriteDto();
            dto.setId(favourite.getId());
            dto.setProductDto(productTransformer.transform(favourite.getProduct()));
            dto.setUserDto(userTransfomer.transform(favourite.getUser()));
            dto.setIsActive(favourite.getIsActive());
        }
        return dto;
    }

    @Override
    public Favourite reverseTransform(FavouriteDto favouriteDto) {
        Favourite favourite = null;
        if (favouriteDto != null) {
            favourite = new Favourite();
            favourite.setId(favouriteDto.getId());
            favourite.setProduct(productTransformer.reverseTransform(favouriteDto.getProductDto()));
            favourite.setUser(userTransfomer.reverseTransform(favouriteDto.getUserDto()));
            favourite.setIsActive(favouriteDto.getIsActive());
        }
        return favourite;
    }
} 