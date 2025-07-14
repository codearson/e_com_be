package com.e_com.Dao;

import java.util.List;
import com.e_com.Domain.Favourite;
import com.e_com.Dto.FavouriteDto;

/**
 * Title: FavouriteDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 7:41:27 PM
 * @version 1.0
 **/

public interface FavouriteDao extends BaseDao<Favourite> {
	
    FavouriteDto saveFavourite(FavouriteDto favouriteDto);
    
    FavouriteDto updateFavourite(FavouriteDto favouriteDto);
    
    List<FavouriteDto> getAllFavourites();
} 