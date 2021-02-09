package com.fastcode.dvdrental.application.core.inventory;

import com.fastcode.dvdrental.application.core.inventory.dto.*;
import com.fastcode.dvdrental.domain.core.film.FilmEntity;
import com.fastcode.dvdrental.domain.core.inventory.InventoryEntity;
import com.fastcode.dvdrental.domain.core.store.StoreEntity;
import java.time.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IInventoryMapper {
    InventoryEntity createInventoryInputToInventoryEntity(CreateInventoryInput inventoryDto);

    @Mappings(
        {
            @Mapping(source = "entity.film.filmId", target = "filmId"),
            @Mapping(source = "entity.film.filmId", target = "filmDescriptiveField"),
            @Mapping(source = "entity.store.storeId", target = "storeId"),
            @Mapping(source = "entity.store.storeId", target = "storeDescriptiveField"),
        }
    )
    CreateInventoryOutput inventoryEntityToCreateInventoryOutput(InventoryEntity entity);

    InventoryEntity updateInventoryInputToInventoryEntity(UpdateInventoryInput inventoryDto);

    @Mappings(
        {
            @Mapping(source = "entity.film.filmId", target = "filmId"),
            @Mapping(source = "entity.film.filmId", target = "filmDescriptiveField"),
            @Mapping(source = "entity.store.storeId", target = "storeId"),
            @Mapping(source = "entity.store.storeId", target = "storeDescriptiveField"),
        }
    )
    UpdateInventoryOutput inventoryEntityToUpdateInventoryOutput(InventoryEntity entity);

    @Mappings(
        {
            @Mapping(source = "entity.film.filmId", target = "filmId"),
            @Mapping(source = "entity.film.filmId", target = "filmDescriptiveField"),
            @Mapping(source = "entity.store.storeId", target = "storeId"),
            @Mapping(source = "entity.store.storeId", target = "storeDescriptiveField"),
        }
    )
    FindInventoryByIdOutput inventoryEntityToFindInventoryByIdOutput(InventoryEntity entity);

    @Mappings({ @Mapping(source = "foundInventory.inventoryId", target = "inventoryInventoryId") })
    GetStoreOutput storeEntityToGetStoreOutput(StoreEntity store, InventoryEntity foundInventory);

    @Mappings({ @Mapping(source = "foundInventory.inventoryId", target = "inventoryInventoryId") })
    GetFilmOutput filmEntityToGetFilmOutput(FilmEntity film, InventoryEntity foundInventory);
}
