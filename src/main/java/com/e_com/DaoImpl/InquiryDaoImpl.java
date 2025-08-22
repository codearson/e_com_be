package com.e_com.DaoImpl;

import com.e_com.Dao.InquiryDao;
import com.e_com.Domain.Inquiry;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InquiryDaoImpl extends BaseDaoImpl<Inquiry> implements InquiryDao {

    @Override
    public PaginatedResponseDto getAll(int page, int size) {
        Criteria criteria = getCurrentSession().createCriteria(Inquiry.class);
        criteria.add(Restrictions.eq("isActive", true));
        criteria.setFirstResult((page) * size);
        criteria.setMaxResults(size);
        List<Inquiry> inquiries = criteria.list();

        Criteria countCriteria = getCurrentSession().createCriteria(Inquiry.class);
        countCriteria.add(Restrictions.eq("isActive", true));
        countCriteria.setProjection(Projections.rowCount());
        Long count = (Long) countCriteria.uniqueResult();

        return HttpReqRespUtils.paginatedResponseMapper(inquiries, page, size, count.intValue());
    }

    @Override
    public Inquiry getById(int id) {
        Criteria criteria = getCurrentSession().createCriteria(Inquiry.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("isActive", true));
        return (Inquiry) criteria.uniqueResult();
    }
}