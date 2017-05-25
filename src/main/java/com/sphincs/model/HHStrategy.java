package com.sphincs.model;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sphincs.vo.Vacancy;

public class HHStrategy implements Strategy {

    private final String URL_FORMAT =
            "https://hh.ru/search/vacancy?text=%s&clusters=true&no_magic=true&enable_snippets=true&page=%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 0;
            Document doc;

            while ((doc = getDocument(searchString, page)) != null) {
                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (!elements.isEmpty()) {
                    for (Element currentElement : elements) {
                        vacancies.add(creatreVacancyFromElement(currentElement));
                    }
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

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();
        return document;
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
