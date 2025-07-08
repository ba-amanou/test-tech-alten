import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import { LoginFormComponent } from "./login/features/login-form/login-form.component";
import { AuthGuard } from "./core/guards/auth.guard";

export const APP_ROUTES: Routes = [
  {
    path: "home",
    component: HomeComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "login",
    component: LoginFormComponent,
  },  
  {
    path: "products",
    canActivate: [AuthGuard],
    loadChildren: () =>
      import("./products/products.routes").then((m) => m.PRODUCTS_ROUTES)
  },
  {
    path: "cart",
    canActivate: [AuthGuard],
    loadChildren: () =>
      import("./cart/cart.routes").then((m) => m.CART_ROUTES)
  },
  {
    path: "contact",
    canActivate: [AuthGuard],
    loadChildren: () =>
      import("./contact/contact.routes").then((m) => m.CONTACT_ROUTES)
  },    
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: '**', redirectTo: "login"},
];
