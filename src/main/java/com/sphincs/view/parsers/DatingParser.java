package com.sphincs.view.parsers;

import static com.sphincs.view.utils.Constants.FILE_PATH_TEMPLATE_CONTACT;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.select.Elements;

import com.sphincs.view.utils.FileHandler;
import com.sphincs.vo.Contact;

public class DatingParser {

    private File outputFile;
    private FileHandler fileHandler;

    public DatingParser() {
        fileHandler = new FileHandler();
    }

    public void produceDatingFile(List<Contact> contacts) {
        StringBuilder body = new StringBuilder();
        try {
            Elements headEl = fileHandler.getDocument(FILE_PATH_TEMPLATE_CONTACT)
                    .select("head");
            Elements bodyEl = fileHandler.getDocument(FILE_PATH_TEMPLATE_CONTACT)
                    .select("body");
            Elements htmlTemplate = fileHandler.getDocument(FILE_PATH_TEMPLATE_CONTACT)
                    .select("[class=contact template]");

            for (Contact contact : contacts) {
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

            bodyEl.select("[class=contact template]")
                    .before(body.toString());
            outputFile = fileHandler.createOutputFile("Contacts");
            fileHandler.updateFile(outputFile, headEl.outerHtml(), true);
            fileHandler.updateFile(outputFile, bodyEl.outerHtml(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
