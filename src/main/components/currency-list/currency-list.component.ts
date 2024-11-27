import { Component, OnInit } from '@angular/core';
import { CurrencyService } from 'src/app/services/currency.service';

@Component({
    selector: 'app-currency-list',
    templateUrl: './currency-list.component.html',
    styleUrls: ['./currency-list.component.css']
})
export class CurrencyListComponent implements OnInit {
    requests: any[] = [];

    constructor(private currencyService: CurrencyService) {}

    ngOnInit() {
        this.currencyService.getAllRequests().subscribe(
            (data) => (this.requests = data),
            (err) => alert('Błąd podczas pobierania danych: ' + err.error)
        );
    }
}
