package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.UserBankDetails;
import com.e_com.Dto.UserBankDetailsDto;

/**
 * Title: UserBankDetailsTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

@Component
public class UserBankDetailsTransformer implements BaseTransformer<UserBankDetails, UserBankDetailsDto> {

	@Autowired
	UserTransfomer userTransformer;
	
	@Autowired
	BankTransformer bankTransformer;
	
	@Autowired
	BranchTransformer branchTransformer;
	
	@Override
    public UserBankDetailsDto transform(UserBankDetails userBankDetails) {
		UserBankDetailsDto userBankDetailsDto = null;
        if (userBankDetails != null) {
        	userBankDetailsDto = new UserBankDetailsDto();
        	userBankDetailsDto.setId(userBankDetails.getId());
        	userBankDetailsDto.setAccountHolderName(userBankDetails.getAccountHolderName());
        	userBankDetailsDto.setAccountNumber(userBankDetails.getAccountNumber());
        	userBankDetailsDto.setCreatedAt(userBankDetails.getCreatedAt());
        	if (userBankDetails.getUser() != null) {
        		userBankDetailsDto.setUserDto(userTransformer.transform(userBankDetails.getUser()));
			}
        	if (userBankDetails.getBank() != null) {
        		userBankDetailsDto.setBankDto(bankTransformer.transform(userBankDetails.getBank()));
			}
        	if (userBankDetails.getBranch() != null) {
        		userBankDetailsDto.setBranchDto(branchTransformer.transform(userBankDetails.getBranch()));
			}
        	userBankDetailsDto.setIsActive(userBankDetails.getIsActive());
        }
        return userBankDetailsDto;
    }

    @Override
    public UserBankDetails reverseTransform(UserBankDetailsDto userBankDetailsDto) {
    	UserBankDetails userBankDetails = null;
        if (userBankDetailsDto != null) {
        	userBankDetails = new UserBankDetails();
        	userBankDetails.setId(userBankDetailsDto.getId());
        	userBankDetails.setAccountHolderName(userBankDetailsDto.getAccountHolderName());
        	userBankDetails.setAccountNumber(userBankDetailsDto.getAccountNumber());
        	userBankDetails.setCreatedAt(userBankDetailsDto.getCreatedAt());
        	if (userBankDetailsDto.getUserDto() != null) {
        		userBankDetails.setUser(userTransformer.reverseTransform(userBankDetailsDto.getUserDto()));
			}
        	if (userBankDetailsDto.getBankDto() != null) {
        		userBankDetails.setBank(bankTransformer.reverseTransform(userBankDetailsDto.getBankDto()));
			}
        	if (userBankDetailsDto.getBranchDto() != null) {
        		userBankDetails.setBranch(branchTransformer.reverseTransform(userBankDetailsDto.getBranchDto()));
			}
        	userBankDetails.setIsActive(userBankDetailsDto.getIsActive());
        }
        return userBankDetails;
    }
	
}
