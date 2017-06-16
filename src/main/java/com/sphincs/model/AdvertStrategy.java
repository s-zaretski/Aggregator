package com.sphincs.model;

import static com.sphincs.view.utils.Constants.ADVERT_URL_FORMAT;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static org.jsoup.Jsoup.connect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;
import com.sphincs.vo.Advert;

public class AdvertStrategy implements Strategy {

    private static String[] invoice = new String[2];

    static {
        invoice[0] = "1";
        invoice[1] = "1";
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
    public List<Advert> runInvoice(String... searchRequest) {
        List<Advert> adverts = new ArrayList<>();
        try {
            int page = 1;
            Document doc;
            ArrayList<String> request = Lists.newArrayList(searchRequest);
            request.add(valueOf(page));

            while ((page < 4)) {
                String[] searchRequestArray = request.stream()
                        .map(String::new)
                        .toArray(String[]::new);
                if ((doc = getDocument(searchRequestArray)) != null) {
                    Elements elements = doc.select("[class=add_list add_type4]");
                    if (!elements.isEmpty()) {
                        elements.forEach(element -> adverts.add(creatreAdvertFromElement(element)));
                    }
                    request.remove(2);
                    page++;
                    request.add(valueOf(page));
                }
            }
            List<Advert> sortedAdverts = adverts.stream()
                    .sorted(Comparator.comparing(Advert::getCost))
                    .filter(advert -> getCost(advert) < 220)
                    .collect(Collectors.toList());
            return sortedAdverts;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyList();
    }

    private Double getCost(final Advert advert) {
        try {
            return Double.parseDouble(advert.getCost().split(" ")[0]);
        } catch (Exception e) {
            return 0d;
        }
    }

    private Document getDocument(String... searchString) throws IOException {
        String url = format(ADVERT_URL_FORMAT, searchString);
        return connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .cookie("puid", "123")
                .referrer("none")
                .get();
    }

    private Advert creatreAdvertFromElement(Element element) {
        String date = getDate(element);
        String message = getMessage(element);
        String cost = getCost(element);
        String place = getPlace(element);
        String tags = getTags(element);
        String url = getURL(element);
        return new Advert(date, message, cost, place, tags, url);
    }

    private String getDate(Element element) {
        return element.select("[class=add_data]")
                .text();
    }

    private String getMessage(Element element) {
        return element.select("[class=add_title_wrap]")
                .text();
    }

    private String getCost(Element element) {
        return element.select("[class=add_cost]")
                .text();
    }

    private String getPlace(final Element element) {
        return element.select("[class=placed]")
                .text();
    }

    private String getTags(final Element element) {
        return element.select("[class=tags]")
                .text();
    }

    private String getURL(Element element) {
        return element.select("[class=add_title]")
                .attr("href");
    }
}
