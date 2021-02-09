import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { InventoryService } from '../inventory.service';
import { IInventory } from '../iinventory';
import { BaseDetailsComponent, Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

import { FilmService } from 'src/app/entities/film/film.service';
import { StoreService } from 'src/app/entities/store/store.service';

@Component({
  selector: 'app-inventory-details',
  templateUrl: './inventory-details.component.html',
  styleUrls: ['./inventory-details.component.scss'],
})
export class InventoryDetailsComponent extends BaseDetailsComponent<IInventory> implements OnInit {
  title = 'Inventory';
  parentUrl = 'inventory';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public global: Globals,
    public inventoryService: InventoryService,
    public pickerDialogService: PickerDialogService,
    public errorService: ErrorService,
    public filmService: FilmService,
    public storeService: StoreService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(formBuilder, router, route, dialog, global, pickerDialogService, inventoryService, errorService);
  }

  ngOnInit() {
    this.entityName = 'Inventory';
    this.setAssociations();
    super.ngOnInit();
    this.setForm();
    this.getItem();
    this.setPickerSearchListener();
  }

  setForm() {
    this.itemForm = this.formBuilder.group({
      inventoryId: [{ value: '', disabled: true }, Validators.required],
      filmId: ['', Validators.required],
      filmDescriptiveField: ['', Validators.required],
      storeId: ['', Validators.required],
      storeDescriptiveField: ['', Validators.required],
    });

    this.fields = [];
  }

  onItemFetched(item: IInventory) {
    this.item = item;
    this.itemForm.patchValue(item);
  }

  setAssociations() {
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
        table: 'film',
        type: 'ManyToOne',
        label: 'film',
        service: this.filmService,
        descriptiveField: 'filmDescriptiveField',
        referencedDescriptiveField: 'filmId',
      },
      {
        column: [
          {
            key: 'inventoryId',
            value: undefined,
            referencedkey: 'inventoryId',
          },
        ],
        isParent: true,
        table: 'rental',
        type: 'OneToMany',
        label: 'rentals',
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
        table: 'store',
        type: 'ManyToOne',
        label: 'store',
        service: this.storeService,
        descriptiveField: 'storeDescriptiveField',
        referencedDescriptiveField: 'storeId',
      },
    ];

    this.childAssociations = this.associations.filter((association) => {
      return association.isParent;
    });

    this.parentAssociations = this.associations.filter((association) => {
      return !association.isParent;
    });
  }

  onSubmit() {
    let inventory = this.itemForm.getRawValue();
    super.onSubmit(inventory);
  }
}
