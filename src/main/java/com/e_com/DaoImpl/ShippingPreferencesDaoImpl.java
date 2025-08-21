package com.e_com.DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.e_com.Dao.ShippingPreferencesDao;
import com.e_com.Domain.Product;
import com.e_com.Domain.Profile;
import com.e_com.Domain.ShippingPreferences;
import com.e_com.Dto.ProductDto;
import com.e_com.Dto.ProfileDto;
import com.e_com.Dto.ShippingPreferencesDto;
import com.e_com.Transformer.ShippingPreferencesTransformer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

@Slf4j
@Repository
public class ShippingPreferencesDaoImpl extends BaseDaoImpl<ShippingPreferences> implements ShippingPreferencesDao {
	
	@Autowired
    private ShippingPreferencesTransformer shippingPreferencesTransformer;

	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ShippingPreferencesDto saveShippingPreferences(ShippingPreferencesDto shippingPreferencesDto) {
        log.info("ShippingPreferencesDaoImpl.saveShippingPreferences() invoked.");
        ShippingPreferences shippingPreferences = shippingPreferencesTransformer.reverseTransform(shippingPreferencesDto);
        ShippingPreferences saveShippingPreferences = saveOrUpdate(shippingPreferences); 
        return shippingPreferencesTransformer.transform(saveShippingPreferences);
    }
    
    @Transactional
    public ShippingPreferencesDto updateShippingPreferences(ShippingPreferencesDto shippingPreferencesDto) {
        log.info("ShippingPreferencesDaoImpl.updateShippingPreferences() invoked.");
        ShippingPreferences shippingPreferences = shippingPreferencesTransformer.reverseTransform(shippingPreferencesDto);
        ShippingPreferences updatedShippingPreferences = saveOrUpdate(shippingPreferences);
        return shippingPreferencesTransformer.transform(updatedShippingPreferences);
    }
    
    @Transactional
    public ShippingPreferencesDto checkShippingPreferencesAvailability(Integer shippingPreferencesId) {
        log.info("ShippingPreferencesDaoImpl.checkShippingPreferencesAvailability() invoked with conditionsId: {}", shippingPreferencesId);
        Criteria criteria = getCurrentSession().createCriteria(ShippingPreferences.class, "shipping_preferences");
        criteria.add(Restrictions.eq("shipping_preferences.id", shippingPreferencesId));
        ShippingPreferences shippingPreferences = (ShippingPreferences) criteria.uniqueResult();
        ShippingPreferencesDto shippingPreferencesDto = null;
        if (shippingPreferences != null) {
        	shippingPreferencesDto = shippingPreferencesTransformer.transform(shippingPreferences);
        }
        return shippingPreferencesDto;
    }
    @Override
    @Transactional
    public List<ShippingPreferencesDto> findByUserId(Integer shippingPreferencesId) {
        log.info("ShippingPreferencesDaoImpl.findByUserId() invoked with userId: {}", shippingPreferencesId);
        Session session = getCurrentSession();

        Criteria criteria = session.createCriteria(ShippingPreferences.class, "shipping_preferences");
        criteria.createAlias("shipping_preferences.user", "userAlias"); // Join with user table
        criteria.add(Restrictions.eq("userAlias.id", shippingPreferencesId));
        criteria.add(Restrictions.eq("shipping_preferences.isActive", true)); // Optional: only active products

        List<ShippingPreferences> shippingPreferencesList = criteria.list();

        List<ShippingPreferencesDto> shippingPreferencesDtoList = new ArrayList<>();
        for (ShippingPreferences shippingPreferences : shippingPreferencesList) {
        	shippingPreferencesDtoList.add(shippingPreferencesTransformer.transform(shippingPreferences));
        }

        return shippingPreferencesDtoList;
    }


}
