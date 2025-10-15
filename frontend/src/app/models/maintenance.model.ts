export interface Maintenance {
  id?: number;
  flatOwnerId: number;
  flatNumber?: string;
  amount: number;
  paymentDate: string;
  paymentMode: string;
  transactionId?: string;
  receiptNumber?: string;
  status: 'PENDING' | 'COMPLETED' | 'FAILED' | 'REFUNDED';
  remarks?: string;
}
