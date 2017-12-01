/**
 * Created by Artem on 25.11.2017.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Grabber {
    public Set<String> pageParse(String url){
        Document doc = null;
        Set<String> emails = new HashSet<String>();
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
            String cssTable = "#okrArea > div:nth-child(1)";
            String info = doc.select(cssTable).text();
            info = info.replace(" [ at ] ", "@");
            Regex regex = new Regex();
            emails = regex.findEmails(info);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return emails;
    }

    public Set<String> vnzFinder(String url) {
        Document doc = null;
        Set<String> emails = new HashSet<String>();
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
            String region = "#menu-nav > li.current > a";
            System.out.print(doc.select(region).text() + " has ");
            String cssTable = "#okrArea";
            Elements AllLinks = doc.select(cssTable).select("a[href]");
            Set<Element> links = new HashSet<Element>();
            for (Element link : AllLinks) {
                links.add(link);
            }
            for (Element link : links) {
                String tmpLink = link.toString();
                tmpLink = tmpLink.substring(tmpLink.indexOf("href=\".")+8, tmpLink.indexOf(">") - 1);
                if (tmpLink.contains(".html")) {
                    emails.addAll(pageParse("http://www.vstup.info/2017/" + tmpLink));
                }
            }
            System.out.println(emails.size() + " emails.");
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return emails;
    }

    public void regionFinder() {
        Set<String> emails = new HashSet<String>();
        for (int i = 3; i <= 26; i++) {//27 - Kyiv
            String url = String.format("http://www.vstup.info/2017/i2017o%d.html", i);
            emails.addAll(vnzFinder(url));
        }
        System.out.println(String.format("Total number of emails: %d", emails.size()));
        try {
            String path = String.format("All regions without Kyiv (%d emails).txt", emails.size());
            File f = new File(path);
            f.createNewFile();
            PrintWriter out = new PrintWriter(path);
            for (String email: emails) {
                out.write(email + "\n");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
