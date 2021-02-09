package com.fastcode.dvdrental.application.core.staff.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStaffOutput {

    private Boolean active;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Integer staffId;
    private String username;
    private Integer addressId;
    private Integer addressDescriptiveField;
    private Integer storeId;
    private Integer storeDescriptiveField;
}
