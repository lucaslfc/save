package OperationTarif;

public class Tarif {
	
	
	private Integer TarifInteger;
	private String libelleTarifString;
	private final static int prix = 10;
	
	public Tarif(Integer tarifInteger, String libelleTarifString) {
		TarifInteger = tarifInteger;
		this.libelleTarifString = libelleTarifString;
	}
	
	
	public Integer getTarifInteger() {
		return TarifInteger;
	}
	public void setTarifInteger(Integer tarifInteger) {
		TarifInteger = tarifInteger;
	}
	public String getLibelleTarifString() {
		return libelleTarifString;
	}
	public void setLibelleTarifString(String libelleTarifString) {
		this.libelleTarifString = libelleTarifString;
	}
	public static int getPrix() {
		return prix;
	}

}
