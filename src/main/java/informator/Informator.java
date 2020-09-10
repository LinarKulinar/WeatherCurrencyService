package informator;

import informator.currency.CurrencyCode;
import informator.currency.CurrencyRequestException;
import informator.weather.CityValidationException;
import informator.weather.WeatherRequestException;

import java.time.LocalTime;

public interface Informator {
    /**
     * Возвращает время в выбранном городе (на момент запроса).
     */
    String getTimeInCurrentCity(String city) throws WeatherRequestException, CityValidationException;

    /**
     * Возвращает информацию  о погоде в выбранном городе за период времени
     * (сейчас, вчера в это же время и завтра ожидается):
     * температура, скорость  и направление ветра (на русском языке), описание (ветренно, ясно и тд)
     */
    String getThreeDaysWeather(String city) throws WeatherRequestException, CityValidationException;

    /**
     * Возвращает информацию  о курсе выбранной валюты (X руб Y коп за X штук)
     * за период времени (вчера, сегодня, завтра);
     */
    String getThreeDaysCurrencyRate(CurrencyCode currencyCode) throws CurrencyRequestException;

    /**
     * Вовзвращает информацию в какой из дней: (вчера, сегодня, завтра) стоимость валюты имеет максимальное значение.
     */
    String getThreeDaysMaxCurrencyRateInfo(CurrencyCode currencyCode) throws CurrencyRequestException;
}
