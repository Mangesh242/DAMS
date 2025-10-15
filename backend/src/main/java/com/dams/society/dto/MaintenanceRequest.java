package com.dams.society.dto;

import com.dams.society.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest {
    
    @NotNull(message = "Flat owner ID is required")
    private Long flatOwnerId;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    private Double amount;
    
    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;
    
    @NotBlank(message = "Payment mode is required")
    private String paymentMode;
    
    private String transactionId;
    
    @NotNull(message = "Status is required")
    private PaymentStatus status;
    
    private String remarks;
}
