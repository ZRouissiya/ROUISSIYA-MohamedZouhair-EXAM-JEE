export type StatutContrat = 'EN_COURS' | 'VALIDE' | 'RESILIE';

export interface ContratAssurance {
  id?: number;
  type: string;
  dateSouscription: string;
  statut: StatutContrat;
  dateValidation?: string;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
  clientId: number;
}

export interface ContratAutomobile extends ContratAssurance {
  numeroImmatriculation: string;
  marqueVehicule: string;
  modeleVehicule: string;
}

export interface ContratHabitation extends ContratAssurance {
  typeLogement: 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
  adresseLogement: string;
  superficie: number;
}

export interface ContratSante extends ContratAssurance {
  niveauCouverture: 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';
  nombrePersonnesCouvertes: number;
}