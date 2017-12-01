/**
 * Created by Artem on 25.11.2017.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex
{
    public Set<String> findEmails(String text)
    {
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        Set<String> emails = new HashSet<String>();
        while(matcher.find()) {
            emails.add(matcher.group());
        }
        return emails;
    }
    public void findAtVstupInfo(String text){
        Pattern p = Pattern.compile("\\b href=\".[A-Z0-9._%+-]+\">\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        Set<String> emails = new HashSet<String>();
        while(matcher.find()) {
            emails.add(matcher.group());
        }

        for(String s : emails)
        {
            System.out.println(s);
        }
    }
}
