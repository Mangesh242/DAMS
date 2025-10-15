export interface Visitor {
  id?: number;
  name: string;
  phoneNumber: string;
  hasPhoto?: boolean;
  inTime: string;
  outTime?: string;
  flatNumber: string;
  flatOwnerId: number;
  reason: string;
  approvalStatus: 'PENDING' | 'APPROVED' | 'REJECTED';
  approvalRemarks?: string;
  approvalDate?: string;
}
