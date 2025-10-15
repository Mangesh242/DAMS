export interface FlatOwner {
  id?: number;
  username: string;
  password?: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  flatNumber: string;
  wing: string;
  floor: number;
  maintenanceAmount: number;
  active?: boolean;
}
