import { Component,computed,inject } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { BadgeModule } from 'primeng/badge';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { CartService } from "./cart/data-access/cart.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, BadgeModule, PanelMenuComponent],
})
export class AppComponent {
  title = "ALTEN SHOP";

  private readonly cartService = inject(CartService);
  private router = inject(Router);
  private readonly cart = this.cartService.cart;

  public readonly cartTotalQuantity = computed( () => 
    this.cart().items.reduce((sum, item) => sum = sum + item.quantity, 0)
  )

  public goToCart(){
    this.router.navigate(['/cart/list']);
  }
}
