package com.fastcode.dvdrental.application.core.customer;

import com.fastcode.dvdrental.application.core.customer.dto.*;
import com.fastcode.dvdrental.domain.core.address.AddressEntity;
import com.fastcode.dvdrental.domain.core.customer.CustomerEntity;
import com.fastcode.dvdrental.domain.core.store.StoreEntity;
import java.time.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    CustomerEntity createCustomerInputToCustomerEntity(CreateCustomerInput customerDto);

    @Mappings(
        {
            @Mapping(source = "entity.address.addressId", target = "addressId"),
            @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),
            @Mapping(source = "entity.store.storeId", target = "storeId"),
            @Mapping(source = "entity.store.storeId", target = "storeDescriptiveField"),
        }
    )
    CreateCustomerOutput customerEntityToCreateCustomerOutput(CustomerEntity entity);

    CustomerEntity updateCustomerInputToCustomerEntity(UpdateCustomerInput customerDto);

    @Mappings(
        {
            @Mapping(source = "entity.address.addressId", target = "addressId"),
            @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),
            @Mapping(source = "entity.store.storeId", target = "storeId"),
            @Mapping(source = "entity.store.storeId", target = "storeDescriptiveField"),
        }
    )
    UpdateCustomerOutput customerEntityToUpdateCustomerOutput(CustomerEntity entity);

    @Mappings(
        {
            @Mapping(source = "entity.address.addressId", target = "addressId"),
            @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),
            @Mapping(source = "entity.store.storeId", target = "storeId"),
            @Mapping(source = "entity.store.storeId", target = "storeDescriptiveField"),
        }
    )
    FindCustomerByIdOutput customerEntityToFindCustomerByIdOutput(CustomerEntity entity);

    @Mappings({ @Mapping(source = "foundCustomer.customerId", target = "customerCustomerId") })
    GetStoreOutput storeEntityToGetStoreOutput(StoreEntity store, CustomerEntity foundCustomer);

    @Mappings(
        {
            @Mapping(source = "address.address", target = "address"),
            @Mapping(source = "foundCustomer.customerId", target = "customerCustomerId"),
        }
    )
    GetAddressOutput addressEntityToGetAddressOutput(AddressEntity address, CustomerEntity foundCustomer);
}
