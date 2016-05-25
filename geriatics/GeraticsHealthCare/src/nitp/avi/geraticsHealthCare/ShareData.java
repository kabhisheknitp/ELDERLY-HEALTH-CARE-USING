package nitp.avi.geraticsHealthCare;

public class ShareData {

	    private volatile static ShareData shareData;
	    public static ShareData data(){
	    if(shareData == null){
	        synchronized (ShareData.class) {
	            if (shareData == null) {
	                shareData = new ShareData();
	            }
	        }
	    }
	    return shareData;
	    }  

	    public String Diseasename; 
	    public String site;
	    public String [] webResultDisease;
	    public String source;
	    public String webSelectedDiseaseSymptom;
	    public String webSelectedDiseaseDiagnosis;
	    public String hostAddress="http://192.168.43.230:8089";
	}

