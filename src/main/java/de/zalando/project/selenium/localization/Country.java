package de.zalando.project.selenium.localization;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Locale;

public enum Country {

    DE("de", new Locale("de")),
    NL("nl", new Locale("nl")),
    FR("fr", new Locale("fr")),
    IT("it", new Locale("it")),
    UK("uk", new Locale("en")),
    AT("at", new Locale("de", "AT")),
    CH("ch", new Locale("de", "CH")),
    CHFR("chfr", new Locale("fr", "CH")),
    PL("pl", new Locale("pl")),
    BE("be", new Locale("nl", "BE")),
    BEFR("befr", new Locale("fr", "BE")),
    SE("se", new Locale("sv")),
    FI("fi", new Locale("fi")),
    DK("dk", new Locale("da")),
    ES("es", new Locale("es")),
    NO("no", new Locale("no"));

    private final String countryCode;
    private final Locale locale;

    Country(final String countryCode, final Locale locale) {
        this.countryCode = countryCode;
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static Country fromCountryCode(final String countryCode) {
        for (final Country tld : Country.values()) {
            if (tld.getCountryCode().equalsIgnoreCase(countryCode)) {
                return tld;
            }
        }
        throw new IllegalArgumentException("No country with " + countryCode + " found");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("countryCode", countryCode)
                .append("locale", locale)
                .toString();
    }
}
