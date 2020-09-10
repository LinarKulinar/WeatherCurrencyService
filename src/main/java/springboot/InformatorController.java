
package springboot;

import informator.Informator;
import informator.InformatorImpl;
import informator.currency.CurrencyCode;
import informator.currency.CurrencyRequestException;
import informator.weather.CityValidationException;
import informator.weather.WeatherRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class InformatorController {
    private final String WEATHER_ERR = "Произошла ошибка при обращении на сервер погоды";
    private final String CURRENCY_ERR = "Произошла ошибка при обращении на сервер  курсом валюты";
    private final String CITY_ERR = "Не получислось распознать город из запроса. Заданный город не задан в системе";
    private final String CURRENCY_PARSE_ERR = "Не получислось распознать курс валюты из запроса";

    @GetMapping("/time/{city}")
    public ResponseEntity<String> showTime(@PathVariable("city") String city) {
        Informator informator = new InformatorImpl();
        try {
            return new ResponseEntity<>(informator.getTimeInCurrentCity(city), HttpStatus.OK);
        } catch (WeatherRequestException e) {
            return new ResponseEntity<>(WEATHER_ERR, HttpStatus.NOT_FOUND);
        } catch (CityValidationException e) {
            return new ResponseEntity<>(CITY_ERR, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<String> showWeather(@PathVariable("city") String city) {
        Informator informator = new InformatorImpl();
        try {
            return new ResponseEntity<>(informator.getThreeDaysWeather(city), HttpStatus.OK);
        } catch (WeatherRequestException e) {
            return new ResponseEntity<>(WEATHER_ERR, HttpStatus.NOT_FOUND);
        } catch (CityValidationException e) {
            return new ResponseEntity<>(CITY_ERR, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/currency_rate/{currency_code}")
    public ResponseEntity<String> showCurrencyRate(@PathVariable("currency_code") String currencyCode) {
        Informator informator = new InformatorImpl();
        try {
            return new ResponseEntity<>(informator.getThreeDaysCurrencyRate(CurrencyCode.valueOf(currencyCode)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(CURRENCY_PARSE_ERR, HttpStatus.BAD_REQUEST);
        } catch (CurrencyRequestException e) {
            return new ResponseEntity<>(CURRENCY_ERR, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/max_currency_rate/{currency_code}")
    public ResponseEntity<String> showMaxCurrencyRate(@PathVariable("currency_code") String currencyCode) {
        Informator informator = new InformatorImpl();
        try {
            return new ResponseEntity<>(informator.getThreeDaysMaxCurrencyRateInfo(CurrencyCode.valueOf(currencyCode)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(CURRENCY_PARSE_ERR, HttpStatus.BAD_REQUEST);
        } catch (CurrencyRequestException e) {
            return new ResponseEntity<>(CURRENCY_ERR, HttpStatus.NOT_FOUND);
        }
    }
}