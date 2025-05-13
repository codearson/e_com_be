package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.Status;
import com.e_com.Dto.StatusDto;

/**
 * Title: StatusTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 13, 2025
 * @time 11:45:26 PM
 * @version 1.0
 **/

@Component
public class StatusTransformer implements BaseTransformer<Status, StatusDto> {

	@Override
	public StatusDto transform(Status status) {
		StatusDto statusDto = null;
		if (status != null) {
			statusDto = new StatusDto();
			statusDto.setId(status.getId());
			statusDto.setType(status.getType());
			statusDto.setIsActive(status.getIsActive());
		}
		return statusDto;
	}

	@Override
	public Status reverseTransform(StatusDto statusDto) {
		Status status = null;
		if (statusDto != null) {
			status = new Status();
			status.setId(statusDto.getId());
			status.setType(statusDto.getType());
			status.setIsActive(statusDto.getIsActive());
		}
		return status;
	}
}
