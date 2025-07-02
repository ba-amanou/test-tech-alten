export interface Product {
    id: number;
    code: string;
    name: string;
    description: string;
    image: string;
    category: string;
    price: number;
    quantity: number;
    internalReference: string;
    shellId: number;
    inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
    rating: number;
    createdAt: number;
    updatedAt: number;
}

export type Severity = 'success' | 'secondary' | 'info' | 'warning' | 'danger' | 'contrast';

export const INVENTORY_STATUS_MAP: Record<Product['inventoryStatus'], { label: string; severity: Severity }> = {
  INSTOCK: {
    label: 'en stock',
    severity: 'success'
  },
  LOWSTOCK: {
    label: 'stock faible',
    severity: 'warning'
  },
  OUTOFSTOCK: {
    label: 'stock épuisé',
    severity: 'danger'
  }
} as const;