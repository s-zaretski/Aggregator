package com.sphincs.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sphincs.Controller;
import com.sphincs.vo.Contact;
import com.sphincs.vo.Vacancy;

public class HtmlView<T> implements View<T> {

    private final String FILE_PATH = "/home/s-zaretski/projects/aggregator/src/main/resources/";
    private final String FILE_PATH_TEMPLATE_VACANCY =
            "/home/s-zaretski/projects/aggregator/src/main/resources/template_vacancy.html";
    private final String FILE_PATH_TEMPLATE_CONTACT =
            "/home/s-zaretski/projects/aggregator/src/main/resources/template_contact.html";

    private Controller controller;
    private File outputFile;

    @Override
    public void update(List<T> responses) {
        if (responses.get(0) instanceof Vacancy) {
            produceVacancyFile(responses);
            return;
        }
        if (responses.get(0) instanceof Contact) {
            produceDatingfile(responses);
            return;
        }
    }

    private void produceVacancyFile(List<T> responses) {
        StringBuilder body = new StringBuilder();
        try {
            Elements headEl = getDocument(FILE_PATH_TEMPLATE_VACANCY).select("head");
            Elements bodyEl = getDocument(FILE_PATH_TEMPLATE_VACANCY).select("body");
            Elements htmlTemplate = getDocument(FILE_PATH_TEMPLATE_VACANCY).select("[class=vacancy template]");

            for (T response : responses) {
                Vacancy vacancy = (Vacancy) response;
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
                body.append(elements.outerHtml())
                        .append("\n");
            }

            bodyEl.select("[class=vacancy template]").before(body.toString());
            createOutputFile(FILE_PATH);
            updateFile(outputFile, headEl.outerHtml(), true);
            updateFile(outputFile, bodyEl.outerHtml(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void produceDatingfile(List<T> responses) {
        StringBuilder body = new StringBuilder();
        try {
            Elements headEl = getDocument(FILE_PATH_TEMPLATE_CONTACT).select("head");
            Elements bodyEl = getDocument(FILE_PATH_TEMPLATE_CONTACT).select("body");
            Elements htmlTemplate = getDocument(FILE_PATH_TEMPLATE_CONTACT).select("[class=contact template]");

            for (T response : responses) {
                Contact contact = (Contact) response;
                Elements elements = htmlTemplate.clone();
                elements.select("[class=age]")
                        .last()
                        .text(contact.getAge());
                elements.select("[class=reason]")
                        .last()
                        .text(contact.getReason());
                elements.select("[href=url]")
                        .attr("href", contact.getUrl())
                        .last()
                        .text(contact.getDate());
                elements.select("[class=message]")
                        .last()
                        .text(contact.getMessage());
                body.append(elements.outerHtml())
                        .append("\n");
            }

            bodyEl.select("[class=contact template]").before(body.toString());
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

    public void runInvoice(String... searchRequest) {
        controller.runInvoice(searchRequest);
    }

    private Document getDocument(String filePath) throws IOException {
        File fileTemplate = new File(filePath);
        return Jsoup.parse(fileTemplate, "utf-8");
    }

    private void createOutputFile(String path) {
        outputFile = new File(path + controller.getModel()
                .getProviders()
                .get(0)
                .getStrategy()
                .getClass()
                .getSimpleName() + ".html");
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
