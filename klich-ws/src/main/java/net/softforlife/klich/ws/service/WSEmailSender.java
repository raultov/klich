package net.softforlife.klich.ws.service;

public interface WSEmailSender {
	
	public boolean send(String [] to, String subject, String body) ;
	
	public boolean sendResetPasswordEmail(String [] to, String userId, String code);
}
