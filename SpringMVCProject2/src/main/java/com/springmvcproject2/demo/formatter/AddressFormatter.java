package com.springmvcproject2.demo.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;


public class AddressFormatter implements Formatter<Address> {
    public enum Style {
        FULL,
        REGION
    }

    private Style style = Style.FULL;

    public void setStyle (Style style) {
        this.style = style;
    }

    @Override
    public Address parse(String text, Locale locale) throws ParseException {
        if (text != null) {
            String[] parts = text.split(",");
            if (style == Style.FULL && parts.length == 4) {
                Address address = new Address();
                address.setStreet(parts[0].trim());
                address.setCity(parts[1].trim());
                address.setZipCode(parts[2].trim());
                address.setCounty(parts[3].trim());
                return address;
            } else if (style == Style.REGION && parts.length == 3) {
                Address address = new Address();
                address.setCity(parts[0].trim());
                address.setZipCode(parts[1].trim());
                address.setCounty(parts[2].trim());
                return address;
            }
        }
        return null;
    }

    @Override
    public String print(Address a, Locale l) {
        if (a == null) {
            return "";
        }
        switch (style) {
            case FULL:
                return String.format(l, "%s, %s, %s, %s", a.getStreet(), a.getCity(),
                        a.getZipCode(), a.getCounty());
            case REGION:
                return String.format(l, "%s, %s, %s", a.getCity(), a.getZipCode(),
                        a.getCounty());
        }
        return a.toString();
    }
}
