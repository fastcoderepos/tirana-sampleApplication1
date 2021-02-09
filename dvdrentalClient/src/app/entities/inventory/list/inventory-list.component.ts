import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { IInventory } from '../iinventory';
import { InventoryService } from '../inventory.service';
import { Router, ActivatedRoute } from '@angular/router';
import { InventoryNewComponent } from '../new/inventory-new.component';
import { BaseListComponent, Globals, listColumnType, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

import { FilmService } from 'src/app/entities/film/film.service';
import { StoreService } from 'src/app/entities/store/store.service';

@Component({
  selector: 'app-inventory-list',
  templateUrl: './inventory-list.component.html',
  styleUrls: ['./inventory-list.component.scss'],
})
export class InventoryListComponent extends BaseListComponent<IInventory> implements OnInit {
  title = 'Inventory';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public inventoryService: InventoryService,
    public errorService: ErrorService,
    public filmService: FilmService,
    public storeService: StoreService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, inventoryService, errorService);
  }

  ngOnInit() {
    this.entityName = 'Inventory';
    this.setAssociation();
    this.setColumns();
    this.primaryKeys = ['inventoryId'];
    super.ngOnInit();
  }

  setAssociation() {
    this.associations = [
      {
        column: [
          {
            key: 'filmId',
            value: undefined,
            referencedkey: 'filmId',
          },
        ],
        isParent: false,
        descriptiveField: 'filmDescriptiveField',
        referencedDescriptiveField: 'filmId',
        service: this.filmService,
        associatedObj: undefined,
        table: 'film',
        type: 'ManyToOne',
        url: 'inventorys',
        listColumn: 'film',
        label: 'film',
      },
      {
        column: [
          {
            key: 'storeId',
            value: undefined,
            referencedkey: 'storeId',
          },
        ],
        isParent: false,
        descriptiveField: 'storeDescriptiveField',
        referencedDescriptiveField: 'storeId',
        service: this.storeService,
        associatedObj: undefined,
        table: 'store',
        type: 'ManyToOne',
        url: 'inventorys',
        listColumn: 'store',
        label: 'store',
      },
    ];
  }

  setColumns() {
    this.columns = [
      {
        column: 'inventoryId',
        searchColumn: 'inventoryId',
        label: 'inventory Id',
        sort: true,
        filter: true,
        type: listColumnType.Number,
      },
      {
        column: 'filmDescriptiveField',
        searchColumn: 'film',
        label: 'film',
        sort: true,
        filter: true,
        type: listColumnType.String,
      },
      {
        column: 'storeDescriptiveField',
        searchColumn: 'store',
        label: 'store',
        sort: true,
        filter: true,
        type: listColumnType.String,
      },
      {
        column: 'actions',
        label: 'Actions',
        sort: false,
        filter: false,
        type: listColumnType.String,
      },
    ];
    this.selectedColumns = this.columns;
    this.displayedColumns = this.columns.map((obj) => {
      return obj.column;
    });
  }
  addNew(comp) {
    if (!comp) {
      comp = InventoryNewComponent;
    }
    super.addNew(comp);
  }
}
