package com.e_com.ServiceImpl;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dao.InquiryDao;
import com.e_com.Domain.Inquiry;
import com.e_com.Dto.InquiryDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.InquiryService;
import com.e_com.Service.BL.InquiryServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
@Slf4j
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryServiceBL inquiryServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;
    @Autowired
    private InquiryDao inquiryDao;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public ResponseDto createInquiry(InquiryDto inquiryDto) {
        log.info("InquiryServiceImpl.createInquiry invoked");
        ResponseDto responseDto;
        try {
            InquiryDto createdInquiry = inquiryServiceBL.createInquiry(inquiryDto);
            if (createdInquiry != null) {
                log.info("Inquiry Details saved.");
                responseDto = serviceUtil.getServiceResponse(createdInquiry);
            } else {
                log.info("Unable to save Inquiry details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_CREATE_INQUIRY);
            }
        }
        catch (Exception e) {
            log.error("Exception occurs while saving Inquiry.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_CREATE_INQUIRY);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllInquiries(int page, int size) {
        log.info("InquiryServiceImpl.getAllInquiries invoked");
        ResponseDto responseDto;
        try {
            responseDto = serviceUtil.getServiceResponse(inquiryServiceBL.getAllInquiries(page, size));
        }
        catch (Exception e) {
            log.error("Exception occurs while retrieving all Inquiries.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_GET_ALL_INQUIRIES);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getInquiryById(int id) {
        log.info("InquiryServiceImpl.getInquiryById invoked");
        ResponseDto responseDto;
        try {
            InquiryDto inquiryDto = inquiryServiceBL.getInquiryById(id);
            if (inquiryDto != null) {
                log.info("Inquiry Details retrieved.");
                responseDto = serviceUtil.getServiceResponse(inquiryDto);
            } else {
                log.info("Unable to retrieve Inquiry details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_INQUIRY_BY_ID);
            }
        }
        catch (Exception e) {
            log.error("Exception occurs while retrieving Inquiry.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_GET_INQUIRY_BY_ID);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateInquiry(int id, InquiryDto inquiryDto) {
        log.info("InquiryServiceImpl.updateInquiry invoked");
        ResponseDto responseDto;
        try {
            InquiryDto updatedInquiry = inquiryServiceBL.updateInquiry(id, inquiryDto);
            if (updatedInquiry != null) {
                log.info("Inquiry Details updated.");

                try {
                    MimeMessage mimeMessage = emailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                    helper.setTo(updatedInquiry.getUserEmail());
                    helper.setSubject("RE: " + updatedInquiry.getSubject() + " (Ref: " + updatedInquiry.getReferenceNumber() + ")");

                    String htmlMsg = "<h3>Hello " + updatedInquiry.getUserName() + ",</h3>" +
                    		"<p>Thank you for your inquiry. Please find the details below.</p>" +
                            "<p><b>Reference Number:</b> " + updatedInquiry.getReferenceNumber() + "</p>" +
                            "<h4>Your Original Inquiry:</h4>" + "<p><i>\"" + updatedInquiry.getMessage() + "\"</i></p>" +
                            "<h4>Our Response:</h4>" + "<p>" + updatedInquiry.getAdminReply() + "</p>" +
                            "<p>Thank you,<br>The E-COM Team</p>";

                    helper.setText(htmlMsg, true); // true indicates HTML
                    emailSender.send(mimeMessage);
                    log.info("Admin reply HTML email sent to user email: {}", updatedInquiry.getUserEmail());

                } catch (Exception e) {
                    log.error("Error sending email notification for inquiry ID: " + updatedInquiry.getId(), e);
                    // Decide if you want to fail the whole operation if email fails.
                    // For now, we just log the error and continue.
                }

                responseDto = serviceUtil.getServiceResponse(updatedInquiry);
            } else {
                log.info("Unable to update Inquiry details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_INQUIRY);
            }
        }
        catch (Exception e) {
            log.error("Exception occurs while updating Inquiry.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_INQUIRY);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteInquiry(int id) {
        log.info("InquiryServiceImpl.deleteInquiry invoked");
        ResponseDto responseDto;
        try {
            inquiryServiceBL.deleteInquiry(id);
            log.info("Inquiry Details deleted.");
            responseDto = serviceUtil.getServiceResponse(null);
        }
        catch (Exception e) {
            log.error("Exception occurs while deleting Inquiry.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_DELETE_INQUIRY);
        }
        return responseDto;
    }

}