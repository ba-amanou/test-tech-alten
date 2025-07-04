import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ContactFormService } from 'app/contact/data-access/contact-form.service';
import { ContactForm } from 'app/contact/data-access/contact-form.model';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.scss',
  imports: [ReactiveFormsModule, InputTextModule, InputTextareaModule, ButtonModule, ToastModule]
})
export class ContactFormComponent {

  private readonly formBuilder = inject(FormBuilder);
  private readonly messageService = inject(MessageService);
  private readonly contactFormService = inject(ContactFormService);
  public isSubmitting : boolean = false;

  public contactForm = this.formBuilder.nonNullable.group({
    email: ['', [Validators.required, Validators.email]],
    message: ['', [Validators.required, Validators.maxLength(300)]]
  });

  public get email() {
    return this.contactForm.get('email')!;
  }

  public get message() {
    return this.contactForm.get('message')!;
  }

  public onSubmit(){
    if (this.contactForm.invalid || this.isSubmitting) return;

    this.isSubmitting = true;
    const formValue : ContactForm = {
      email: this.email.value,
      message: this.message.value
    };

    this.contactFormService.sendContactForm(formValue)
    .pipe(finalize(() => this.isSubmitting = false))
    .subscribe({
      next: () => {
        this.messageService.add({ 
          severity:'success', 
          summary:'Succès', 
          detail: 'Demande de contact envoyée'
        });
        this.contactForm.reset();
      },
      error: () => {
        this.messageService.add({ 
            severity:'error', 
            summary:'Erreur', 
            detail: 'Echec de l\'envoi du message'
          });
      },
    });
  }

}
