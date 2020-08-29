import {Injectable} from '@angular/core';
import {Toast} from '../entities/toast';

@Injectable({
  providedIn: 'root'
})
export class ToastsService {
  private readonly _toasts: Array<Toast>;
  private readonly TOASTS_LIMIT = 2;

  constructor() {
    this._toasts = new Array<Toast>();
  }

  get toasts(): Array<Toast> {
    return this._toasts;
  }

  addToast(toast: Toast): void {
    if (toast && toast.type && toast.message) {
      if (this._toasts.length > this.TOASTS_LIMIT) {
        this._toasts.shift();
      }
      this._toasts.push(toast);
    }
  }

  deleteToastByIndex(toastIndex: number): void {
    this._toasts.splice(toastIndex, 1);
  }
}
