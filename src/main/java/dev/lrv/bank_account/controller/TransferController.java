package dev.lrv.bank_account.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.lrv.bank_account.service.TransferService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transfer;

    @PostMapping("/execute")
    public boolean execute(@RequestParam BigDecimal amount) throws InterruptedException {

        return transfer.executeTransfer(UUID.randomUUID(), UUID.randomUUID(), amount);
    }

    @PostMapping("/init")
    public String init(@RequestParam BigDecimal amount) throws InterruptedException {
        transfer.initTransfer(UUID.randomUUID(), UUID.randomUUID(), amount);
        return "Virement initié avec succès !";
    }

}
