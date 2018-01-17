/**
 * Created by Artem on 25.11.2017.
 */
public class Run {
    public static void  main(String ... args) {
        //Grabber_EnicInUA grabber = new Grabber_EnicInUA();
        //Grabber_VstupInfo grabberVstupInfo = new Grabber_VstupInfo();
        //Grabber_OLX grabberOLX = new Grabber_OLX();
        //GrabberLxft grabber = new GrabberLxft();

        //grabberVstupInfo.regionFinder();
        //grabberOLX.urlCollector("https://www.olx.ua/rabota/nachalo-karery-studenty/?search%5Bfilter_enum_job_type%5D%5B0%5D=temp");
        grabber.collectForToken("LXFT");
    }
}
