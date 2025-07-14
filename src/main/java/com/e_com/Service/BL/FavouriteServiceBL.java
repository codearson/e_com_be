package com.e_com.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.FavouriteDao;
import com.e_com.Dto.FavouriteDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: FavouriteServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 7:30:45 PM
 * @version 1.0
 **/

@Slf4j
@Service
public class FavouriteServiceBL {

    @Autowired
    private FavouriteDao favouriteDao;

    public FavouriteDto saveFavourite(FavouriteDto favouriteDto) {
        log.info("FavouriteServiceBL.saveFavourite() invoked.");
        return favouriteDao.saveFavourite(favouriteDto);
    }

    public FavouriteDto updateFavourite(FavouriteDto favouriteDto) {
        log.info("FavouriteServiceBL.updateFavourite() invoked.");
        return favouriteDao.updateFavourite(favouriteDto);
    }

    public List<FavouriteDto> getAllFavourites() {
        log.info("FavouriteServiceBL.getAllFavourites() invoked.");
        List<FavouriteDto> fullList = favouriteDao.getAllFavourites();
        // Return only minimal info: id, productDto.id, userDto.id, isActive
        List<FavouriteDto> minimalList = new java.util.ArrayList<>();
        for (FavouriteDto dto : fullList) {
            FavouriteDto minimal = new FavouriteDto();
            minimal.setId(dto.getId());
            // Set only productDto.id
            com.e_com.Dto.ProductDto product = new com.e_com.Dto.ProductDto();
            if (dto.getProductDto() != null) {
                product.setId(dto.getProductDto().getId());
            }
            minimal.setProductDto(product);
            // Set only userDto.id
            com.e_com.Dto.UserDto user = new com.e_com.Dto.UserDto();
            if (dto.getUserDto() != null) {
                user.setId(dto.getUserDto().getId());
            }
            minimal.setUserDto(user);
            minimal.setIsActive(dto.getIsActive());
            minimalList.add(minimal);
        }
        return minimalList;
    }
} 