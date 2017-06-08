package com.sphincs.view.utils;

public interface Constants {

    String FILE_PATH = "/home/s-zaretski/projects/aggregator/src/main/resources/";
    String FILE_PATH_TEMPLATE_VACANCY = "/home/s-zaretski/projects/aggregator/src/main/resources/template_vacancy.html";
    String FILE_PATH_TEMPLATE_CONTACT = "/home/s-zaretski/projects/aggregator/src/main/resources/template_contact.html";
    String FILE_PATH_TEMPLATE_ADVERT = "/home/s-zaretski/projects/aggregator/src/main/resources/template_advert.html";

    String HH_URL_FORMAT =
            "https://hh.ru/search/vacancy?text=%s&clusters=true&no_magic=true&enable_snippets=true&page=%s";
    String DATING_PREFIX = "https://www.mamba.ru/";
    String DATING_URL_FORMAT = DATING_PREFIX
            + "bbs/search.phtml?iAm=1&lookFor=2&ageMin=%s&ageMax=%s&location=248_249_0_0&target=0&submitSearch=Y";
    String ADVERT_URL_FORMAT =
            "http://brest.irr.by/realestate/longtime/brestskiy-r_n/brest-gorod/search/rooms=%s/roomsForRent=%s/currency=BYN/list=list/page_len60/page%s/";

}
