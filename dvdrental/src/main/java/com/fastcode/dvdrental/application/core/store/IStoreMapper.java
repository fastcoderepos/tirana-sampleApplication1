package com.fastcode.dvdrental.application.core.store;

import com.fastcode.dvdrental.application.core.store.dto.*;
import com.fastcode.dvdrental.domain.core.address.AddressEntity;
import com.fastcode.dvdrental.domain.core.store.StoreEntity;
import java.time.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IStoreMapper {
    StoreEntity createStoreInputToStoreEntity(CreateStoreInput storeDto);

    @Mappings(
        {
            @Mapping(source = "entity.address.addressId", target = "addressId"),
            @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),
        }
    )
    CreateStoreOutput storeEntityToCreateStoreOutput(StoreEntity entity);

    StoreEntity updateStoreInputToStoreEntity(UpdateStoreInput storeDto);

    @Mappings(
        {
            @Mapping(source = "entity.address.addressId", target = "addressId"),
            @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),
        }
    )
    UpdateStoreOutput storeEntityToUpdateStoreOutput(StoreEntity entity);

    @Mappings(
        {
            @Mapping(source = "entity.address.addressId", target = "addressId"),
            @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),
        }
    )
    FindStoreByIdOutput storeEntityToFindStoreByIdOutput(StoreEntity entity);

    @Mappings(
        {
            @Mapping(source = "address.address", target = "address"),
            @Mapping(source = "foundStore.storeId", target = "storeStoreId"),
        }
    )
    GetAddressOutput addressEntityToGetAddressOutput(AddressEntity address, StoreEntity foundStore);
}
