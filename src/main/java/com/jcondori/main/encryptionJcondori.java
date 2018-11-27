package com.jcondori.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class encryptionJcondori {

    public String encriptar(String mensaje) {
        try {
            double[][] encriptador = new double[4][4];
            encriptador[0][0] = 13;
            encriptador[1][0] = 9;
            encriptador[2][0] = 3;
            encriptador[3][0] = 5;
            encriptador[0][1] = 2;
            encriptador[1][1] = 1;
            encriptador[2][1] = 4;
            encriptador[3][1] = 6;
            encriptador[0][2] = 4;
            encriptador[1][2] = 6;
            encriptador[2][2] = 2;
            encriptador[3][2] = 7;
            encriptador[0][3] = 8;
            encriptador[1][3] = 5;
            encriptador[2][3] = 4;
            encriptador[3][3] = 1;

            double[][] resultado = this.multiplicarMatriz(encriptador, this.converToMatrizAscii(mensaje));

            return this.convertToTextCifrado(resultado);
        } catch (Exception e) {
            return "Carracteres no validos";
        }
    }

    public String desencriptar(String mensaje) {
        double[][] desencriptador = new double[4][4];
        desencriptador[0][0] = 0.23015873015873023;
        desencriptador[1][0] = 0.10185185185185194;
        desencriptador[2][0] = -0.22883597883597884;
        desencriptador[3][0] = -0.16005291005291025;
        desencriptador[0][1] = -0.20634920634920653;
        desencriptador[1][1] = -0.24074074074074084;
        desencriptador[2][1] = 0.3201058201058201;
        desencriptador[3][1] = 0.23544973544973577;
        desencriptador[0][2] = -0.23015873015873017;
        desencriptador[1][2] = 0.0648148148148148;
        desencriptador[2][2] = 0.062169312169312166;
        desencriptador[3][2] = 0.3267195767195768;
        desencriptador[0][3] = 0.11111111111111112;
        desencriptador[1][3] = 0.12962962962962962;
        desencriptador[2][3] = -0.018518518518518517;
        desencriptador[3][3] = -0.20370370370370375;

        double[][] resultado = this.multiplicarMatriz(desencriptador, this.convertToMatrizSinMyAscii(mensaje));

        return this.convertToMensaje(resultado);

    }

    private double[][] convertToMatrizSinMyAscii(String mensaje) {
        String sinMyPintado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            sinMyPintado = sinMyPintado + this.myPintarRevert(String.valueOf(mensaje.charAt(i)));
        }
        List<String> numeros = new ArrayList();
        for (String string : sinMyPintado.split(",")) {
            numeros.add(string);
        }
        double[][] matriz = new double[4][numeros.size() / 4];
        int i = 0;
        for (int f = 0; f < matriz[0].length; f++) {
            for (int c = 0; c < matriz.length; c++) {
                matriz[c][f] = Double.valueOf(numeros.get(i));
                i++;
            }
        }
        return matriz;
    }

    private String convertToMensaje(double[][] matriz) {
        String mensaje = "";
        for (int i = 0; i < matriz[0].length; i++) {
            for (int l = 0; l < matriz.length; l++) {
                mensaje = mensaje + (char) new BigDecimal(matriz[l][i]).setScale(0, RoundingMode.HALF_EVEN).intValue();
            }
        }
        return mensaje;
    }

    private double[][] converToMatrizAscii(String mensaje) {
        double[][] matriz = new double[4][mensaje.length() % 4 == 0 ? mensaje.length() / 4 : (mensaje.length() / 4) + 1];
        int i = 0;
        for (int f = 0; f < matriz[0].length; f++) {
            for (int c = 0; c < matriz.length; c++) {
                if (i < mensaje.length()) {
                    matriz[c][f] = (int) mensaje.charAt(i);
                }
                i++;
            }
        }
        return matriz;
    }

    private String convertToTextCifrado(double[][] matriz) {
        String cifrado = "";
        for (int i = 0; i < matriz[0].length; i++) {
            for (int l = 0; l < matriz.length; l++) {
                cifrado = cifrado + matriz[l][i] + ",";
            }
        }
        cifrado = cifrado.substring(0, cifrado.length() - 1);
        String myPintado = "";
        for (int i = 0; i < cifrado.length(); i++) {
            myPintado = myPintado + this.myPintar(String.valueOf(cifrado.charAt(i)));
        }
        return myPintado;
    }

    private double[][] multiplicarMatriz(double[][] llave, double[][] mensaje) {
        double[][] result = new double[llave.length][mensaje[0].length];
        if (llave[0].length != mensaje.length) return result; // matrix multiplication is not possible
        for (int i = 0; i < llave.length; i++) {         // rows from llave
            for (int j = 0; j < mensaje[0].length; j++) {     // columns from mensaje
                for (int k = 0; k < llave[0].length; k++) { // columns from llave
                    result[i][j] += llave[i][k] * mensaje[k][j];
                }
            }
        }
        return result;
    }

    private String myPintar(String letra) {
        switch (letra) {
            case "0":
                return "☺";
            case "1":
                return "☻";
            case "2":
                return "♠";
            case "3":
                return "♣";
            case "4":
                return "♥";
            case "5":
                return "♦";
            case "6":
                return "♪";
            case "7":
                return "♫";
            case "8":
                return "☎";
            case "9":
                return "♨";
            case ",":
                return "❣";
            case ".":
                return "❢";
            default:
                return letra;
        }
    }

    private String myPintarRevert(String letra) {
        switch (letra) {
            case "☺":
                return "0";
            case "☻":
                return "1";
            case "♠":
                return "2";
            case "♣":
                return "3";
            case "♥":
                return "4";
            case "♦":
                return "5";
            case "♪":
                return "6";
            case "♫":
                return "7";
            case "☎":
                return "8";
            case "♨":
                return "9";
            case "❣":
                return ",";
            case "❢":
                return ".";
            default:
                return letra;
        }
    }

}
