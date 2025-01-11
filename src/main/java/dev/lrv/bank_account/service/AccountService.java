package dev.lrv.bank_account.service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import dev.lrv.bank_account.NotificationHandler;
import dev.lrv.bank_account.model.Transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class AccountService {

    private final NotificationHandler notificationHandler;

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

    public boolean notifyUser(Transfer transfer) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);

        StringBuilder notif = new StringBuilder("Le virement de ");
        notif.append(transfer.getAmount());
        notif.append(" € a été réalisé avec succès");
        notificationHandler.sendNotification(notif.toString());

        System.out.printf("########### Le compte %s a été notifié sur la réussite de son virement %n",
                transfer.getSrcId());
        return true;
    }

}
