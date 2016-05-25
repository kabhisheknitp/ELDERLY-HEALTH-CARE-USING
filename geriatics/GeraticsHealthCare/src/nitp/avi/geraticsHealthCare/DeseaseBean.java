package nitp.avi.geraticsHealthCare;

public class DeseaseBean {

	/**
	 * @param args
	 */
	private String name,symptoms,diagnosis;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getdiagnosis() {
		return diagnosis;
	}
	public void setdiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+symptoms+diagnosis;
	}

}
