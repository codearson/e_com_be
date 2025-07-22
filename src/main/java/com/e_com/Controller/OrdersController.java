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

import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.OrdersService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrdersController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 22:49:34
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping("/save")
    public ResponseDto saveOrders(@RequestBody OrdersDto ordersDto) {
        log.info("OrdersController.saveOrders() invoked");
        return ordersService.saveOrders(ordersDto);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateOrders(@RequestBody OrdersDto ordersDto) {
        log.info("OrdersController.updateOrders() invoked");
        return ordersService.updateOrders(ordersDto);
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateOrdersStatus(@RequestParam("ordersId") Integer ordersId, @RequestParam("status") Boolean status) {
        log.info("OrdersController.updateOrdersStatus() invoked with ordersId: {}, status: {}", ordersId, status);
        return ordersService.updateOrdersStatus(ordersId, status);
    }

    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageOrders(@RequestParam("pageNumber") int pageNumber, 
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam("status") Boolean status,
                                       WebRequest webRequest) {
        log.info("OrdersController.getAllPageOrders() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return ordersService.getAllPageOrders(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }

    @GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllBySearchOrders(@RequestParam(value = "title", required = false) String title,
                                           @RequestParam(value = "firstName", required = false) String firstName,
                                           @RequestParam(value = "partnerName", required = false) String partnerName,
                                           @RequestParam(value = "type", required = false) String type) {
        log.info("OrdersController.getAllBySearchOrders() invoked with title: {}, firstName: {}, partnerName: {}, type: {}", 
                 title, firstName, partnerName, type);
        return ordersService.getAllBySearchOrders(title, firstName, partnerName, type);
    }

    @GetMapping("/getById")
    public ResponseDto getOrderById(@RequestParam("ordersId") Integer ordersId) {
        log.info("OrdersController.getOrderById() invoked with ordersId: {}", ordersId);
        return ordersService.getOrderById(ordersId);
    }
}