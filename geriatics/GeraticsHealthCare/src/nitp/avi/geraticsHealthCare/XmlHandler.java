package nitp.avi.geraticsHealthCare;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
 

public class XmlHandler {
    private List<DeseaseBean> diseases= new ArrayList<DeseaseBean>();
    private DeseaseBean disease;
    private String text;
 
    public List<DeseaseBean> getDiseases() {
        return diseases;
    }
 
    public List<DeseaseBean> parse(InputStream is) {
           try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser  parser = factory.newPullParser();
 
            parser.setInput(is, null);
 
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagname.equalsIgnoreCase("disease")) {
                        // create a new instance of employee
                        disease = new DeseaseBean();
                    }
                    break;
 
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
 
                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("disease")) {
                        // add employee object to list
                        diseases.add(disease);
                    }else if (tagname.equalsIgnoreCase("name")) {
                        disease.setName(text);
                    }  else if (tagname.equalsIgnoreCase("symptoms")) {
                        disease.setSymptoms(text);
                    } else if (tagname.equalsIgnoreCase("diagnosis")) {
                        disease.setdiagnosis(text);
                    } 
                    break;
 
                default:
                    break;
                }
                eventType = parser.next();
            }
 
        } catch (XmlPullParserException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}
 
        return diseases;
    }
}