import { Component, OnInit } from '@angular/core';
import { User } from '../../utils/user';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent implements OnInit{
  user: User | null = null;

  constructor (private userServ: UserService) {
  }

  ngOnInit(): void {
    this.userServ.getUser().subscribe((userData) => {
      this.user = userData;
    })
  }

  editUser() {
    
  }
}
