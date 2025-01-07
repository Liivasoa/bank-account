package dev.lrv.bank_account.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean executeTransfer(UUID srcId, UUID dstId, BigDecimal amount) throws InterruptedException {
        if (this.getAccountService().validateAccount(srcId) && this.getAccountService().validateAccount(dstId)) {
            if (this.getAccountService().withdraw(srcId, amount) && this.getAccountService().deposit(dstId, amount)) {
                return this.getAccountService().notifyUser(srcId);
            }
        }
        return false;
    }

}
