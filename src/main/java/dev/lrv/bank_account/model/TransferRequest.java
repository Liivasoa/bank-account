package dev.lrv.bank_account.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class TransferRequest {
    private UUID srcId;
    private UUID dstId;
    private BigDecimal amount;
}
