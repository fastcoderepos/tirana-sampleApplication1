package com.fastcode.dvdrental.application.extended.customer;

import com.fastcode.dvdrental.application.core.customer.CustomerAppService;
import com.fastcode.dvdrental.commons.logging.LoggingHelper;
import com.fastcode.dvdrental.domain.extended.address.IAddressRepositoryExtended;
import com.fastcode.dvdrental.domain.extended.customer.ICustomerRepositoryExtended;
import com.fastcode.dvdrental.domain.extended.store.IStoreRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("customerAppServiceExtended")
public class CustomerAppServiceExtended extends CustomerAppService implements ICustomerAppServiceExtended {

    public CustomerAppServiceExtended(
        ICustomerRepositoryExtended customerRepositoryExtended,
        IAddressRepositoryExtended addressRepositoryExtended,
        IStoreRepositoryExtended storeRepositoryExtended,
        ICustomerMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(customerRepositoryExtended, addressRepositoryExtended, storeRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
