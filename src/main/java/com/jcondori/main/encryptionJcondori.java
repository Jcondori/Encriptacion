package com.jcondori.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class encryptionJcondori {

    private InversaMatriz inversa = new InversaMatriz();
    private double[][] encriptador = {{13, 2, 4, 8}, {9, 1, 6, 5}, {3, 4, 2, 4}, {5, 6, 7, 1}};

    public String encriptar(String mensaje) {
        try {

            double[][] resultado = this.multiplicarMatriz(encriptador, this.converToMatrizAscii(mensaje));

            return this.convertToTextCifrado(resultado);
        } catch (Exception e) {
            return "Carracteres no validos";
        }
    }

    public String encriptar(String mensaje, int llave) {
        try {

            double[][] encriptador = alterarMatriz(this.encriptador.clone(), llave);

            double[][] resultado = this.multiplicarMatriz(encriptador, this.converToMatrizAscii(mensaje));

            return this.convertToTextCifrado(resultado);

        } catch (Exception e) {
            return "Carracteres no validos";
        }
    }

    public String desencriptar(String mensaje) {

        double[][] resultado = this.multiplicarMatriz(inversa.matrizInversa(encriptador), this.convertToMatrizSinMyAscii(mensaje));

        return this.convertToMensaje(resultado);

    }

    public String desencriptar(String mensaje, int llave) {

        double[][] encriptador = alterarMatriz(this.encriptador.clone(), llave);

        double[][] resultado = this.multiplicarMatriz(inversa.matrizInversa(encriptador), this.convertToMatrizSinMyAscii(mensaje));

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

    private double[][] multiplicarMatrizAlterando(double[][] llave, double[][] mensaje, int alteracion) {
        double[][] result = new double[llave.length][mensaje[0].length];
        if (llave[0].length != mensaje.length) return result; // matrix multiplication is not possible
        for (int i = 0; i < llave.length; i++) {         // rows from llave
            for (int j = 0; j < mensaje[0].length; j++) {     // columns from mensaje
                for (int k = 0; k < llave[0].length; k++) { // columns from llave
                    result[i][j] += (llave[i][k] + (alteracion)) * mensaje[k][j];
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

    public void imprimirMatriz(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public double[][] alterarMatriz(double[][] mat, int llave) {
        double[][] result = new double[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                result[i][j] = mat[i][j] + (llave);
            }
        }
        return result;
    }

}
