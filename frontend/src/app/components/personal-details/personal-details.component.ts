import { Component, OnInit } from '@angular/core';
import { User } from '../../utils/user';
import { UserService } from '../../services/user/user.service';
import { AuthService } from '../../services/auth/auth.service';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent implements OnInit{
  user: User | null = null;
  editing: boolean = false;
  editedUser: User = { id: '', firstName: '', lastName: '', email: '' };

  constructor (private userServ: UserService, private authServ: AuthService) {
  }

  ngOnInit(): void {
    this.getUserData();
  }

  getUserData() {
    this.authServ.userLoginOn.subscribe((userLoginOn) => {
      if (userLoginOn) {
        this.userServ.getUser().subscribe((userData) => {
          this.user = userData;
          this.editedUser = {...userData};
        })
      } else {
        this.user = null;
      }
    })
  }

  startEditing() {
    this.editing = true;
  }

  saveChanges() {
    // this.userServ.updateUser(this.editedUser).subscribe(() => {
    //   this.user = { ...this.editedUser };
    //   this.editing = false;
    // });
    console.log(this.editedUser);
  }

  cancelEditing() {
    this.editing = false;
    if (this.user) {
      this.editedUser = { ...this.user };
    }  
  }
}
