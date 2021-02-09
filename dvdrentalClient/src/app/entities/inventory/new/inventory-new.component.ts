import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { InventoryService } from '../inventory.service';
import { IInventory } from '../iinventory';
import { Globals, BaseNewComponent, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

import { FilmService } from 'src/app/entities/film/film.service';
import { StoreService } from 'src/app/entities/store/store.service';

@Component({
  selector: 'app-inventory-new',
  templateUrl: './inventory-new.component.html',
  styleUrls: ['./inventory-new.component.scss'],
})
export class InventoryNewComponent extends BaseNewComponent<IInventory> implements OnInit {
  title: string = 'New Inventory';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<InventoryNewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public global: Globals,
    public pickerDialogService: PickerDialogService,
    public inventoryService: InventoryService,
    public errorService: ErrorService,
    public filmService: FilmService,
    public storeService: StoreService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(
      formBuilder,
      router,
      route,
      dialog,
      dialogRef,
      data,
      global,
      pickerDialogService,
      inventoryService,
      errorService
    );
  }

  ngOnInit() {
    this.entityName = 'Inventory';
    this.setAssociations();
    super.ngOnInit();
    this.setForm();
    this.checkPassedData();
    this.setPickerSearchListener();
  }

  setForm() {
    this.itemForm = this.formBuilder.group({
      filmId: ['', Validators.required],
      filmDescriptiveField: ['', Validators.required],
      storeId: ['', Validators.required],
      storeDescriptiveField: ['', Validators.required],
    });

    this.fields = [];
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
        service: this.filmService,
        label: 'film',
        descriptiveField: 'filmDescriptiveField',
        referencedDescriptiveField: 'filmId',
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
        service: this.storeService,
        label: 'store',
        descriptiveField: 'storeDescriptiveField',
        referencedDescriptiveField: 'storeId',
      },
    ];
    this.parentAssociations = this.associations.filter((association) => {
      return !association.isParent;
    });
  }

  onSubmit() {
    let inventory = this.itemForm.getRawValue();
    super.onSubmit(inventory);
  }
}
