package com.e_com.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.ShippingAddressDao;
import com.e_com.Domain.ShippingAddress;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ShippingAddressDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ShippingAddressTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShippingAddressDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 15 May 2025
 * @time 19:01:21
 * @version 1.0
 **/

@Slf4j
@Repository
public class ShippingAddressDaoImpl extends BaseDaoImpl<ShippingAddress> implements ShippingAddressDao {

    @Autowired
    ShippingAddressTransformer shippingAddressTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ShippingAddressDto saveShippingAddress(ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressDaoImpl.saveShippingAddress() invoked.");
        ShippingAddress shippingAddress = shippingAddressTransformer.reverseTransform(shippingAddressDto);
        ShippingAddress savedShippingAddress = saveOrUpdate(shippingAddress);
        return shippingAddressTransformer.transform(savedShippingAddress);
    }

    @Transactional
    public ShippingAddressDto updateShippingAddress(ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressDaoImpl.updateShippingAddress() invoked.");
        ShippingAddress shippingAddress = shippingAddressTransformer.reverseTransform(shippingAddressDto);
        ShippingAddress updatedShippingAddress = saveOrUpdate(shippingAddress);
        return shippingAddressTransformer.transform(updatedShippingAddress);
    }

    @Transactional
    public ShippingAddressDto checkShippingAddressAvailability(Integer shippingAddressId) {
        log.info("ShippingAddressDaoImpl.checkShippingAddressAvailability() invoked with shippingAddressId: {}", shippingAddressId);
        Criteria criteria = getCurrentSession().createCriteria(ShippingAddress.class, "shippingAddress");
        criteria.add(Restrictions.eq("shippingAddress.id", shippingAddressId));
        ShippingAddress shippingAddress = (ShippingAddress) criteria.uniqueResult();
        ShippingAddressDto shippingAddressDto = null;
        if (shippingAddress != null) {
            shippingAddressDto = shippingAddressTransformer.transform(shippingAddress);
        }
        return shippingAddressDto;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageShippingAddress(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ShippingAddressDaoImpl.getAllPageShippingAddress() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<ShippingAddress> shippingAddressList = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM shipping_address");
        if (status != null) {
            countString.append(" WHERE is_active = ").append(status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(ShippingAddress.class, "shippingAddress");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        shippingAddressList = criteria.list();

        if (shippingAddressList != null && !shippingAddressList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(shippingAddressList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(shippingAddressList.stream().map(shippingAddress -> {
                return shippingAddressTransformer.transform(shippingAddress);
            }).collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public List<ShippingAddressDto> getAllShippingAddress(String address) {
        log.info("ShippingAddressDaoImpl.getAllShippingAddress() invoked with address: {}", address);
        Criteria criteria = getCurrentSession().createCriteria(ShippingAddress.class, "shippingAddress");

        // Add address filter if provided
        if (address != null && !address.isEmpty()) {
            criteria.add(Restrictions.ilike("address", "%" + address + "%"));
        }

        List<ShippingAddress> shippingAddressList = criteria.list();
        return shippingAddressList.stream()
                                 .map(shippingAddress -> shippingAddressTransformer.transform(shippingAddress))
                                 .collect(Collectors.toList());
    }
}