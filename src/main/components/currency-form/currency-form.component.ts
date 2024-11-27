import { Component } from '@angular/core';
import { CurrencyService } from 'src/app/services/currency.service';

@Component({
    selector: 'app-currency-form',
    templateUrl: './currency-form.component.html',
    styleUrls: ['./currency-form.component.css']
})
export class CurrencyFormComponent {
    formData = { currency: '', name: '' };
    response: any;

    constructor(private currencyService: CurrencyService) {}

    submitForm() {
        this.currencyService.getCurrencyValue(this.formData).subscribe(
            (res) => (this.response = res),
            (err) => alert('Błąd: ' + err.error)
        );
    }
}
