package com.e_com.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_com.Dao.PasswordResetTokenDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.PasswordResetToken;
import com.e_com.Domain.User;
import com.e_com.Dto.PasswordResetRequestDto;
import com.e_com.Dto.ResetPasswordDto;
import com.e_com.Dto.UserDto;
import com.e_com.Dto.UserLogsDto;
import com.e_com.Service.EmailService;
import com.e_com.Service.PasswordResetService;
import com.e_com.Service.UserLogsService;
import com.e_com.Transformer.PasswordResetTokenTransformer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PasswordResetRequestDto;
import com.e_com.Dto.ResetPasswordDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BL.PasswordResetServiceBL;
import com.e_com.Service.PasswordResetService;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private PasswordResetServiceBL passwordResetServiceBL;

    @Override
    public ResponseDto forgotPassword(PasswordResetRequestDto request) {
        log.info("PasswordResetServiceImpl.forgotPassword invoked for email: {}", request.getEmail());
        ResponseDto responseDto;
        
        try {
            String success = passwordResetServiceBL.forgotPassword(request);
            
            if (success=="Password reset email sent successfully") {
                log.info("Password reset email sent successfully.");
                responseDto = serviceUtil.getServiceResponse("Reset password link sent successfully!");
            } else {
                log.warn("Failed to send reset password email.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_FORGOT_PASSWORD_EMAIL);
            }
        } catch (Exception e) {
            log.error("Exception in forgotPassword process.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_FORGOT_PASSWORD_EMAIL);
        }
        
        return responseDto;
    }

    @Override
    public ResponseDto resetPassword(ResetPasswordDto request) {
        log.info("PasswordResetServiceImpl.resetPassword invoked.");
        ResponseDto responseDto;

        try {
            boolean success = passwordResetServiceBL.resetPassword(request);
            
            if (success) {
                log.info("Password reset successfully.");
                responseDto = serviceUtil.getServiceResponse("Password reset successfully!");
            } else {
                log.warn("Password reset failed.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RESET_PASSWORD);
            }
        } catch (Exception e) {
            log.error("Exception in resetPassword process.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RESET_PASSWORD);
        }
        
        return responseDto;
    }
    

    @Override
    public ResponseDto emailTokenSend(PasswordResetRequestDto request) {
        log.info("PasswordResetServiceImpl.emailTokenSend invoked for email: {}", request.getEmail());
        ResponseDto responseDto;

        try {
            String result = passwordResetServiceBL.emailTokenSend(request);

            if ("Email verification token sent successfully".equals(result)) {
                log.info("Email token sent successfully.");
                responseDto = serviceUtil.getServiceResponse("Verification token sent successfully!");
            } else {
                log.warn("Failed to send email token: {}", result);
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_EMAIL_VERIFICATION_TOKEN_SEND);
            }
        } catch (Exception e) {
            log.error("Exception in emailTokenSend process.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_EMAIL_VERIFICATION_TOKEN_SEND);
        }

        return responseDto;
    }



}
