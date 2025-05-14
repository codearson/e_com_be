package com.e_com.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.e_com.Dto.BranchDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: BranchService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 13, 2025
 * @time 10:52:53 PM
 * @version 1.0
 **/


@Service
public interface BranchService {
    ResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	public ResponseDto save(BranchDto branchDto);
	
	ResponseDto updateBranch(BranchDto branchDto);
	
	ResponseDto getBranchByName(String branchName);
	
	public ResponseDto updateBranchStatus(Integer branchId, Boolean status);
	
	public ResponseDto getBranchById(Integer id);
	
	ResponseDto getAllBranch(String branchName);


}
