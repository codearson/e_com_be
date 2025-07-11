package com.e_com.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Dto.FavouriteDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.FavouriteService;
import com.e_com.Service.BL.FavouriteServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import com.e_com.Constants.ApplicationMessageConstants;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Title: FavouriteServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 7:18:15 PM
 * @version 1.0
 **/

@Slf4j
@Component
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteServiceBL favouriteServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveFavourite(FavouriteDto favouriteDto) {
        log.info("FavouriteServiceImpl.saveFavourite invoked");
        ResponseDto responseDto;
        try {
            if (favouriteDto == null || favouriteDto.getProductDto() == null || favouriteDto.getUserDto() == null) {
                log.info("Invalid Favourite data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_FAVOURITE_DETAILS);
            }
            FavouriteDto saved = favouriteServiceBL.saveFavourite(favouriteDto);
            if (saved != null) {
                log.info("Favourite Details saved.");
                responseDto = serviceUtil.getServiceResponse(saved);
            } else {
                log.info("Unable to save Favourite details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_FAVOURITE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Favourite.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_FAVOURITE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateFavourite(FavouriteDto favouriteDto) {
        log.info("FavouriteServiceImpl.updateFavourite invoked");
        ResponseDto responseDto;
        try {
            if (favouriteDto == null || favouriteDto.getId() == null || favouriteDto.getProductDto() == null || favouriteDto.getUserDto() == null) {
                log.info("Invalid Favourite data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_FAVOURITE_DETAILS);
            }
            FavouriteDto updated = favouriteServiceBL.updateFavourite(favouriteDto);
            if (updated != null) {
                log.info("Favourite Details updated.");
                responseDto = serviceUtil.getServiceResponse(updated);
            } else {
                log.info("Unable to update Favourite details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_FAVOURITE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Favourite.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_FAVOURITE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllFavourites() {
        log.info("FavouriteServiceImpl.getAllFavourites invoked");
        ResponseDto responseDto;
        try {
            // Now returns minimal FavouriteDto list
            List<FavouriteDto> list = favouriteServiceBL.getAllFavourites();
            if (list != null) {
                log.info("Retrieved all Favourite details.");
                responseDto = serviceUtil.getServiceResponse(list);
            } else {
                log.info("Unable to retrieve Favourite details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_FAVOURITE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while getting all Favourites.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_FAVOURITE_DETAILS);
        }
        return responseDto;
    }
}
