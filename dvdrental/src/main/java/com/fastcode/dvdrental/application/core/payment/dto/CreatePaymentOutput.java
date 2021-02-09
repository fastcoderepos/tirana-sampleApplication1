package com.fastcode.dvdrental.application.core.payment.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentOutput {

    private Double amount;
    private LocalDate paymentDate;
    private Integer paymentId;
    private Integer customerId;
    private Integer customerDescriptiveField;
    private Integer rentalId;
    private Integer rentalDescriptiveField;
    private Integer staffId;
    private Integer staffDescriptiveField;
}
