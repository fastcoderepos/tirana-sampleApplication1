package com.fastcode.dvdrental.application.core.rental.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetStaffOutput {

    private Boolean active;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Integer staffId;
    private String username;
    private Integer rentalRentalId;
}
