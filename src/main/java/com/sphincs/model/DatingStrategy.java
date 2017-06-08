package com.sphincs.model;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sphincs.vo.Contact;

public class DatingStrategy implements Strategy {

    private final String PREFIX = "https://www.mamba.ru/";
    private final String URL_FORMAT = PREFIX
            + "bbs/search.phtml?iAm=1&lookFor=2&ageMin=%s&ageMax=%s&location=248_249_0_0&target=0&submitSearch=Y";

    @Override
    public List<Contact> runInvoice(String... searchRequest) {
        List<Contact> contacts = new ArrayList<>();
        try {
            Document doc = getDocument(searchRequest);
            Elements elements = doc.select("[class=b-informer b-informer_type_announcement announcement-search-item]");
            if (!elements.isEmpty()) {
                elements.forEach(element -> contacts.add(creatreContactFromElement(element)));
            }

            return contacts;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyList();
    }

    private Document getDocument(String... searchString) throws IOException {
        String url = String.format(URL_FORMAT, searchString);
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();
    }

    private Contact creatreContactFromElement(Element element) {
        String date = getDate(element);
        String[] ageArray = getAge(element).split(" ");
        String age = ageArray[0] + " " + ageArray[1] + " " + ageArray[2];
        String reason = getReason(element);
        String message = getMessage(element);
        String url = PREFIX + getURL(element);
        return new Contact(date, age, reason, message, url);
    }

    private String getDate(Element element) {
        return element.select("[class=gray9]")
                .text();
    }

    private String getAge(Element element) {
        return element.select("[class=bottom-info announcement-search-item__info]")
                .text();
    }

    private String getReason(Element element) {
        return element.select("[class=gray9 gray9_last]")
                .text();
    }

    private String getMessage(Element element) {
        return element.select("[class=announcement-link announcement-link_type_announcement]")
                .text();
    }

    private String getURL(Element element) {
        return element.select("[class=announcement-link announcement-link_type_announcement]")
                .attr("href");
    }
}
