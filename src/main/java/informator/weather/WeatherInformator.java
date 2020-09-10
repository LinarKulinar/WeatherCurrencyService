//package informator.weather;
//
//import java.time.LocalTime;
//
//public class WeatherInformator {
//    private String city;
//    private WeatherRequester weatherRequester;
//
//    public WeatherInformator(String city) {
//        weatherRequester = new WeatherRequester();
//        this.city = city;
//    }
//
//
//    /**
//     * Время в выбранном городе (на момент запроса).
//     * Время возвращается с точностью до 1 минуты.
//     *
//     * @return
//     * @throws WeatherRequestException
//     */
//    public LocalTime getTimeInCurrentCity() throws WeatherRequestException {
//        return weatherRequester.getLocalDateTimeInCity(city).toLocalTime();
//    }
//
//    /**
//     * Возвращает информацию  о погоде в выбранном городе за период времени
//     * (сейчас, вчера в это же время и завтра ожидается):
//     * температура, скорость  и направление ветра (на русском языке), описание (ветренно, ясно и тд)
//     */
//    public String getThreeDaysWeather() throws WeatherRequestException {
//        return weatherRequester.getWeather(city);
//    }
//
//}
