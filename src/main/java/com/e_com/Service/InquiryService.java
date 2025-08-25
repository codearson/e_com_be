package com.e_com.Service;

import com.e_com.Dto.InquiryDto;
import com.e_com.Dto.ResponseDto;

public interface InquiryService {

    ResponseDto createInquiry(InquiryDto inquiryDto);

    ResponseDto getAllInquiries(int page, int size);

    ResponseDto getInquiryById(int id);

    ResponseDto updateInquiry(int id, InquiryDto inquiryDto);

    ResponseDto deleteInquiry(int id);
}
