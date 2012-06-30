package net.softforlife.klich.service.encrypt;


/**
 * Servicio que incorpora metodos para controlar
 * la seguridad del sistema.
 *
 * @author rtovar
 */
public interface SecurityService {

    /**
     * Encripta el texto.
     *
     * @param text
     * @return Texto encriptado
     */
    String encryptText (String text);

    /**
     * Desencripta el texto
     *
     * @param text
     * @return Texto desencriptado
     */
    String decryptText (String text);

    /**
     * Genera una licencia válida para el sistema.
     *
     * @return String con la licencia
     */
    String generateLicense ();

    /**
     * Genera un password aleatorio de letras y números
     *
     * @return String con el password
     */
    String generatePassword();
}
