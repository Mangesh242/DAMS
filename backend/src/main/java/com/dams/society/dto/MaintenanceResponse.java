package com.dams.society.dto;

import com.dams.society.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceResponse {
    private Long id;
    private Long flatOwnerId;
    private String flatNumber;
    private Double amount;
    private LocalDate paymentDate;
    private String paymentMode;
    private String transactionId;
    private String receiptNumber;
    private PaymentStatus status;
    private String remarks;
}
