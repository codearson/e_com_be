package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.Branch;
import com.e_com.Dto.BranchDto;

/**
 * Title: BranchTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 13, 2025
 * @time 8:23:49 PM
 * @version 1.0
 **/

@Component
public class BranchTransformer implements BaseTransformer<Branch, BranchDto> {

	@Override
	public BranchDto transform(Branch branch) {
		BranchDto branchDto = null;
		if (branch != null) {
			branchDto = new BranchDto();
			branchDto.setId(branch.getId());
			branchDto.setBranchName(branch.getBranchName());
			branchDto.setIsActive(branch.getIsActive());
		}
		return branchDto;
	}

	@Override
	public Branch reverseTransform(BranchDto branchDto) {
		Branch branch = null;
		if (branchDto != null) {
			branch = new Branch();
			branch.setId(branchDto.getId());
			branch.setBranchName(branchDto.getBranchName());
			branch.setIsActive(branchDto.getIsActive());
		}
		return branch;
	}

}