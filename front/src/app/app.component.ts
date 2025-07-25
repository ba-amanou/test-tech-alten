import { Component,computed,inject } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { BadgeModule } from 'primeng/badge';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { CartService } from "./cart/data-access/cart.service";
import { ToastModule } from "primeng/toast";
import { LoginService } from "./login/data-access/login.service";
import { ButtonModule } from "primeng/button";
import { ConfirmationService } from "primeng/api";
import { ConfirmDialogModule } from 'primeng/confirmdialog';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, BadgeModule, PanelMenuComponent, ToastModule, ButtonModule, ConfirmDialogModule],
})
export class AppComponent {
  title = "ALTEN SHOP";

  private readonly cartService = inject(CartService);
  private readonly loginService = inject(LoginService);
  private readonly confirmationService = inject(ConfirmationService);
  private router = inject(Router);
  private readonly cart = this.cartService.cart;

  public readonly cartTotalQuantity = computed( () => 
    this.cart().items.reduce((sum, item) => sum = sum + item.quantity, 0)
  )

  public goToCart(){
    this.router.navigate(['/cart/list']);
  }

  get isLoggedIn(): boolean {
    return this.loginService.isAuthenticated();
  }

  public onConfirmLogout() {
    this.confirmationService.confirm({
      message: 'Voulez-vous vraiment vous déconnecter ?',
      header: 'Déconnexion',
      icon: 'pi pi-sign-out',
      acceptLabel: 'Oui',
      rejectLabel: 'Annuler',
      accept: () => {
        this.loginService.logout();
        this.router.navigate(['/login']);
      }
    });
  }

}
