package com.ecommerce.order.controller;


import com.ecommerce.order.config.AppConstants;
import com.ecommerce.order.dto.OrderDTO;
import com.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/deliver")
public class DelivererController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderDTO>> getOrderShipped(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE_SHIPPED) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY_ORDERED) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER_TANG) String sortOrder
    ){
        return  new ResponseEntity<>(orderService.getOrderSHIPPED(pageNumber, pageSize,sortBy,sortOrder), HttpStatus.OK);
    }


    @PutMapping("/orders/{orderId}/delivered")
    public ResponseEntity<OrderDTO> delivererMarkDelivered(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.delivererMarkDelivered(orderId), HttpStatus.OK);
    }

}
