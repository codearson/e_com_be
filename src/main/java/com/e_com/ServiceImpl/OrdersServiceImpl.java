package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.OrdersService;
import com.e_com.Service.BL.OrdersServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrdersServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 22:03:35
 * @version 1.0
 **/

@Component
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersServiceBL ordersServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;
    
    @Override
    public ResponseDto saveOrders(OrdersDto ordersDto) {
        log.info("OrdersServiceImpl.saveOrders invoked");
        ResponseDto responseDto = null;
        try {
            if (ordersDto == null || ordersDto.getProductDto() == null || 
                ordersDto.getUserDto() == null || ordersDto.getPostagePartnerDto() == null || 
                ordersDto.getShippingAddressDto() == null || ordersDto.getStatusDto() == null) {
                log.info("Invalid Orders data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_ORDERS_DETAILS);
            }
            OrdersDto savedOrdersDto = ordersServiceBL.saveOrders(ordersDto);
            if (savedOrdersDto != null) {
                log.info("Orders Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedOrdersDto);
            } else {
                log.info("Unable to save Orders details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_ORDERS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Orders details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_ORDERS_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateOrders(OrdersDto ordersDto) {
        log.info("OrdersServiceImpl.updateOrders invoked");
        ResponseDto responseDto = null;
        try {
            if (ordersDto == null || ordersDto.getId() == null || 
                ordersDto.getProductDto() == null || ordersDto.getUserDto() == null || 
                ordersDto.getPostagePartnerDto() == null || ordersDto.getShippingAddressDto() == null || 
                ordersDto.getStatusDto() == null) {
                log.info("Invalid Orders data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDERS_DETAILS);
            }
            OrdersDto updatedOrdersDto = ordersServiceBL.updateOrders(ordersDto);
            if (updatedOrdersDto != null) {
                log.info("Orders Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedOrdersDto);
            } else {
                log.info("Unable to update Orders details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDERS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Orders details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_ORDERS_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateOrdersStatus(Integer ordersId, Boolean status) {
        log.info("OrdersServiceImpl.updateOrdersStatus invoked with ordersId: {}, status: {}", ordersId, status);
        ResponseDto responseDto = null;
        try {
            if (ordersId == null) {
                log.info("Invalid ordersId provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDERS_STATUS);
            }
            OrdersDto updatedOrdersDto = ordersServiceBL.updateOrdersStatus(ordersId, status);
            if (updatedOrdersDto != null) {
                log.info("Orders Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedOrdersDto);
            } else {
                log.info("Unable to update Orders status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDERS_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Orders status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_ORDERS_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageOrders(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("OrdersServiceImpl.getAllPageOrders() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_ORDERS_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = ordersServiceBL.getAllPageOrders(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Orders details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Orders details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_ORDERS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Orders details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_ORDERS_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllBySearchOrders(String title, String firstName, String partnerName, String type) {
        log.info("OrdersServiceImpl.getAllBySearchOrders() invoked with title: {}, firstName: {}, partnerName: {}, type: {}", 
                 title, firstName, partnerName, type);
        ResponseDto responseDto = null;
        try {
            List<OrdersDto> ordersList = ordersServiceBL.getAllBySearchOrders(title, firstName, partnerName, type);
            log.info("Retrieved Orders details by search.");
            responseDto = serviceUtil.getServiceResponse(ordersList);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Orders details by search.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_ORDERS_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getOrderById(Integer ordersId) {
        log.info("OrdersServiceImpl.getOrderById invoked with ordersId: {}", ordersId);
        ResponseDto responseDto = null;
        try {
            if (ordersId == null) {
                log.info("Invalid ordersId provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_ORDERS_DETAILS);
            }
            OrdersDto ordersDto = ordersServiceBL.getOrderById(ordersId);
            if (ordersDto != null) {
                log.info("Order details retrieved.");
                responseDto = serviceUtil.getServiceResponse(ordersDto);
            } else {
                log.info("Order not found.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_ORDERS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving order details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_ORDERS_DETAILS);
        }
        return responseDto;
    }
}