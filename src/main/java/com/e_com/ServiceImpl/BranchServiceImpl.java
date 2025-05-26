package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.BranchDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

import com.e_com.Service.BranchService;
import com.e_com.Service.BL.BranchServiceBL;

/**
 * Title: BranchServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 13, 2025
 * @time 11:02:48 PM
 * @version 1.0
 **/

@Component
@Slf4j
public class BranchServiceImpl implements BranchService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	BranchServiceBL branchServiceBL;

	@Override
	public ResponseDto getAllPageBranch(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("BranchServiceImpl.getAllPageBranch() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		ResponseDto responseDto = null;
		try {
			if (pageNumber < 1 || pageSize < 0) {
				log.info("Invalid pagination parameters provided.");
				return serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_BRANCH_DETAILS);
			}
			PaginatedResponseDto paginatedResponseDto = branchServiceBL.getAllPageBranch(pageNumber, pageSize, status, searchParameters);
			if (paginatedResponseDto != null) {
				log.info("Retrieved paginated Branch details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve paginated Branch details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_BRANCH_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving paginated Branch details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PAGINATED_BRANCH_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(BranchDto branchDto) {
		log.info("BranchServiceImpl.save(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			BranchDto saveBranchDto = branchServiceBL.save(branchDto);
			if (saveBranchDto != null) {
				log.info("Branch Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveBranchDto);
			} else {
				log.info("Unable to save Branch details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_BRANCH_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Branch details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_BRANCH_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateBranch(BranchDto branchDto) {
	    log.info("BranchServiceImpl.updateBranch() invoked");
	    ResponseDto responseDto = null;
	    try {
	        BranchDto updatedBranch = branchServiceBL.updateBranch(branchDto);
	        if (updatedBranch != null) {
	            log.info("Branch details updated successfully.");
	            responseDto = serviceUtil.getServiceResponse(updatedBranch);
	        } else {
	            log.info("Failed to update Branch details.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BRANCH_DETAILS);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while updating Branch details.", e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_BRANCH_DETAILS);
	    }
	    return responseDto;
	}

	
	@Override
	public ResponseDto getBranchByName(String branchName) {
	    log.info("BranchServiceImpl.getBranchByName() invoked");

	    try {
	        List<BranchDto> branchList = branchServiceBL.getBranchByName(branchName);
	        if (!branchList.isEmpty()) {
	            return serviceUtil.getServiceResponse(branchList);
	        } else {
	            return serviceUtil.getErrorServiceResponse(
	                ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_BRANCH_BY_NAME);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving Branch by name", e);
	        return serviceUtil.getExceptionServiceResponseByProperties(
	            ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_BRANCH_BY_NAME);
	    }
	}
	
	@Override
	public ResponseDto updateBranchStatus(Integer branchId, Boolean status) {
		log.info("InvoiceServiceImpl.updateBranchStatus(BranchDto branchDto) invoked");
		ResponseDto responseDto = null;
		try {
			BranchDto updatedBranchStatusDto = branchServiceBL.updateBranchStatus(branchId, status);
			if (updatedBranchStatusDto != null) {
				log.info("Branch Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedBranchStatusDto);
			} else {
				log.info("Unable to update Branch status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BRANCH_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Branch status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_BRANCH_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getBranchById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<BranchDto> branchDtoList = branchServiceBL.getBranchById(id);
			if (branchDtoList != null && !branchDtoList.isEmpty()) {
				log.info("branch detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(branchDtoList);
			} else {
				log.info("Unable to Retrive branch detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_BRANCH_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the branch detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_BRANCH_BY_ID);
		}
		return responseDto;
	}
	
	@Override
    public ResponseDto getAllBranch(String branchName) {
        log.info("BranchServiceImpl.getAllBranch() invoked with branchName: {}", branchName);
        ResponseDto responseDto = null;
        try {
            List<BranchDto> branchList = branchServiceBL.getAllBranch(branchName);
            if (branchList != null && !branchList.isEmpty()) {
                log.info("Retrieved all Branch details.");
                responseDto = serviceUtil.getServiceResponse(branchList);
            } else {
                log.info("Unable to retrieve all Branch details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRANCH_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all Branch details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRANCH_DETAILS);
        }
        return responseDto;
    }
}
