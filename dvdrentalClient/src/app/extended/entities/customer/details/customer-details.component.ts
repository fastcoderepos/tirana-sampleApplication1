import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { CustomerExtendedService } from '../customer.service';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { AddressExtendedService } from 'src/app/extended/entities/address/address.service';
import { StoreExtendedService } from 'src/app/extended/entities/store/store.service';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';
import { CustomerDetailsComponent } from 'src/app/entities/customer/index';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.scss'],
})
export class CustomerDetailsExtendedComponent extends CustomerDetailsComponent implements OnInit {
  title: string = 'Customer';
  parentUrl: string = 'customer';
  //roles: IRole[];
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public global: Globals,
    public customerExtendedService: CustomerExtendedService,
    public pickerDialogService: PickerDialogService,
    public errorService: ErrorService,
    public addressExtendedService: AddressExtendedService,
    public storeExtendedService: StoreExtendedService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(
      formBuilder,
      router,
      route,
      dialog,
      global,
      customerExtendedService,
      pickerDialogService,
      errorService,
      addressExtendedService,
      storeExtendedService,
      globalPermissionService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }
}
