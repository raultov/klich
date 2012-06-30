package net.softforlife.klich.JSF.common;

import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Generator.
 */
public class Generator {

    /**
     * Constructor de la clase Generator.
     */
    public Generator() {
    }

    /**
     * generateLicense.
     * 
     * @return el valor actual de String
     */
    public static String generateLicense() {
        String strRetVal = "";
        String strTemp = "";
        try {
            // Get IPAddress Segment
            InetAddress addr = InetAddress.getLocalHost();

            byte[] ipaddr = addr.getAddress();
            for (int i = 0; i < ipaddr.length; i++) {
                Byte b = new Byte(ipaddr[i]);

                strTemp = Integer.toHexString(b.intValue() & 0x000000ff);
                while (strTemp.length() < 2) {
                    strTemp = '0' + strTemp;
                }
                strRetVal += strTemp;
            }

            //strRetVal += ':';

            //Get CurrentTimeMillis() segment
            strTemp = Long.toHexString(System.currentTimeMillis());
            while (strTemp.length() < 12) {
                strTemp = '0' + strTemp;
            }
            strRetVal = strTemp.substring(7) + '-';



            //strRetVal += strTemp.substring(4) + ':';
            strRetVal += generatePassword();

            //Get IdentityHash() segment
            strTemp = Long.toHexString(System.identityHashCode(new Generator()));
            while (strTemp.length() < 8)
            {
            strTemp = '0' + strTemp;
            }
            strRetVal += "-" + strTemp;

        } catch (java.net.UnknownHostException ex) {
            ex.printStackTrace();
        }
        
        return strRetVal.toUpperCase();
    }

    /**
     * generatePassword.
     * 
     * @return el valor actual de String
     */
    
    public static String generatePassword() {
        //Get Random Segment
        SecureRandom prng = null;
        try {
            prng = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }

        String strTemp = Integer.toHexString(prng.nextInt());
        while (strTemp.length() < 8) {
            strTemp = '0' + strTemp;
        }

        return strTemp.substring(4).toUpperCase();
    }

    /**
     * Metodo principal.
     * 
     * @param args parametros de entrada.
     * 
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            long lngStart = System.currentTimeMillis();

             System.out.println(Generator.generateLicense());
            System.out.println(Generator.generatePassword());
            System.out.println("Elapsed time: " + (System.currentTimeMillis() - lngStart));
        }
    }
}