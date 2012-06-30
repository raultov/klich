package net.softforlife.klich.service.impl;

import net.softforlife.klich.JSF.common.Generator;
import net.softforlife.klich.service.encrypt.SecurityService;

import org.jasypt.encryption.pbe.PBEStringEncryptor;

public class SecurityServiceImpl implements SecurityService {

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
