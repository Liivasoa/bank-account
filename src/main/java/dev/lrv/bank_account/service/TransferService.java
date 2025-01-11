package dev.lrv.bank_account.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.lrv.bank_account.service.broker.TransferProducer;

import dev.lrv.bank_account.model.Transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class TransferService {

    @Autowired
    private AccountService accountService;

    private final TransferProducer producer;

    public boolean initTransfer(UUID srcId, UUID dstId, BigDecimal amount) throws InterruptedException {
        Transfer transferRequest = new Transfer(srcId, dstId, amount);
        producer.sendToValidation(transferRequest);
        return true;
    }

}
