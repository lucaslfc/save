package OperationNotification;

public class Notification {
	

	private int IdNotification;
	private String Contenu;
	private String Mail;
	private String Mobile;
	
	
	public Notification(int idNotification, String contenu, String mail, String mobile) {
		this.Contenu = contenu;
		this.Mail = mail;
		this.Mobile = mobile;
	}
	
	
	public int getIdNotification() {
		return IdNotification;
	}
	public void setIdNotification(int idNotification) {
		IdNotification = idNotification;
	}
	public String getContenu() {
		return Contenu;
	}
	public void setContenu(String contenu) {
		Contenu = contenu;
	}
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	
	
	

}
