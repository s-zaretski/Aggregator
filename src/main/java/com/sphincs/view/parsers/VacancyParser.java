package com.sphincs.view.parsers;

import static com.sphincs.view.utils.Constants.FILE_PATH_TEMPLATE_VACANCY;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.select.Elements;

import com.sphincs.view.utils.FileHandler;
import com.sphincs.vo.Vacancy;

public class VacancyParser {

    private File outputFile;
    private FileHandler fileHandler;

    public VacancyParser() {
        fileHandler = new FileHandler();
    }

    public void produceVacancyFile(List<Vacancy> vacancies) {
        StringBuilder body = new StringBuilder();
        try {
            Elements headEl = fileHandler.getDocument(FILE_PATH_TEMPLATE_VACANCY)
                    .select("head");
            Elements bodyEl = fileHandler.getDocument(FILE_PATH_TEMPLATE_VACANCY)
                    .select("body");
            Elements htmlTemplate = fileHandler.getDocument(FILE_PATH_TEMPLATE_VACANCY)
                    .select("[class=vacancy template]");

            for (Vacancy vacancy : vacancies) {
                Elements elements = htmlTemplate.clone();
                elements.select("[class=city]")
                        .last()
                        .text(vacancy.getCity());
                elements.select("[class=companyName]")
                        .last()
                        .text(vacancy.getCompanyName());
                elements.select("[class=salary]")
                        .last()
                        .text(vacancy.getSalary());
                elements.select("[href=url]")
                        .attr("href", vacancy.getUrl())
                        .last()
                        .text(vacancy.getTitle());
                if (elements.select("[class=title]")
                        .text()
                        .toLowerCase()
                        .contains("международник")) {
                    continue;
                }
                body.append(elements.outerHtml())
                        .append("\n");
            }

            bodyEl.select("[class=vacancy template]")
                    .before(body.toString());
            outputFile = fileHandler.createOutputFile("Vacancy");
            fileHandler.updateFile(outputFile, headEl.outerHtml(), true);
            fileHandler.updateFile(outputFile, bodyEl.outerHtml(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
