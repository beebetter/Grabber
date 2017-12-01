/**
 * Created by Artem on 25.11.2017.
 */
public class Run {
    public static void  main(String ... args) {
        //Grabber_EnicInUA grabber = new Grabber_EnicInUA();
        Grabber grabber = new Grabber();
        //grabber.grab();
        //grabber.pageParse("http://www.vstup.info/2017/i2017i318.html");
        //grabber.vnzFinder("http://www.vstup.info/2017/i2017o12.html");
        grabber.regionFinder();
    }
}
