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
            "https://hh.ru/search/vacancy?text=%s&enable_snippets=true&clusters=true&no_magic=true&area=%s&page=%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 0;
            Document doc;

            while ((doc = getDocument(searchString, 1007, page)) != null) {
                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (!elements.isEmpty()) {
                    for (Element currentElement : elements) {
                        String title = currentElement.select("[data-qa=vacancy-serp__vacancy-title]")
                                .text();
                        String salary = currentElement.select("[data-qa=vacancy-serp__vacancy-compensation]")
                                .text();
                        String city = currentElement.select("[data-qa=vacancy-serp__vacancy-address]")
                                .text();
                        String companyName = currentElement.select("[data-qa=vacancy-serp__vacancy-employer]")
                                .text();
                        String url = currentElement.select("[data-qa=vacancy-serp__vacancy-title]")
                                .attr("href");
                        Vacancy vacancy = new Vacancy(title, salary, city, companyName, url);
                        vacancies.add(vacancy);
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

    protected Document getDocument(String searchString, int area, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, area, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();

        return document;
    }
}
