
Module:
```ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

Component:
```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'My Angular App';
}

```

Data binding:
```ts
<!-- app.component.html -->
<img [src]="imageUrl" alt="Angular Logo">
```

```ts
// app.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  imageUrl = 'https://angular.io/assets/images/logos/angular/angular.png';
}
```


Event binding:
```ts
<!-- app.component.html -->
<button (click)="onButtonClick()">Click me!</button>
```

```ts
// app.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  onButtonClick() {
    console.log('Button clicked!');
    // Perform additional logic here
  }
}
```

EventEmitter:

```ts
import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-child',
  template: `
    <button (click)="sendMessage()">Send Message to Parent</button>
  `
})
export class ChildComponent {
  @Output() messageEvent = new EventEmitter<string>();

  sendMessage() {
    this.messageEvent.emit('Message from child');
  }
}
```

```ts
import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-child',
  template: `
    <button (click)="sendMessage()">Send Message to Parent</button>
  `
})
export class ChildComponent {
  @Output() messageEvent = new EventEmitter<string>();

  sendMessage() {
    this.messageEvent.emit('Message from child');
  }
}
```

Service:
```ts
// data.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  getData() {
    return ['Service Data 1', 'Service Data 2'];
  }
}

// app.component.ts
import { Component, OnInit } from '@angular/core';
import { DataService } from './data.service';

@Component({
  selector: 'app-root',
  template: `<ul><li *ngFor="let item of data">{{item}}</li></ul>`
})
export class AppComponent implements OnInit {
  data: string[];
  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.data = this.dataService.getData();
  }
}
```


### Lifecycle Hooks in Angular

1. **Constructor**
   - The constructor of the component class is called first when Angular creates the component.
   - Used for initializing class members and injecting dependencies.

2. **ngOnChanges**
   - Called whenever one or more input properties of the component change.
   - Used to respond to input property changes before ngOnInit is called.

3. **ngOnInit**
   - Called once after Angular has initialized all data-bound properties of a directive.
   - Used for initializing the component, retrieving data from a service, or performing other initialization tasks.

7. **ngAfterViewInit**
   - Called after Angular initializes the component's views and child views.
   - Used for initialization that relies on the component's view or child views being initialized.

9. **ngOnDestroy**
   - Called immediately before Angular destroys the component.
   - Used for cleanup tasks such as unsubscribing from observables or releasing resources.

### Order of Execution

- **Creation**: Constructor -> ngOnChanges (if any input properties change) -> ngOnInit -> ngDoCheck -> ngAfterContentInit -> ngAfterContentChecked -> ngAfterViewInit -> ngAfterViewChecked
- **Destruction**: ngOnDestroy

### Typical Use Cases

- **Constructor**: Initializing class members and injecting dependencies.
- **ngOnInit**: Fetching initial data from a service, initializing state.
- **ngDoCheck**: Performing custom change detection.
- **ngAfterContentInit** and **ngAfterViewInit**: Initializing child components, setting up third-party libraries that require access to the DOM.
- **ngOnDestroy**: Cleaning up resources, unsubscribing from observables.

### Example

```typescript
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-lifecycle',
  template: `
    <p>{{ message }}</p>
  `
})
export class LifecycleComponent implements OnInit, OnDestroy {
  message: string = 'Hello from LifecycleComponent';

  constructor() {
    console.log('Constructor called');
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    // Initialization logic, data fetching, etc.
  }

  ngOnDestroy(): void {
    console.log('ngOnDestroy called');
    // Cleanup logic, unsubscribe from observables, etc.
  }
}
```

In this example:
- `Constructor` is called first when the component is instantiated.
- `ngOnInit` is called next, where initialization tasks can be performed.
- `ngOnDestroy` is called when the component is about to be destroyed, allowing for cleanup tasks.

Understanding and utilizing these lifecycle hooks effectively can help you manage component initialization, change detection, and cleanup in Angular applications.


### Decorator vs Directive:
**Angular Directives & Decorators**

**Decorator:**

> A Decorator is a special kind of declaration that can be attached to a class declaration, method, accessor, property, or parameter. Decorators use the form @expression, where expression must evaluate to a function that will be called at runtime with information about the decorated declaration.

Here is an example decorator from the TypeScript docs called sealed:

```javascript
function sealed(constructor: Function) { 
Object.seal(constructor); 
Object.seal(constructor.prototype); 
}
```

You will note that it takes a constructor as an argument. It can be used on a class as follows:

```javascript
@sealed class Greeter { 
  greeting: string; 
  constructor(message: string) { 
    this.greeting = message; 
  } 
  greet() { 
    return “Hello, “ + this.greeting; 
  } 
}
```

**Directives:**

> Angular Directive is basically a class with a @Directive decorator. A component is also a directive-with-a-template. A @Component decorator is actually a @Directive decorator extended with template-oriented features. Whenever Angular renders a directive, it changes the DOM according to the instructions given by the directive. The directive appears within an element tag similar to attributes.

The Angular Directive can be classified into two types: **structural** and **attribute directives**.

_Structural directives alter layout by adding, removing, and replacing elements in DOM._ _Attribute directive alter the appearance or behavior of an existing element. When you include attribute directives in templates, they look like regular HTML attributes. The ngModel directive, which implements two-way data binding, is an example of an attribute directive. ngModel modifies the behavior of an existing element by setting its display property and responding to the changing events._

Notice how inside an Angular component we use the ngModel directive?

```javascript
 <label for="example-ngModel">[(ngModel)]:</label>
<input [(ngModel)]="currentItem.name" id="example-ngModel">
```

### How to Make Custom Directives and Use Them in Angular

Creating custom directives in Angular involves defining a directive class and using decorators to provide metadata about how the directive should be used. There are two main types of custom directives you can create: attribute directives and structural directives.

#### Creating an Attribute Directive

1. **Create the Directive Class**:
   - Use the `@Directive` decorator to define the directive.
   - Implement behavior in the directive class.

2. **Register the Directive**:
   - Declare the directive in an Angular module.

3. **Use the Directive**:
   - Apply the directive to elements in a template.

**Example**: Highlight Directive

**Step 1**: Create the Directive Class

```typescript
import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter') onMouseEnter() {
    this.highlight('yellow');
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.highlight(null);
  }

  private highlight(color: string | null) {
    this.renderer.setStyle(this.el.nativeElement, 'backgroundColor', color);
  }
}
```

**Step 2**: Register the Directive

```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HighlightDirective } from './highlight.directive';

@NgModule({
  declarations: [
    AppComponent,
    HighlightDirective
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

**Step 3**: Use the Directive in a Template

```html
<p appHighlight>Hover over this text to see the highlight effect.</p>
```

#### Creating a Structural Directive

Structural directives change the DOM layout by adding or removing elements.

**Example**: Custom `ngIf` Directive

**Step 1**: Create the Directive Class

```typescript
import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appIf]'
})
export class IfDirective {
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}

  @Input() set appIf(condition: boolean) {
    if (condition) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }
}
```

**Step 2**: Register the Directive

```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { IfDirective } from './if.directive';

@NgModule({
  declarations: [
    AppComponent,
    IfDirective
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

**Step 3**: Use the Directive in a Template

```html
<div *appIf="condition">This content is conditionally rendered.</div>
```

### @HostListener and @Optional

#### @HostListener

The `@HostListener` decorator in Angular is used to listen to events on the host element of the directive or component.

**Example**: Listening to Click Events

```typescript
import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appClick]'
})
export class ClickDirective {
  @HostListener('click', ['$event'])
  onClick(event: Event) {
    console.log('Element clicked', event);
  }
}
```

**Usage in Template**:

```html
<button appClick>Click me</button>
```

In this example, `@HostListener('click', ['$event'])` listens to click events on the host element where the `appClick` directive is applied.

#### @Optional

The `@Optional` decorator in Angular is used to indicate that a dependency is optional. If the dependency is not available, Angular will pass `null` instead of throwing an error.

**Example**: Optional Dependency Injection

```typescript
import { Injectable, Optional } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OptionalService {
  getMessage() {
    return 'This is an optional service';
  }
}

@Injectable({
  providedIn: 'root'
})
export class ConsumerService {
  constructor(@Optional() private optionalService: OptionalService) {}

  useService() {
    if (this.optionalService) {
      console.log(this.optionalService.getMessage());
    } else {
      console.log('OptionalService is not available.');
    }
  }
}
```

In this example, `@Optional()` makes the `optionalService` dependency optional. If `OptionalService` is not provided, `optionalService` will be `null`.

### Routing:
Great! Let's start with **Routing** in Angular and make this interactive. I'll explain the basics and internal workings first, then you can ask any questions you have before we move on to the next topic.

### Routing in Angular

**Example**:

```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

In your `app.module.ts`, you need to import the `AppRoutingModule`:

```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

#### Internal Workings

1. **Router Outlet**:
    - Acts as a placeholder for the routed components.
    - Use `<router-outlet></router-outlet>` in your root component’s template (usually `app.component.html`).

2. **Navigation**:
    - Use the `Router` service to navigate programmatically.
    - Use `routerLink` directive for declarative navigation.

**Example**:

**app.component.html**:
```html
<nav>
  <a routerLink="/">Home</a>
  <a routerLink="/about">About</a>
</nav>
<router-outlet></router-outlet>
```

**Programmatic Navigation**:
```typescript
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  template: `<button (click)="goToAbout()">Go to About</button>`
})
export class HomeComponent {
  constructor(private router: Router) {}

  goToAbout() {
    this.router.navigate(['/about']);
  }
}
```

#### Lazy Loading

**Lazy loading** is a design pattern that loads only the necessary components, modules, or assets on demand rather than loading everything upfront. In Angular, lazy loading is implemented at the module level using the Angular Router. Instead of loading all feature modules when the application starts, lazy loading allows you to load feature modules only when the user navigates to a route that requires them.

2. **Lazy Loading**:
    - Load feature modules on demand using `loadChildren`.

**Example for Guards**:
```typescript
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    const isAuthenticated = false; // Replace with actual authentication check
    if (isAuthenticated) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
```

**Example for Lazy Loading**:
```typescript
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', loadChildren: () => import('./about/about.module').then(m => m.AboutModule) }
];
```
### Forms:
```ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

```ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  myForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.myForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      address: this.fb.group({
        street: ['', Validators.required],
        city: ['', Validators.required],
        postalCode: ['', Validators.required]
      }),
      hobbies: this.fb.array([]) // Initially empty array of hobbies
    });
  }

  onSubmit() {
    console.log('Form Submitted!', this.myForm.value);
  }

  addHobby() {
    const hobbies = this.myForm.get('hobbies') as FormArray;
    hobbies.push(this.fb.control(''));
  }
}
```

```ts
<form [formGroup]="myForm" (ngSubmit)="onSubmit()">
  <div>
    <label for="name">Name:</label>
    <input type="text" id="name" formControlName="name">
    <div *ngIf="myForm.controls.name.invalid && myForm.controls.name.touched">
      Name is required and must be at least 3 characters long.
    </div>
  </div>

  <div>
    <label for="email">Email:</label>
    <input type="email" id="email" formControlName="email">
    <div *ngIf="myForm.controls.email.invalid && myForm.controls.email.touched">
      Enter a valid email.
    </div>
  </div>

  <div formGroupName="address">
    <div>
      <label for="street">Street:</label>
      <input type="text" id="street" formControlName="street">
      <div *ngIf="myForm.get('address.street').invalid && myForm.get('address.street').touched">
        Street is required.
      </div>
    </div>

    <div>
      <label for="city">City:</label>
      <input type="text" id="city" formControlName="city">
      <div *ngIf="myForm.get('address.city').invalid && myForm.get('address.city').touched">
        City is required.
      </div>
    </div>

    <div>
      <label for="postalCode">Postal Code:</label>
      <input type="text" id="postalCode" formControlName="postalCode">
      <div *ngIf="myForm.get('address.postalCode').invalid && myForm.get('address.postalCode').touched">
        Postal Code is required.
      </div>
    </div>
  </div>

  <div formArrayName="hobbies">
    <div *ngFor="let hobby of myForm.get('hobbies').controls; let i = index">
      <label for="hobby{{i}}">Hobby {{i + 1}}:</label>
      <input type="text" [formControlName]="i">
    </div>
    <button type="button" (click)="addHobby()">Add Hobby</button>
  </div>

  <button type="submit" [disabled]="myForm.invalid">Submit</button>
</form>
```

### Pipe:
```ts
{{ today | date:'dd/MM/yyyy' }}
```

```ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'myCustomPipe' })
export class MyCustomPipe implements PipeTransform {
  transform(value: any, ...args: any[]): any {
    // Transformation logic here
    return transformedValue;
  }
}
```

HttpClient:
```ts
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient) {}

  getData(): Observable<any> {
    return this.http.get<any>('https://api.example.com/data')
      .pipe(
        map(response => response.data), // Extract data from response
        catchError(error => {
          console.error('Error fetching data:', error);
          return []; // Return empty array on error
        })
      );
  }
}
```

```ts
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, filter, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient) {}

  fetchData(): Observable<any[]> {
    return this.http.get<any[]>('https://api.example.com/data')
      .pipe(
        retry(3), // Retry up to 3 times
        map(response => response.data), // Extract 'data' property
        filter(users => users.filter(user => user.age > 30))
      );
  }
}
}
```

