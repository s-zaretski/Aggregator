package com.sphincs.model;

import static com.sphincs.view.utils.Constants.DATING_PREFIX;
import static com.sphincs.view.utils.Constants.DATING_URL_FORMAT;
import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static org.jsoup.Jsoup.connect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sphincs.vo.Contact;

public class DatingStrategy implements Strategy {

    private static String[] invoice = new String[2];

    static {
        invoice[0] = "18";
        invoice[1] = "40";
    }

    @Override
    public String[] getInvoice() {
        return invoice;
    }

    @Override
    public void setInvoice(final String[] invoice) {
        this.invoice = invoice;
    }

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
        String url = format(DATING_URL_FORMAT, searchString);
        return connect(url)
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
        String url = DATING_PREFIX + getURL(element);
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
