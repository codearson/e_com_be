package com.e_com.Service;

import com.e_com.Dto.FavouriteDto;
import com.e_com.Dto.ResponseDto;
import java.util.List;

/**
 * Title: FavouriteService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 7:12:43 PM
 * @version 1.0
 **/

public interface FavouriteService {
	
    ResponseDto saveFavourite(FavouriteDto favouriteDto);
    
    ResponseDto updateFavourite(FavouriteDto favouriteDto);
    
    ResponseDto getAllFavourites();
} 