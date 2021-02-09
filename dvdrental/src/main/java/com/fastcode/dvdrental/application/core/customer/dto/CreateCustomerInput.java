package com.fastcode.dvdrental.application.core.customer.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateCustomerInput {

    @NotNull(message = "active Should not be null")
    private Boolean active;

    @Length(max = 50, message = "email must be less than 50 characters")
    private String email;

    @NotNull(message = "firstName Should not be null")
    @Length(max = 50, message = "firstName must be less than 50 characters")
    private String firstName;

    @NotNull(message = "lastName Should not be null")
    @Length(max = 50, message = "lastName must be less than 50 characters")
    private String lastName;

    private Integer addressId;
    private Integer storeId;
}
