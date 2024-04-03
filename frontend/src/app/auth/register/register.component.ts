import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { CommonModule } from '@angular/common';
import { RegisterRequest } from '../../utils/registerRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.registerForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]]
    })
  }

  register() {
    if (this.registerForm.invalid) {
      alert('Invalid form, check the fields.');
    } else {
      this.authService.register(this.registerForm.value as RegisterRequest).subscribe({
        next: () => {
          alert('Correctly registered, sign in.');
          this.router.navigateByUrl('/login');
        },
        error: (errorData) => {
          console.log('Error: ' + errorData);
        },
        complete: () => {
          console.log('Registration completed');
        }
      })
    }
  }

  passwordValidator(control: any) {
    if (!/(?=.*[a-z])(?=.*[A-Z])/.test(control.value)) {
      return { passwordInvalid: true };
    }
    return null;
  }

  get firstName() {
    return this.registerForm.get('firstName');
  }

  get lastName() {
    return this.registerForm.get('lastName');
  }

  get email() {
    return this.registerForm.get('email');
  }

  get password() {
    return this.registerForm.get('password');
  }
}
