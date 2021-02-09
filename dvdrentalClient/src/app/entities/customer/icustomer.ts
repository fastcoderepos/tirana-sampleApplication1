export interface ICustomer {
  active: boolean;
  customerId: number;
  email?: string;
  firstName: string;
  lastName: string;

  addressDescriptiveField?: number;
  addressId: number;
  storeDescriptiveField?: number;
  storeId: number;
}
