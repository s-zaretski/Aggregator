package com.sphincs.model;

import static com.sphincs.view.utils.Constants.HH_URL_FORMAT;
import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static org.jsoup.Jsoup.connect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sphincs.vo.Vacancy;

public class HHStrategy implements Strategy {

    private static String[] invoice = new String[1];

    static {
        invoice[0] = "водитель Брест";
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
    public List<Vacancy> runInvoice(String... searchRequest) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 0;
            Document doc;

            while ((page < 100) && (doc = getDocument(page, searchRequest)) != null) {
                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (!elements.isEmpty()) {
                    elements.forEach(element -> vacancies.add(creatreVacancyFromElement(element)));
                } else {
                    break;
                }
                page++;
            }

            return vacancies;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyList();
    }

    private Document getDocument(int page, String... searchString) throws IOException {
        String url = format(HH_URL_FORMAT, searchString[0], page);
        return connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();
    }

    private Vacancy creatreVacancyFromElement(Element element) {
        String title = getTitle(element);
        String salary = getSalary(element);
        String city = getCity(element);
        String companyName = getCompanyName(element);
        String url = getURL(element);
        return new Vacancy(title, salary, city, companyName, url);
    }

    private String getURL(Element element) {
        return element.select("[data-qa=vacancy-serp__vacancy-title]")
                .attr("href");
    }

    private String getCompanyName(Element element) {
        return element.select("[data-qa=vacancy-serp__vacancy-employer]")
                .text();
    }

    private String getCity(Element element) {
        return element.select("[data-qa=vacancy-serp__vacancy-address]")
                .text();
    }

    private String getSalary(Element element) {
        return element.select("[data-qa=vacancy-serp__vacancy-compensation]")
                .text();
    }

    private String getTitle(Element element) {
        return element.select("[data-qa=vacancy-serp__vacancy-title]")
                .text();
    }

}
