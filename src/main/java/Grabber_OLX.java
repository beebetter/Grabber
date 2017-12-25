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

public class Grabber_OLX {
    public void pageParse(String url){
        Document doc = null;
        String cssText = "#textContent";
        String cssTitle = "#offerdescription > div.offer-titlebox > h1";
        String cssPlace = "#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a";
        String cssDate = "#offerdescription > div.offer-titlebox > div.offer-titlebox__details > em";
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
            String text = doc.select(cssText).text();
            String title = doc.select(cssTitle).text();
            String place = doc.select(cssPlace).text();
            String date = doc.select(cssDate).text();
            date = dateModifier(date);
            System.out.println("1 - " + title);
            System.out.println("2 - " + text);
            System.out.println("3 - " + place);
            System.out.println("4 - " + date);

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    public String dateModifier(String text) {
        int p1 = 13;// - "Добавлено: в "
        int p2 = text.indexOf(",");
        String time = text.substring(p1, p2);
        text =text.substring(p2 + 1);
        int p3 = text.indexOf(",");
        String date = text.substring(1, p3);
        return date + " | " + time;
    }
    public Set<String> urlFinder(String url) {
        System.out.println(">>>>-" + url);
        Document doc = null;
        Set<String> links = new HashSet<String>();
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
            String cssTable = String.format("#offers_table");
            Elements AllLinks = doc.select(cssTable).select("a[href]");
            for (Element link : AllLinks) {
                String tmpLink = link.toString();
                int p1 = tmpLink.indexOf("href=\"https://www.olx.ua/obyavlenie/") + 6;
                int p2 = tmpLink.indexOf(".html") + 5;
                if (p1 == 5 || p2 == 4)
                    continue;
                tmpLink = tmpLink.substring(p1, p2);
                links.add(tmpLink);
                System.out.println("  >>-" + tmpLink);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return links;
    }
    public Set<String> urlCollector(String url) {
        Set<String> links = new HashSet<String>();
        Set<String> linksFromPage = new HashSet<String>();
        int i = 0;
        do {
            i++;
            linksFromPage = urlFinder(url + "&page=" + i);
            links.addAll(linksFromPage);
        } while (linksFromPage.size() > 0);
        for (String link:links
             ) {
            System.out.println(link);

        }
        return links;
    }

}
