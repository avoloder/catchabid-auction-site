import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  private api = 'api/notifications';

  constructor(
    private http: HttpClient
  ) {
  }

  getNotifications():Observable<Notification[]> {
    return this.http.get<Notification[]>(this.api);
  }
}
