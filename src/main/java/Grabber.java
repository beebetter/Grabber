/**
 * Created by Artem on 25.11.2017.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Grabber {
    public void collectForToken(String token){
        try {
            Stock stock = YahooFinance.get(token);
            /*BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
            System.out.println(price);
            System.out.println(change);
            System.out.println(peg );
            System.out.println(dividend);*/
            //System.out.println(stock.getName());
            //System.out.println(stock.getStats());
            System.out.println("Revenue: " + stock.getStats().getRevenue());
            System.out.println("EBITDA: " + stock.getStats().getEBITDA());
            System.out.println("EPS: " + stock.getStats().getEps());
            //stock.print();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        /*Document doc = null;
        try {
            String url = "https://finance.yahoo.com/quote/<token>/profile?p=<token>";
            url = "https://www.bloomberg.com/quote/LXFT:US";
            //url = url.replace("<token>", token);
           // System.out.println(url);
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
            //String cssEmployees = "#Col1-0-Profile-Proxy > section > div.asset-profile-container > div > div > p.D\\28 ib\\29.Va\\28 t\\29 > strong:nth-child(8) > span";
            //String cssEmployees = "#Col1-0-Profile-Proxy > section > div.asset-profile-container > div > div ";
            //String css = "#quote-summary > div.D\\28 ib\\29.W\\28 1\\2f 2\\29.Bxz\\28 bb\\29.Pstart\\28 12px\\29.Va\\28 t\\29.ie-7_D\\28 i\\29.ie-7_Pos\\28 a\\29.smartphone_D\\28 b\\29.smartphone_W\\28 100\\25 \\29.smartphone_Pstart\\28 0px\\29.smartphone_BdB.smartphone_Bdc\\28 \\24 c-fuji-grey-c\\29 > table > tbody > tr:nth-child(2) > td.Ta\\28 end\\29.Fw\\28 b\\29.Lh\\28 14px\\29";
            String css = "#root > div > div > section.quotePageMainContent.page-modules > div:nth-child(6) > div > div.rowList__9489bc6c.left__fe2675a4 > div:nth-child(1) > div > span.fieldValue__2d582aa7";
            String cs1 = "#root > div > div > section.quotePageMainContent.page-modules > div:nth-child(6) > div > div.rowList__9489bc6c.left__fe2675a4 > div:nth-child(1) > div > span.fieldValue__2d582aa7";
            //String employees = doc.select(cssEmployees).toString();
            //System.out.println(employees);
            System.out.println(doc.select(css).toString());
        } catch (IOException e) {
            //e.printStackTrace();
        }*/
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
