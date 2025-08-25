package com.e_com.Dao;

import com.e_com.Domain.Inquiry;
import com.e_com.Dto.PaginatedResponseDto;

public interface InquiryDao extends BaseDao<Inquiry> {

    PaginatedResponseDto getAll(int page, int size);

    Inquiry getById(int id);
}
