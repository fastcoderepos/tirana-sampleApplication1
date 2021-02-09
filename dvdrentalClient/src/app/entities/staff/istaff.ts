export interface IStaff {
  active: boolean;
  email?: string;
  firstName: string;
  lastName: string;
  password?: string;
  staffId: number;
  username: string;

  addressDescriptiveField?: number;
  addressId: number;
  storeDescriptiveField?: number;
  storeId: number;
}
