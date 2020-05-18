package operationPlaceStationnement;

public class PlaceStationnement {
	
	
	private Integer PlaceStationnementInteger;
	private String refPlaceStationnementString;
	private String statutString;
	
	public PlaceStationnement(String refPlaceStationnementString, String statutString) {
		this.refPlaceStationnementString = refPlaceStationnementString;
		this.statutString = statutString;
	}
	


	public Integer getPlaceStationnementInteger() {
		return PlaceStationnementInteger;
	}

	public void setPlaceStationnementInteger(Integer placeStationnementInteger) {
		PlaceStationnementInteger = placeStationnementInteger;
	}

	public String getRefPlaceStationnementString() {
		return refPlaceStationnementString;
	}

	public void setRefPlaceStationnementString(String refPlaceStationnementString) {
		this.refPlaceStationnementString = refPlaceStationnementString;
	}

	public String getStatutString() {
		return statutString;
	}

	public void setStatutString(String statutString) {
		this.statutString = statutString;
	}
	

	
}
