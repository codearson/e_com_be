package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.BranchDao;
import com.e_com.Dto.BranchDto;
import com.e_com.Dto.PaginatedResponseDto;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 13, 2025
 * @time 11:00:58 PM
 * @version 1.0
 **/


@Slf4j
@Service
public class BranchServiceBL {
	
	@Autowired
	BranchDao branchDao;

	public PaginatedResponseDto getAllPageBranch(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("BranchServiceBL.getAllPageBranch() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		return branchDao.getAllPageBranch(pageNumber, pageSize, status, searchParameters);
	}

	public BranchDto save(BranchDto branchDto) {
		log.info("BranchServiceBL.save() invoked.");
		return branchDao.save(branchDto);
	}

	public BranchDto updateBranch(BranchDto branchDto) {
	    log.info("BranchServiceBL.updateBranch() invoked.");
	    return branchDao.update(branchDto);
	}
	
	public List<BranchDto> getBranchByName(String branchName) {
	    log.info("BranchServiceBL.getBranchByName() invoked");
	    return branchDao.getBranchByName(branchName);
	}
	
	public BranchDto updateBranchStatus(Integer branchId, Boolean status) {
		BranchDto branchDto = branchDao.checkBranchAvailability(branchId);
		if (branchDto != null) {
			branchDto.setIsActive(status);
			return branchDao.update(branchDto);
		} else {
			return null;
		}
	}
	
	public List<BranchDto> getBranchById(Integer id) {
		log.info("BranchServiceBL.getBranchById()invoked");
		return branchDao.getBranchById(id);
	}
	
	public List<BranchDto> getAllBySearch(String branchName) {
        log.info("BranchServiceBL.getAllBySearch() invoked with branchName: {}", branchName);
        return branchDao.getAllBySearch(branchName);
    }
}
