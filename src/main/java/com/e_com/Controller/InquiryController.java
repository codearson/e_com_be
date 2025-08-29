package com.e_com.Controller;

import com.e_com.Dto.InquiryDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/inquiries")
@Slf4j
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/createInquiry")
    public ResponseDto createInquiry(@RequestBody InquiryDto inquiryDto) {
        log.info("Creating inquiry: {}", inquiryDto);
        return inquiryService.createInquiry(inquiryDto);
    }

    @GetMapping("/getAllInquiries")
    public ResponseDto getAllInquiries(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        log.info("Getting all inquiries with page: {} and size: {}", page, size);
        return inquiryService.getAllInquiries(page, size);
    }

    @GetMapping("/getInquiryById/{id}")
    public ResponseDto getInquiryById(@PathVariable int id) {
        log.info("Getting inquiry with id: {}", id);
        return inquiryService.getInquiryById(id);
    }

    @PutMapping("/updateInquiry/{id}")
    public ResponseDto updateInquiry(@PathVariable int id, @RequestBody InquiryDto inquiryDto) {
        log.info("Updating inquiry with id: {}: {}", id, inquiryDto);
        return inquiryService.updateInquiry(id, inquiryDto);
    }

    @DeleteMapping("/deleteInquiry/{id}")
    public ResponseDto deleteInquiry(@PathVariable int id) {
        log.info("Deleting inquiry with id: {}", id);
        return inquiryService.deleteInquiry(id);
    }

}