export interface Paiement {
  id?: number;
  date: string;
  montant: number;
  type: 'MENSUALITE' | 'PAIEMENT_ANNUEL' | 'PAIEMENT_EXCEPTIONNEL';
  contratId?: number;
}