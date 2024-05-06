package com.hcltech.hackaton.squadten.portfoliomanagement.api.portfolio.domain.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuditResponse(
        String transactionRef,
        String instrumentId,
        String instrumentName,
        String tradeType,
        LocalDateTime auditDate
) {
}
