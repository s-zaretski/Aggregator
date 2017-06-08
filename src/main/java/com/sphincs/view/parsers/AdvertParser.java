package com.sphincs.view.parsers;

import static com.sphincs.view.utils.Constants.FILE_PATH_TEMPLATE_ADVERT;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.select.Elements;

import com.sphincs.view.utils.FileHandler;
import com.sphincs.vo.Advert;

public class AdvertParser {

    private File outputFile;
    private FileHandler fileHandler;

    public AdvertParser() {
        fileHandler = new FileHandler();
    }

    public void produceAdvertFile(final List<Advert> adverts) {
        StringBuilder body = new StringBuilder();
        try {
            Elements headEl = fileHandler.getDocument(FILE_PATH_TEMPLATE_ADVERT)
                    .select("head");
            Elements bodyEl = fileHandler.getDocument(FILE_PATH_TEMPLATE_ADVERT)
                    .select("body");
            Elements htmlTemplate = fileHandler.getDocument(FILE_PATH_TEMPLATE_ADVERT)
                    .select("[class=advert template]");

            for (Advert advert : adverts) {
                Elements elements = htmlTemplate.clone();
                elements.select("[class=message]")
                        .last()
                        .text(advert.getMessage());
                elements.select("[class=cost]")
                        .last()
                        .text(advert.getCost());
                elements.select("[class=place]")
                        .last()
                        .text(advert.getPlace());
                elements.select("[class=tags]")
                        .last()
                        .text(advert.getTags());
                elements.select("[href=url]")
                        .attr("href", advert.getUrl())
                        .last()
                        .text(advert.getDate());

                body.append(elements.outerHtml())
                        .append("\n");
            }

            bodyEl.select("[class=advert template]")
                    .before(body.toString());
            outputFile = fileHandler.createOutputFile("Advert");
            fileHandler.updateFile(outputFile, headEl.outerHtml(), true);
            fileHandler.updateFile(outputFile, bodyEl.outerHtml(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
