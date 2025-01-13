package dev.lrv.bank_account.domain.service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class AccountService {

    public boolean deposit(UUID accountId, BigDecimal amount) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("########### Dépôt de %d euro sur le compte %s %n", amount.intValue(), accountId);
        return true;
    }

    public boolean withdraw(UUID accountId, BigDecimal amount) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("########### Retrait de %d euro sur le compte %s %n", amount.intValue(), accountId);
        return true;
    }

    public boolean validateAccount(UUID accountId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.printf("########### Compte %s validé %n", accountId);
        return true;
    }

    public boolean notifyUser(UUID accountId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.printf("########### Le compte %s a été notifié sur la réussite de son virement %n", accountId);
        return true;
    }

}
