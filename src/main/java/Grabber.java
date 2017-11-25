/**
 * Created by Artem on 25.11.2017.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Grabber {
    public void grab() {
        String urlHead = "http://enic.in.ua/index.php/ua/uipukr?page=";
        String urlTail = "&fullName=&regionId=-&partnerTypeId=-&ownershipFormId=-&accrLevelId=4";
        String all = "";
        for (int i = 1; i <= 11; i++) {
            all += grab("http://enic.in.ua/index.php/ua/uipukr?page=" + i
                    + "&fullName=&regionId=-&partnerTypeId=-&ownershipFormId=-&accrLevelId=4");
        }
        Regex regex = new Regex();
        regex.findEmails(all);
    }
    public String grab(String url) {
        String cssTable = "body > div.holster > div.container.left-green-bg > div > div > div.content";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
            //Elements paragraphs = doc.select(cssTable);
            //String text = doc.select(cssTable).text();
            return doc.select(cssTable).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
