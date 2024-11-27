import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CurrencyFormComponent } from './components/currency-form/currency-form.component';
import { CurrencyListComponent } from './components/currency-list/currency-list.component';

@NgModule({
    declarations: [
        AppComponent,
        CurrencyFormComponent,
        CurrencyListComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
