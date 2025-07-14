package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.e_com.Dto.FavouriteDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.FavouriteService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: FavouriteController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 7:01:54 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/save")
    public ResponseDto saveFavourite(@RequestBody FavouriteDto favouriteDto) {
        log.info("FavouriteController.saveFavourite() invoked");
        return favouriteService.saveFavourite(favouriteDto);
    }

    @PutMapping("/update")
    public ResponseDto updateFavourite(@RequestBody FavouriteDto favouriteDto) {
        log.info("FavouriteController.updateFavourite() invoked");
        return favouriteService.updateFavourite(favouriteDto);
    }

    @GetMapping("/getAll")
    public ResponseDto getAllFavourites() {
        log.info("FavouriteController.getAllFavourites() invoked");
        return favouriteService.getAllFavourites();
    }
} 