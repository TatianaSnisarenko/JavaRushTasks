package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "https://grc.ua/search/vacancy?text=java+%s&page=%d";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();
        Vacancy vacancy;
        int i = 0;
        Document doc;
        try {
            while (true) {

                doc = getDocument(searchString, i++);
                if (doc == null) {
                    break;
                }
                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) {
                    break;
                }
                for (Element elem : elements) {
                    vacancy = new Vacancy();
                    vacancy.setTitle(elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vacancy.setCity(elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setUrl(elem.getElementsByAttributeValueContaining("data-qa", "vacancy-serp__vacancy-title").attr("href").trim());
                    vacancy.setSiteName(URL_FORMAT);
                    String salary = elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text();
                    vacancy.setSalary(salary.length() == 0 ? "" : salary);
                    list.add(vacancy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException {

        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36").referrer("no-referrer-when-downgrade").get();
    }
}