import { Routes } from "@angular/router";
import { LoginFormComponent } from "./login-form/login-form.component";

export const LOGIN_ROUTES: Routes = [
    {
        path: "login",
        component: LoginFormComponent,
    },
    { path: "**", redirectTo: "login" },
];
