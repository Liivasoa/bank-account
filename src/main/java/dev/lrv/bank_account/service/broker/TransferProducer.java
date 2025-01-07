package dev.lrv.bank_account.service.broker;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import dev.lrv.bank_account.model.TransferRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransferProducer {

    private static final String TOPIC = "transfer-request";

    private final KafkaTemplate<String, TransferRequest> kafkaTemplate;

    public void sendTransfer(TransferRequest transferRequest) {
        kafkaTemplate.send(TOPIC, transferRequest);
    }

}
