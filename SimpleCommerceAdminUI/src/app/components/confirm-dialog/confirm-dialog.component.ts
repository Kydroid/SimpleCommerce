import {Component, OnInit} from '@angular/core';
import {ConfirmDialogService} from '../../services/confirm-dialog.service';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit {

  constructor(public confirmDialogService: ConfirmDialogService) {
  }

  ngOnInit(): void {
  }

  cancel(): void {
    this.confirmDialogService.nextResult(false);
  }

  ok(): void {
    this.confirmDialogService.nextResult(true);
  }

  close(): void {
    this.confirmDialogService.close();
  }
}
