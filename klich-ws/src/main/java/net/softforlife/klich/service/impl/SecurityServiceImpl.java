package net.softforlife.klich.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;

import net.softforlife.klich.common.Generator;
import net.softforlife.klich.service.encrypt.SecurityService;

import org.jasypt.encryption.pbe.PBEStringEncryptor;

public class SecurityServiceImpl implements SecurityService {
	private static final String MESSAGE_CHARSET = "UTF-8";
	
	private PBEStringEncryptor encryptor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.ine.tich.encrypt.SecurityService#encryptText(java.lang.String)
	 */

	public String encryptText(String text) {
		return encryptor.encrypt(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.ine.tich.encrypt.SecurityService#decryptText(java.lang.String)
	 */

	public String decryptText(String text) {
		return encryptor.decrypt(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.ine.tich.encrypt.SecurityService#generateLicense()
	 */

	public String generateLicense() {
		return Generator.generateLicense();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.ine.tich.encrypt.SecurityService#generatePassword()
	 */

	public String generatePassword() {
		return Generator.generatePassword();
	}
	
	public String encodeMD5(String str) {
		String result = null;
		
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			
			messageDigest.update(str.getBytes(), 0, str.length());
			result = new BigInteger(1, messageDigest.digest()).toString(16); 
			
			if (result.length() < 32) {
				result = "0" + result; 
			}
			
			//messageDigest.update(str.getBytes(MESSAGE_CHARSET_2));
			//final byte[] resultByte = messageDigest.digest();
			//result = new String(resultByte,MESSAGE_CHARSET_2);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	    return result;
	}	

	/**
	 * @return the encryptor
	 */
	public PBEStringEncryptor getEncryptor() {
		return encryptor;
	}

	/**
	 * @param encryptor
	 *            the encryptor to set
	 */
	public void setEncryptor(PBEStringEncryptor encryptor) {
		this.encryptor = encryptor;
	}

}
