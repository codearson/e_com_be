package com.e_com.Dao;

import java.util.List;

import com.e_com.Domain.Reviews;

/**
 * Title: ReviewsDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 07 August 2025
 * @time 6:20:07 PM
 * @version 1.0
 **/

public interface ReviewsDao extends BaseDao<Reviews> {
    
    List<Reviews> findByProductId(Integer productId);
    
    List<Reviews> findByUserId(Integer userId);
    
    Double getAverageRatingByProductId(Integer productId);

	void delete(Integer id);

	Reviews save(Reviews reviews);

	Reviews findById(Integer id);
}
