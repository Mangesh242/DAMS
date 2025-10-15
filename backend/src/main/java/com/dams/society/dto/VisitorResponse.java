package com.dams.society.dto;

import com.dams.society.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private boolean hasPhoto;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private String flatNumber;
    private Long flatOwnerId;
    private String reason;
    private ApprovalStatus approvalStatus;
    private String approvalRemarks;
    private LocalDateTime approvalDate;
}
