package com.fastcode.dvdrental.application.core.customer.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindCustomerByIdOutput {

    private Boolean active;
    private Integer customerId;
    private String email;
    private String firstName;
    private String lastName;
    private Integer addressId;
    private Integer addressDescriptiveField;
    private Integer storeId;
    private Integer storeDescriptiveField;
    private Long versiono;
}
