package com.fastcode.dvdrental.application.core.payment.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCustomerOutput {

    private Boolean active;
    private Integer customerId;
    private String email;
    private String firstName;
    private String lastName;
    private Integer paymentPaymentId;
}
