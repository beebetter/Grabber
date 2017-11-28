/**
 * Created by Artem on 25.11.2017.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Grabber {
    public void grab() {
        String urlHead = "https://ru.osvita.ua/vnz/guide/search-17-0-0-41-292-";
        String urlTail = ".html";
        String all = "";
        for (int i = 0; i <= 11; i++) {
            grab(urlHead + i*25 + urlTail);
        }
    }
    public void grab(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
            Elements AllLinks = doc.select("a[href]");
            Set<Element> links = new HashSet<Element>();
            Set<String> pages = new HashSet<String>();
            for (Element link : AllLinks) {
                links.add(link);
            }
            for (Element link : links) {
                String tmpLink = link.attr("href");
                if (tmpLink.length() > 11 && tmpLink.length() <= 20 && tmpLink.contains("/vnz/guide/")) {
                    pages.add(tmpLink);
                }
            }
            String cssTable = "#center > article > table > tbody > tr > td.vmiddle";
            //Regex regex = new Regex();
            for (String page : pages) {
                doc = Jsoup.connect("https://ru.osvita.ua" + page).userAgent("Mozilla").timeout(10 * 1000).get();
                System.out.println(doc.select(cssTable).text());
                //regex.findEmails(doc.select(cssTable).text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
