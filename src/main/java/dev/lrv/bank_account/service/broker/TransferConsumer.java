package dev.lrv.bank_account.service.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import dev.lrv.bank_account.model.Transfer;
import dev.lrv.bank_account.service.AccountService;

@Service
public class TransferConsumer {

    @Autowired
    private TransferProducer transferProducer;

    @Autowired
    private AccountService accountService;

    @KafkaListener(topics = "transfer-validation", groupId = "test-group")
    public void validateTransfer(Transfer transfer) throws InterruptedException {

        if (accountService.validateAccount(transfer.getSrcId())
                && accountService.validateAccount(transfer.getDstId())) {
            transferProducer.sendToExecution(transfer);
        }

    }

    @KafkaListener(topics = "transfer-execution", groupId = "test-group")
    public void executeTransfer(Transfer transfer) throws InterruptedException {
        if (accountService.withdraw(transfer.getSrcId(), transfer.getAmount())
                && accountService.deposit(transfer.getDstId(), transfer.getAmount())) {
            transferProducer.sendToNotification(transfer);
        }
    }

    @KafkaListener(topics = "transfer-notification", groupId = "test-group")
    public void notifyUser(Transfer transfer) throws InterruptedException {
        accountService.notifyUser(transfer.getSrcId());
    }

}
