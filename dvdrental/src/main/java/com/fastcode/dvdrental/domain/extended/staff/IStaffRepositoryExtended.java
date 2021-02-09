package com.fastcode.dvdrental.domain.extended.staff;

import com.fastcode.dvdrental.domain.core.staff.IStaffRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("staffRepositoryExtended")
public interface IStaffRepositoryExtended extends IStaffRepository {
    //Add your custom code here
}
