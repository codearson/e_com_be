package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Dto.BranchDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BranchService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Title: BranchTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 13, 2025
 * @time 8:24:49 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("branch")
public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@GetMapping("/getAllPage")
	public ResponseDto getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			@RequestParam("status") Boolean status, WebRequest webRequest) {
		log.info("BranchController.getAll() invoked.");
		Map<String, String> searchParams = HttpReqRespUtils.getSearchParameters(webRequest);
		searchParams.put("status", status.toString());
		return branchService.getAll(pageNumber, pageSize, searchParams);
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody BranchDto branchDto) {
		log.info("BranchController.save() invoked");
		return branchService.save(branchDto);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateBranch(@RequestBody BranchDto branchDto) {
	    log.info("BranchController.updateBranch() invoked");
	    return branchService.updateBranch(branchDto);
	}
	
	@GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getBranchByName(@RequestParam("branchName") String branchName) {
	    log.info("BranchController.getBranchByName() invoked");
	    return branchService.getBranchByName(branchName);
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateInvoiceStatus(@RequestParam("branchId") Integer branchId, @RequestParam("status") Boolean status) {
		log.info("BranchController.updateInvoiceStatus() invoked.");
		return branchService.updateBranchStatus(branchId, status);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getBranchById(@RequestParam("id") Integer id) {
		log.info("BranchController.getBranchById() invoked with id", id);
		return branchService.getBranchById(id);
	}
	
	@GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllBranch(@RequestParam(value = "branchName", required = false) String branchName) {
        log.info("BranchController.getAllBranch() invoked with branchName: {}", branchName);
        return branchService.getAllBranch(branchName);
    }

}
