package com.e_com.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.ReviewsDao;
import com.e_com.Domain.Reviews;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ReviewsDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 07 August 2025
 * @time 7:30:36 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class ReviewsDaoImpl extends BaseDaoImpl<Reviews> implements ReviewsDao {

    @Override
    @Transactional
    public List<Reviews> findByProductId(Integer productId) {
        log.info("ReviewsDaoImpl.findByProductId() invoked with productId: {}", productId);
        Criteria criteria = getCurrentSession().createCriteria(Reviews.class, "reviews");
        criteria.createAlias("reviews.product", "productAlias");
        criteria.add(Restrictions.eq("productAlias.id", productId));
        criteria.add(Restrictions.eq("reviews.isActive", true));
        return criteria.list();
    }

    @Override
    @Transactional
    public List<Reviews> findByUserId(Integer userId) {
        log.info("ReviewsDaoImpl.findByUserId() invoked with userId: {}", userId);
        Criteria criteria = getCurrentSession().createCriteria(Reviews.class, "reviews");
        criteria.createAlias("reviews.user", "userAlias");
        criteria.add(Restrictions.eq("userAlias.id", userId));
        criteria.add(Restrictions.eq("reviews.isActive", true));
        return criteria.list();
    }

    @Override
    @Transactional
    public Double getAverageRatingByProductId(Integer productId) {
        log.info("ReviewsDaoImpl.getAverageRatingByProductId() invoked with productId: {}", productId);
        Criteria criteria = getCurrentSession().createCriteria(Reviews.class, "reviews");
        criteria.createAlias("reviews.product", "productAlias");
        criteria.add(Restrictions.eq("productAlias.id", productId));
        criteria.add(Restrictions.eq("reviews.isActive", true));
        criteria.setProjection(Projections.avg("rating"));
        return (Double) criteria.uniqueResult();
    }

	@Override
	@Transactional
	public void delete(Integer id) {
		log.info("ReviewsDaoImpl.delete() invoked with id: {}", id);
		Reviews review = findById(id);
		if (review != null) {
			review.setIsActive(false);
			super.saveOrUpdate(review);
		}
	}

	@Override
	@Transactional
	public Reviews save(Reviews reviews) {
		log.info("ReviewsDaoImpl.save() invoked.");
		return super.saveOrUpdate(reviews);
	}

	@Override
	@Transactional
	public Reviews findById(Integer id) {
		log.info("ReviewsDaoImpl.findById() invoked with id: {}", id);
		Reviews review = getCurrentSession().get(Reviews.class, id);
		if (review == null) {
			log.info("Review with id {} not found in DB.", id);
		} else {
			log.info("Review with id {} found in DB.", id);
		}
		return review;
	}


}

