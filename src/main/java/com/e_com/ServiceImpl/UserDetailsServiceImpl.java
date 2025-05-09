package com.e_com.ServiceImpl;

import com.e_com.Domain.CustomUserDetails;
import com.e_com.Domain.User;
import com.e_com.Service.BL.UserServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceBL userServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsImpl.loadUserByUsername() invoked with username :{}",username);
        try{
            User user=userServiceBL.getUserByUserName(username);
            if(user==null){
                throw new UsernameNotFoundException("user with the username "+username+" not exists");
            }
            return CustomUserDetails.build(user);
        }catch (Exception ex){
            log.error("Exception occurs while get User details by username  with the message : {}.", ex.getMessage());
            throw new RuntimeException(ex);
        }

    }
}
