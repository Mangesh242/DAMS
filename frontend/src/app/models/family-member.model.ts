export interface FamilyMember {
  id?: number;
  firstName: string;
  lastName: string;
  relationship: string;
  phoneNumber: string;
  email?: string;
  age?: number;
  flatOwnerId: number;
  flatNumber?: string;
}
