package com.hcltech.hackaton.squadten.portfoliomanagement.api.portfolio.domain.listener;

import com.hcltech.hackaton.squadten.portfoliomanagement.api.portfolio.domain.entity.Audit;
import com.hcltech.hackaton.squadten.portfoliomanagement.api.portfolio.domain.service.AuditService;
import com.hcltech.hackaton.squadten.portfoliomanagement.connectors.kafka.AuditMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerListener {

    private final AuditService auditService;

    @KafkaListener(topics = "audit", groupId = "${kafka-consumer-group-id-audit}",
            containerFactory = "kafkaListenerContainerObjectFactory")
    public void comsumerJson(AuditMessage auditMessage) {

        log.info("Consumer audit message: " + auditMessage);
        Audit audit = new Audit();
        audit.setId(String.valueOf(auditMessage.getId()));
        audit.setPortfolioId(auditMessage.getPortfolioId());
        audit.setInstrumentId(auditMessage.getInstrumentId());
        audit.setUnit(String.valueOf(auditMessage.getUnits()));
        audit.setTransactionRef(auditMessage.getTransactionRef());
        auditService.saveAudit(audit);
    }
}
