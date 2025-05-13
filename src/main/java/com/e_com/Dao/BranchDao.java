package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Dto.BranchDto;
import com.e_com.Dto.PaginatedResponseDto;

/**
 * Title: BranchDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 13, 2025
 * @time 11:41:06 PM
 * @version 1.0
 **/

public interface BranchDao {
PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	BranchDto save (BranchDto branchDto);

	BranchDto update(BranchDto branchDto);
	
	List<BranchDto> getBranchByName(String branchName);
	
	BranchDto checkBranchAvailability(Integer branchId);
	
	List<BranchDto> getBranchById(Integer id);
	
	List<BranchDto> getAllBranch(String branchName);
}
