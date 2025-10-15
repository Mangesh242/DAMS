export interface Tenant {
  id?: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  leaseStartDate: string;
  leaseEndDate: string;
  rentAmount: number;
  idProofType: string;
  idProofNumber: string;
  flatOwnerId: number;
  flatNumber?: string;
  active?: boolean;
}
