package dev.lrv.bank_account.service.broker;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import dev.lrv.bank_account.model.Transfer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransferProducer {

    private static final String TOPIC_VALIDATION = "transfer-validation";
    private static final String TOPIC_EXECUTION = "transfer-execution";
    private static final String TOPIC_NOTIFICATION = "transfer-notification";

    private final KafkaTemplate<String, Transfer> kafkaTemplate;

    public void sendToValidation(Transfer transfer) {
        kafkaTemplate.send(TOPIC_VALIDATION, transfer);
    }

    public void sendToExecution(Transfer transfer) {
        kafkaTemplate.send(TOPIC_EXECUTION, transfer);
    }

    public void sendToNotification(Transfer transfer) {
        kafkaTemplate.send(TOPIC_NOTIFICATION, transfer);
    }

}
