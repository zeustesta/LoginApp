import { Component, OnInit } from '@angular/core';
import { User } from '../../utils/user';
import { UserService } from '../../services/user/user.service';
import { environment } from '../../../environment/environment';

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent implements OnInit{
  errorMessage: String = '';
  user?: User;

  constructor (private userServ: UserService) {

  }

  ngOnInit(): void {
      this.userServ.getUser(environment.userId).subscribe({
        next: (userData) => {
          this.user = userData;
        },
        error: (errorData) => {
          this.errorMessage = errorData
        },
        complete: () => {
          console.info('User data OK');
        }
      })
  }
}
