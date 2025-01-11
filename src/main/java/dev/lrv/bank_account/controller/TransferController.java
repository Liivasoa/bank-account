package dev.lrv.bank_account.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lrv.bank_account.model.Transfer;
import dev.lrv.bank_account.service.TransferService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transfer;

    @PostMapping("/init")
    public String init(@RequestBody Transfer t) throws InterruptedException {
        transfer.initTransfer(UUID.randomUUID(), UUID.randomUUID(), t.getAmount());
        return "Virement initié avec succès !";
    }

    @GetMapping("/health")
    public String getMethodName() {
        return "VELONA";
    }

}
