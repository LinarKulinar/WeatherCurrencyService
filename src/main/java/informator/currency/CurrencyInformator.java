//package informator.currency;
//
//import informator.weather.WeatherRequestException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDate;
//import java.util.TreeSet;
//
//public class CurrencyInformator {
//
//    private CurrencyCode currencyCode;
//    CurrencyRequester currencyRequester;
//
//    private static final Logger logger = LoggerFactory.getLogger(CurrencyInformator.class);
//
//    public CurrencyInformator(CurrencyCode currencyCode) {
//        currencyRequester = new CurrencyRequester();
//        this.currencyCode = currencyCode;
//    }
//
//    /**
//     * Возвращает строку с информацией о курсе выбранной валюты currencyCode за три дня: вчера, сегодня, завтра
//     * Даты в часовом поясе выбранного города city
//     *
//     * @return Строка в формате "X руб Y коп за Z штук" в каждый из трех дней: вчера, сегодня, завтра
//     * @throws CurrencyRequestException
//     * @throws WeatherRequestException  может возникнуть при проблемах с запросом даты в заданном городе.
//     *                                  Запрашивание даты происходит через сервер с погодой.
//     */
//    public String getThreeDaysCurrencyRate() throws CurrencyRequestException {
//        logger.info(String.format("Запрошен курс валюты %s", currencyCode));
//        String rateGreeting = "Информация о валюте " + currencyCode;
//        LocalDate dateToday = LocalDate.now();
//
//        String rateYesterday;
//        String rateToday;
//        String rateTomorrow;
//        try {
//            logger.debug("Получаем информацию о курсе валюты на вчера");
//            LocalDate dateYesterday = dateToday.minusDays(1);
//            rateYesterday = "Вчера стоимость составила - " +
//                    currencyRequester.getCurrency(dateYesterday, currencyCode).toHumanReadableString();
//        } catch (MissingRequestedDateException e) {
//            rateYesterday = "На вчера валютный курс недоступен";
//        }
//        try {
//            logger.debug("Получаем информацию о курсе валюты на сегодня");
//            rateToday = "Сегодня стоимость составляет - " +
//                    currencyRequester.getCurrency(dateToday, currencyCode).toHumanReadableString();
//        } catch (MissingRequestedDateException e) {
//            rateToday = "На сегодня валютный курс недоступен";
//        }
//
//        try {
//            logger.debug("Получаем информацию о курсе валюты на завтра");
//            LocalDate dateTomorrow = dateToday.plusDays(1);
//            rateTomorrow = "Завтра стоимость составит - " +
//                    currencyRequester.getCurrency(dateTomorrow, currencyCode).toHumanReadableString();
//        } catch (MissingRequestedDateException e) {
//            rateTomorrow = "На завтра валютный курс недоступен";
//        }
//        return rateGreeting + "\n" + rateYesterday + "\n" + rateToday + "\n" + rateTomorrow;
//    }
//
//
//    /**
//     * Вовзвращает информацию в какой из дней: (вчера, сегодня, завтра) стоимость валюты имеет максимальное значение.
//     *
//     * @return Человекочитаемая строка с описанием наибольшего курса за три дня
//     * @throws CurrencyRequestException
//     */
//    public String getThreeDaysMaxCurrencyRateInfo() throws CurrencyRequestException {
//        LocalDate dateToday = LocalDate.now();
//
//        LocalDate dateYesterday = dateToday.minusDays(1);
//        LocalDate dateTomorrow = dateToday.plusDays(1);
//        TreeSet<Currency> currencyTreeSet = new TreeSet<>();
//
//        try {
//            logger.debug("Получаем информацию о курсе валюты на вчера");
//            Currency currency = currencyRequester.getCurrency(dateYesterday, currencyCode);
//            currencyTreeSet.add(currency);
//        } catch (MissingRequestedDateException e) {
//        }
//        try {
//            logger.debug("Получаем информацию о курсе валюты на сегодня");
//            Currency currency = currencyRequester.getCurrency(dateToday, currencyCode);
//            currencyTreeSet.add(currency);
//        } catch (MissingRequestedDateException e) {
//        }
//
//        try {
//            logger.debug("Получаем информацию о курсе валюты на завтра");
//            Currency currency = currencyRequester.getCurrency(dateTomorrow, currencyCode);
//            currencyTreeSet.add(currency);
//        } catch (MissingRequestedDateException e) {
//        }
//        String answer = null;
//
//        if (currencyTreeSet.size() == 0) { // Если у нас нет информации ни обо одном дне среди трех запрошенных
//            String message = "За вчера сегодня и завтра значение курса " + currencyCode + " неизвестно.";
//            logger.warn(message);
//            return message;
//        }
//
//        Currency maxRate = currencyTreeSet.last();
//        String suffixAnswer = null;
//        if (maxRate.getDate().equals(dateYesterday)) {
//            suffixAnswer = "вчера и составил - " + maxRate.toHumanReadableString();
//            logger.debug("Самый большой курс был вчера");
//        }
//        if (maxRate.getDate().equals(dateToday)) {
//            suffixAnswer = "сегодня и составляет - " + maxRate.toHumanReadableString();
//            logger.debug("Самый большой курс сегодня");
//        }
//        if (maxRate.getDate().equals(dateTomorrow)) {
//            suffixAnswer = "завтра и составит - " + maxRate.toHumanReadableString();
//            logger.debug("Самый большой курс будет завтра");
//        }
//        switch (currencyTreeSet.size()) {
//            case 1: // Если у нас есть информация только о одном дне среди трех запрошенных
//                answer = "Курс " + currencyCode + " за (вчера, сегодня, завтра) известен только на " + suffixAnswer;
//                break;
//            case 2: // Если у нас есть информация только двух днях среди трех запрошенных
//            case 3: // Если у нас есть информация о всех трех днях среди трех запрошенных
//                answer = "Максимальный курс " + currencyCode + " наблюдаем " + suffixAnswer;
//                break;
//        }
//        return answer;
//    }
//}
