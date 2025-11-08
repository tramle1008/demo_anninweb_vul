package com.ecommerce.order.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId ;

    private String paymentMethod;

    @OneToOne(mappedBy = "payment", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Order order;
//id cong thanh toan
    private String pgPaymentId;
    private String pgStatus;
    private String pgResponseMessage;
    private String pgName;
    // chỉ giữ quan hệ 1 chiều với Transaction để tránh vòng lặp JSON
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public Payment(String paymentMethod, String pgPaymentId, String pgStatus, String pgResponseMessage,String pgName  ){
        this.paymentMethod = paymentMethod;
        this.pgPaymentId = pgPaymentId;
        this.pgStatus = pgStatus;
        this.pgResponseMessage = pgResponseMessage;
        this.pgName = pgName;
    }
}
