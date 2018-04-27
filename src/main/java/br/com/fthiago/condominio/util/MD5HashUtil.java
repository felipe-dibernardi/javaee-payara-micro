/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe MD5HashUtil
 *
 * Essa classe é responsável por disponibilizar método para encriptação no padrão MD5
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class MD5HashUtil {

    private static MessageDigest md = null;

    /**
     * Construtor privado da classe.
     */
    private MD5HashUtil() {
        
    }

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gera o hexCode do texto a ser inserido.
     * @param text Texto do qual se quer gerar o hexCode.
     * @return Array de Chars contendo o hexCode.
     */
    private static char[] hexCodes(final byte[] text) {

        char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.getChars(hexString.length() - 2,
                    hexString.length(), hexOutput, i * 2);
        }

        return hexOutput;
    }

    /**
     * Executa a criptografia do parametro informado utilizando o padrao MD5.
     * @param pwd Dado que sera criptografado.
     * @return String contendo o dado criptografado.
     */
    public static String encript(final String pwd) {
        if (md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }
    
}
