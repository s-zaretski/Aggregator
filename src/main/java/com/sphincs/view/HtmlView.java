package com.sphincs.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sphincs.Controller;
import com.sphincs.vo.Vacancy;

public class HtmlView implements View {

    private final String FILE_PATH = "/home/s-zaretski/projects/aggregator/src/main/resources/";
    private final String FILE_PATH_TEMPLATE = "/home/s-zaretski/projects/aggregator/src/main/resources/template.html";

    private Controller controller;
    private File outputFile;

    @Override
    public void update(List<Vacancy> vacancies) {
        StringBuilder body = new StringBuilder();

        try {
            Elements headEl = getDocument(FILE_PATH_TEMPLATE).select("head");
            Elements bodyEl = getDocument(FILE_PATH_TEMPLATE).select("body");

            Elements htmlTemplate = getDocument(FILE_PATH_TEMPLATE).select("[class=vacancy template]");
            htmlTemplate.removeAttr("style").attr("class", "vacancy");

            for (Vacancy vacancy : vacancies) {
                Elements elements = htmlTemplate.clone();
                elements.select("[class=city]").last().text(vacancy.getCity());
                elements.select("[class=companyName]").last().text(vacancy.getCompanyName());
                elements.select("[class=salary]").last().text(vacancy.getSalary());
                elements.select("[href=url]").attr("href", vacancy.getUrl()).last().text(vacancy.getTitle());
                body.append(elements.outerHtml()).append("\n");
            }

            bodyEl.select("[class=vacancy template]").before(body.toString());
            createOutputFile(FILE_PATH);
            updateFile(outputFile, headEl.outerHtml(), true);
            updateFile(outputFile, bodyEl.outerHtml(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void findJob(String searchRequest) {
        controller.findJob(searchRequest);
    }

    private Document getDocument(String filePath) throws IOException {
        File fileTemplate = new File(filePath);
        return Jsoup.parse(fileTemplate, "utf-8");
    }

    private void createOutputFile(String path) {
        outputFile = new File(path + controller.getModel().getProviders().get(0).getStrategy().getClass().getSimpleName() + ".html");
        updateFile(outputFile, "", false);
    }

    private void updateFile(File file, String body, boolean append) {
        try {
            FileWriter writer = new FileWriter(file, append);
            writer.write(body);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
