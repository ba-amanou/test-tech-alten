import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({ providedIn: 'root' })
export class NotificationService {
  constructor(private messageService: MessageService) {}

  showSuccess(summary: string, detail: string = '') {
    this.messageService.add({
      severity: 'success',
      summary,
      detail,
      life: 4000,
    });
  }

  showError(summary: string, detail: string = '') {
    this.messageService.add({
      severity: 'error',
      summary,
      detail,
      life: 6000,
    });
  }

  showInfo(summary: string, detail: string = '') {
    this.messageService.add({
      severity: 'info',
      summary,
      detail,
      life: 4000,
    });
  }

  showWarn(summary: string, detail: string = '') {
    this.messageService.add({
      severity: 'warn',
      summary,
      detail,
      life: 4000,
    });
  }

  clear() {
    this.messageService.clear();
  }
}
