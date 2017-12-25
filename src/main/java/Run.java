/**
 * Created by Artem on 25.11.2017.
 */
public class Run {
    public static void  main(String ... args) {
        //Grabber_EnicInUA grabber = new Grabber_EnicInUA();
        //Grabber_VstupInfo grabberVstupInfo = new Grabber_VstupInfo();
        Grabber_OLX grabberOLX = new Grabber_OLX();
        //grabber.grab();
        //grabber.pageParse("http://www.vstup.info/2017/i2017i318.html");
        //grabber.vnzFinder("http://www.vstup.info/2017/i2017o3.html");
        //grabberVstupInfo.regionFinder();
        //grabber.pageParse("https://www.olx.ua/obyavlenie/podrabotka-na-sony-playstation-4-s-igroy-fifa18-IDvW00G.html#7dc8bbefb4");
        //grabber.urlFinder("https://www.olx.ua/rabota/nachalo-karery-studenty/?search%5Bfilter_enum_job_type%5D%5B0%5D=temp");
        grabberOLX.urlCollector("https://www.olx.ua/rabota/nachalo-karery-studenty/?search%5Bfilter_enum_job_type%5D%5B0%5D=temp");
    }
}
