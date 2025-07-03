import { Component, inject, OnInit } from '@angular/core';
import { CartService } from "app/cart/data-access/cart.service";
import { Product } from 'app/products/data-access/product.model';
import { ButtonModule } from 'primeng/button';
import { DataViewModule } from 'primeng/dataview';

@Component({
  selector: 'app-cart-list',
  standalone: true,
  imports: [DataViewModule, ButtonModule],
  templateUrl: './cart-list.component.html',
  styleUrl: './cart-list.component.scss'
})
export class CartListComponent implements OnInit {

    private readonly cartService = inject(CartService);

    public readonly cart = this.cartService.cart;

    ngOnInit(): void {
      this.cartService.getCart().subscribe();
    }

    public onDelete(product: Product) {
        this.cartService.deleteCartItem(product.id).subscribe();
    }

    public onImgError(event : Event){
      const target = event.target as HTMLImageElement;
      target.src = 'assets/images/no-image.jpg';
  }
}
