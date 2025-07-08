import { Component, OnInit, computed, inject, signal } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { CartService } from "app/cart/data-access/cart.service";
import { Product, INVENTORY_STATUS_MAP, Severity } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { TagModule } from 'primeng/tag';
import { InputTextModule } from 'primeng/inputtext';
import { LoginService } from "app/login/data-access/login.service";

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [FormsModule, DataViewModule, ButtonModule, DialogModule, TagModule , ProductFormComponent, InputTextModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly cartService = inject(CartService);
  private readonly loginService = inject(LoginService);

  public readonly products = this.productsService.products;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);
  public readonly filterText = signal('');
  
  public readonly filteredProducts = computed(() => {
    const all = this.products();
    const filter = this.filterText().toLowerCase();

    return all.filter(product =>
      product.name.toLowerCase().includes(filter)
    );
  });

  ngOnInit() {
    this.productsService.get().subscribe();
    this.cartService.getCart().subscribe();
  }

  public get filter(): string {
    return this.filterText();
  }

  public set filter(value: string) {
    this.filterText.set(value);
  }

  public onAddCartItem(product : Product){
    this.cartService.addCartItem(product).subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  public getStatus(product: Product): string | undefined {
    return INVENTORY_STATUS_MAP[product.inventoryStatus].label;
  }

  public getSeverity(product: Product): Severity {
    return INVENTORY_STATUS_MAP[product.inventoryStatus].severity;
  }

  public onImgError(event : Event){
      const target = event.target as HTMLImageElement;
      target.src = 'assets/images/no-image.jpg';
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  public get isAdmin() : boolean {
    return this.loginService.isAdmin();
  }
}
