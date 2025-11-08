package com.ecommerce.order.dto;

import com.ecommerce.order.models.DeliveryStatus;
import com.ecommerce.order.models.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String email;
    private List<OrderItemDTO> orderItemList;
    private PaymentStatus paymentStatus;
    private DeliveryStatus deliveryStatus;
    private PaymentDTO payment;
    private LocalDate dateOrder;
    private Double totalAmount;
    private String addressId;
    private String userName;

}
