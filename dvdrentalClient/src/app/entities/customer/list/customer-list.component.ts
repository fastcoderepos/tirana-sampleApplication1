import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { ICustomer } from '../icustomer';
import { CustomerService } from '../customer.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerNewComponent } from '../new/customer-new.component';
import { BaseListComponent, Globals, listColumnType, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

import { AddressService } from 'src/app/entities/address/address.service';
import { StoreService } from 'src/app/entities/store/store.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss'],
})
export class CustomerListComponent extends BaseListComponent<ICustomer> implements OnInit {
  title = 'Customer';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public customerService: CustomerService,
    public errorService: ErrorService,
    public addressService: AddressService,
    public storeService: StoreService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, customerService, errorService);
  }

  ngOnInit() {
    this.entityName = 'Customer';
    this.setAssociation();
    this.setColumns();
    this.primaryKeys = ['customerId'];
    super.ngOnInit();
  }

  setAssociation() {
    this.associations = [
      {
        column: [
          {
            key: 'addressId',
            value: undefined,
            referencedkey: 'addressId',
          },
        ],
        isParent: false,
        descriptiveField: 'addressDescriptiveField',
        referencedDescriptiveField: 'addressId',
        service: this.addressService,
        associatedObj: undefined,
        table: 'address',
        type: 'ManyToOne',
        url: 'customers',
        listColumn: 'address',
        label: 'address',
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
        url: 'customers',
        listColumn: 'store',
        label: 'store',
      },
    ];
  }

  setColumns() {
    this.columns = [
      {
        column: 'active',
        searchColumn: 'active',
        label: 'active',
        sort: true,
        filter: true,
        type: listColumnType.Boolean,
      },
      {
        column: 'customerId',
        searchColumn: 'customerId',
        label: 'customer Id',
        sort: true,
        filter: true,
        type: listColumnType.Number,
      },
      {
        column: 'email',
        searchColumn: 'email',
        label: 'email',
        sort: true,
        filter: true,
        type: listColumnType.String,
      },
      {
        column: 'firstName',
        searchColumn: 'firstName',
        label: 'first Name',
        sort: true,
        filter: true,
        type: listColumnType.String,
      },
      {
        column: 'lastName',
        searchColumn: 'lastName',
        label: 'last Name',
        sort: true,
        filter: true,
        type: listColumnType.String,
      },
      {
        column: 'addressDescriptiveField',
        searchColumn: 'address',
        label: 'address',
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
      comp = CustomerNewComponent;
    }
    super.addNew(comp);
  }
}
