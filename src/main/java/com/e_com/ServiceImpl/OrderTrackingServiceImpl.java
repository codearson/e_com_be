package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.OrderTrackingDto;
import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.OrderTrackingService;
import com.e_com.Service.BL.OrderTrackingServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrderTrackingServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 19 May 2025
 * @time 5:25:29 pm
 * @version 1.0
 **/
@Component
@Slf4j
public class OrderTrackingServiceImpl implements OrderTrackingService {
	
	@Autowired	
	private OrderTrackingServiceBL orderTrackingServiceBL;
	
	@Autowired
    private ServiceUtil serviceUtil;
	
	@Override
	public ResponseDto saveOrderTracking(OrderTrackingDto orderTrackingDto) {
        log.info("OrderTrackingServiceImpl.saveOrderTracking invoked");
        ResponseDto responseDto = null;
        try {
            if (orderTrackingDto == null || orderTrackingDto.getPostagePartnerDto() == null || 
                orderTrackingDto.getBranchDto() == null || orderTrackingDto.getUserDto() == null || 
                orderTrackingDto.getOrdersDto() == null) {
                log.info("Invalid OrderTracking data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_ORDER_TRACKING_DETAILS);
            }
            OrderTrackingDto savedOrderTrackingDto = orderTrackingServiceBL.saveOrderTracking(orderTrackingDto);
            if (savedOrderTrackingDto != null) {
                log.info("OrderTracking Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedOrderTrackingDto);
            } else {
                log.info("Unable to save OrderTracking details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_ORDER_TRACKING_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving OrderTracking details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_ORDER_TRACKING_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateOrderTracking(OrderTrackingDto orderTrackingDto) {
        log.info("OrderTrackingServiceImpl.updateOrderTracking invoked");
        ResponseDto responseDto = null;
        try {
            if (orderTrackingDto == null || orderTrackingDto.getId() == null || 
                orderTrackingDto.getPostagePartnerDto() == null || orderTrackingDto.getBranchDto() == null || 
                orderTrackingDto.getUserDto() == null || orderTrackingDto.getOrdersDto() == null) {
                log.info("Invalid OrderTracking data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDER_TRACKING_DETAILS);
            }
            OrderTrackingDto updatedOrderTrackingDto = orderTrackingServiceBL.updateOrderTracking(orderTrackingDto);
            if (updatedOrderTrackingDto != null) {
                log.info("OrderTracking Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedOrderTrackingDto);
            } else {
                log.info("Unable to update OrderTracking details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDER_TRACKING_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating OrderTracking details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_ORDER_TRACKING_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateOrderTrackingStatus(Integer orderTrackingId, Boolean status) {
        log.info("OrderTrackingServiceImpl.updateOrderTrackingStatus invoked with orderTrackingId: {}, status: {}", orderTrackingId, status);
        ResponseDto responseDto = null;
        try {
            if (orderTrackingId == null) {
                log.info("Invalid orderTrackingId provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDER_TRACKING_STATUS);
            }
            OrderTrackingDto updatedOrderTrackingDto = orderTrackingServiceBL.updateOrderTrackingStatus(orderTrackingId, status);
            if (updatedOrderTrackingDto != null) {
                log.info("OrderTracking Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedOrderTrackingDto);
            } else {
                log.info("Unable to update OrderTracking status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ORDER_TRACKING_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating OrderTracking status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_ORDER_TRACKING_STATUS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPageOrderTracking(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("OrderTrackingServiceImpl.getAllPageOrderTracking() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_ORDER_TRACKING_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = orderTrackingServiceBL.getAllPageOrderTracking(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Order Tracking details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Order Tracking details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_ORDER_TRACKING_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Order Tracking details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_ORDER_TRACKING_DETAILS);
        }
        return responseDto;
    }
    
    @Override
	public ResponseDto getAllOrderTracking() {
		log.info("OrderTrackingServiceImpl.getAllOrderTracking() invoked");
		ResponseDto responseDto = null;
		try {
			List<OrderTrackingDto> orderTrackingDto = orderTrackingServiceBL.getAllOrderTracking();
			if (orderTrackingDto != null) {
				log.info("Retrieve All Order Tracking Details.");
				responseDto = serviceUtil.getServiceResponse(orderTrackingDto);
			} else {
				log.info("Unable to retrieve All Order Tracking details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_ORDER_TRACKING_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Order Tracking details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_ORDER_TRACKING_DETAILS);
		}
		return responseDto;
	}
	
}
