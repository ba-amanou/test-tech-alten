import { Routes } from "@angular/router";
import { ContactFormComponent } from "./features/contact-form/contact-form.component";

export const CONTACT_ROUTES: Routes = [
    {
        path: "form",
        component: ContactFormComponent,
    },
    { path: "**", redirectTo: "form" },
];
