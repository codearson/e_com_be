package com.e_com.DaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.FavouriteDao;
import com.e_com.Domain.Favourite;
import com.e_com.Dto.FavouriteDto;
import com.e_com.Transformer.FavouriteTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: FavouriteDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 7:48:36 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class FavouriteDaoImpl extends BaseDaoImpl<Favourite> implements FavouriteDao {

    @Autowired
    private FavouriteTransformer favouriteTransformer;

    @Override
    @Transactional
    public FavouriteDto saveFavourite(FavouriteDto favouriteDto) {
        log.info("FavouriteDaoImpl.saveFavourite() invoked.");
        try {
            Favourite favourite = favouriteTransformer.reverseTransform(favouriteDto);
            Favourite saved = saveOrUpdate(favourite);
            return favouriteTransformer.transform(saved);
        } catch (Exception e) {
            log.error("Error saving Favourite: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public FavouriteDto updateFavourite(FavouriteDto favouriteDto) {
        log.info("FavouriteDaoImpl.updateFavourite() invoked.");
        try {
            Favourite favourite = favouriteTransformer.reverseTransform(favouriteDto);
            Favourite updated = saveOrUpdate(favourite);
            return favouriteTransformer.transform(updated);
        } catch (Exception e) {
            log.error("Error updating Favourite: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public List<FavouriteDto> getAllFavourites() {
        log.info("FavouriteDaoImpl.getAllFavourites() invoked.");
        Criteria criteria = getCurrentSession().createCriteria(Favourite.class);
        List<Favourite> list = criteria.list();
        return list.stream().map(favouriteTransformer::transform).collect(Collectors.toList());
    }
} 