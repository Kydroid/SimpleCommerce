import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfirmDialogService {
  private _isVisible: boolean;
  private _title: string;
  private _content: string;
  private _confirmResponseSubject: Subject<any>;

  constructor() {
    this.resetConfirmDialog();
  }

  private resetConfirmDialog(): void {
    this._isVisible = false;
    this._title = 'title service';
    this._content = 'content service';
  }

  show(title: string, content: string): Observable<any> {
    this._isVisible = true;
    this._title = title;
    this._content = content;
    this._confirmResponseSubject = new Subject();
    return this._confirmResponseSubject.asObservable();
  }

  nextResult(result: boolean): void {
    this._confirmResponseSubject.next(result);
    this.close();
  }

  close(): void {
    this._confirmResponseSubject.complete();
    this.resetConfirmDialog();
  }

  get content(): string {
    return this._content;
  }

  get title(): string {
    return this._title;
  }

  get isVisible(): boolean {
    return this._isVisible;
  }
}
