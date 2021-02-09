package com.fastcode.dvdrental.application.extended.inventory;

import com.fastcode.dvdrental.application.core.inventory.InventoryAppService;
import com.fastcode.dvdrental.commons.logging.LoggingHelper;
import com.fastcode.dvdrental.domain.extended.film.IFilmRepositoryExtended;
import com.fastcode.dvdrental.domain.extended.inventory.IInventoryRepositoryExtended;
import com.fastcode.dvdrental.domain.extended.store.IStoreRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("inventoryAppServiceExtended")
public class InventoryAppServiceExtended extends InventoryAppService implements IInventoryAppServiceExtended {

    public InventoryAppServiceExtended(
        IInventoryRepositoryExtended inventoryRepositoryExtended,
        IFilmRepositoryExtended filmRepositoryExtended,
        IStoreRepositoryExtended storeRepositoryExtended,
        IInventoryMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(inventoryRepositoryExtended, filmRepositoryExtended, storeRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
