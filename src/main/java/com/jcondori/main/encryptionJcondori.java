package com.jcondori.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class encryptionJcondori {

    //Llave predeterminada
    private double[][] encriptador = {{13, 2, 4, 8}, {9, 1, 6, 5}, {3, 4, 2, 4}, {5, 6, 7, 1}};


    public String encriptar(String mensaje) {
        try {

            double[][] resultado = this.multiplicarMatriz(encriptador, this.converToMatrizAscii(mensaje));
//            System.out.println("Matriz llave por defecto");
//            imprimirMatriz(encriptador);
//            System.out.println("Matriz resultado");
//            imprimirMatriz(resultado);
            return this.convertToTextCifrado(resultado);
        } catch (Exception e) {
            return "Carracteres no validos";
        }
    }

    public String encriptarv2(String mensaje) {
        try {
            double[][] resultado = this.multiplicarMatriz(encriptador, this.converToMatrizAscii(mensaje));
            return this.convertToTextCifradov2(resultado);
        } catch (Exception e) {
            return "Carracteres no validos";
        }
    }

    public String encriptar(int llave, String mensaje) {
        try {

            double[][] encriptador = alterarMatriz(this.encriptador, llave);

            double[][] resultado = this.multiplicarMatriz(encriptador, this.converToMatrizAscii(mensaje));

            return this.convertToTextCifrado(resultado);

        } catch (Exception e) {
            return "Carracteres no validos";
        }
    }

    public String desencriptar(String mensaje) {

        double[][] resultado = this.multiplicarMatriz(this.matrizInversa(encriptador), this.convertToMatrizSinMyAscii(mensaje));

        return this.convertToMensaje(resultado);

    }

    public String desencriptarv2(String mensaje) {

        double[][] resultado = this.multiplicarMatriz(this.matrizInversa(encriptador), this.convertToMatrizSinMyAsciiv2(mensaje));

        return this.convertToMensaje(resultado);

    }

    public String desencriptar(int llave, String mensaje) {

        double[][] encriptador = alterarMatriz(this.encriptador, llave);

        double[][] resultado = this.multiplicarMatriz(this.matrizInversa(encriptador), this.convertToMatrizSinMyAscii(mensaje));

        return this.convertToMensaje(resultado);

    }

    //Convierte la matriz descifrada al mensaje original del usuario
    private String convertToMensaje(double[][] matriz) {
        String mensaje = "";
        for (int i = 0; i < matriz[0].length; i++) {
            for (int l = 0; l < matriz.length; l++) {
                mensaje = mensaje + (char) new BigDecimal(matriz[l][i]).setScale(0, RoundingMode.HALF_EVEN).intValue();
            }
        }
        return mensaje;
    }

    //Convierte el mensaje del usuario en una matriz con sus la letras convertidas en ascci
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
//        System.out.println("Matriz mensaje");
//        imprimirMatrizSinAcsii(matriz);
//        System.out.println("Matriz mensaje ascci");
//        imprimirMatriz(matriz);
        return matriz;
    }

    //Convierte la matriz cifrada en el texto final para el usuario
    //Usando la tabla personalizada de conversion de caracteres
    private String convertToTextCifrado(double[][] matriz) {
        String cifrado = "";
        for (int i = 0; i < matriz[0].length; i++) {
            for (int l = 0; l < matriz.length; l++) {
                cifrado = cifrado + matriz[l][i] + ",";
            }
        }
        cifrado = cifrado.substring(0, cifrado.length() - 1);
//        System.out.println(cifrado);
        String myPintado = "";
        for (int i = 0; i < cifrado.length(); i++) {
            myPintado = myPintado + this.myPintar(cifrado.charAt(i));
        }
        return myPintado;
    }

    private String convertToTextCifradov2(double[][] matriz) {
        String cifrado = "";
        for (int i = 0; i < matriz[0].length; i++) {
            for (int l = 0; l < matriz.length; l++) {
                cifrado = cifrado + matriz[l][i] + ",";
            }
        }
        cifrado = cifrado.substring(0, cifrado.length() - 1);
//        System.out.println(cifrado);
        String myPintado = "";
        for (int i = 0; i < cifrado.length(); i++) {
            myPintado = myPintado + this.myPintarNumber(cifrado.charAt(i));
        }
        return myPintado;
    }

    //Convierte el texto cifrado a una matriz cifrada con sus valores numericos
    //Usando la tabla personalizada de conversion de caracteres
    private double[][] convertToMatrizSinMyAscii(String mensaje) {
        String sinMyPintado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            sinMyPintado = sinMyPintado + this.myPintarRevert(mensaje.charAt(i));
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

    private double[][] convertToMatrizSinMyAsciiv2(String mensaje) {
        String sinMyPintado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            sinMyPintado = sinMyPintado + this.myPintarNumberRevert(mensaje.charAt(i));
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

    //Metodo para la multiplicacion de dos matrices
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

    //Convierte el munero a un caracter especificado por uno mismo
    private String myPintar(char numero) {
        switch (numero) {
            case '0':
                return "☺";
            case '1':
                return "☻";
            case '2':
                return "♠";
            case '3':
                return "♣";
            case '4':
                return "♥";
            case '5':
                return "♦";
            case '6':
                return "♪";
            case '7':
                return "♫";
            case '8':
                return "☎";
            case '9':
                return "✋";
            case ',':
                return "❣";
            case '-':
                return "☼";
            case '.':
                return "❢";
            default:
                return String.valueOf(numero);
        }
    }

    private String myPintarNumber(char numero) {
        switch (numero) {
            case '0':
                return ".";
            case '1':
                return "-";
            case '2':
                return "9";
            case '3':
                return "8";
            case '4':
                return "7";
            case '5':
                return "6";
            case '6':
                return "5";
            case '7':
                return "4";
            case '8':
                return "3";
            case '9':
                return "2";
            case '-':
                return "1";
            case '.':
                return "0";
            default:
                return String.valueOf(numero);
        }
    }

    //Convierte el caracter especificado por uno mismo al numero correspondiente
    private String myPintarRevert(char numero) {
        switch (numero) {
            case '☺':
                return "0";
            case '☻':
                return "1";
            case '♠':
                return "2";
            case '♣':
                return "3";
            case '♥':
                return "4";
            case '♦':
                return "5";
            case '♪':
                return "6";
            case '♫':
                return "7";
            case '☎':
                return "8";
            case '✋':
                return "9";
            case '❣':
                return ",";
            case '❢':
                return ".";
            case '☼':
                return "-";
            default:
                return String.valueOf(numero);
        }
    }

    private String myPintarNumberRevert(char numero) {
        switch (numero) {
            case '.':
                return "0";
            case '-':
                return "1";
            case '9':
                return "2";
            case '8':
                return "3";
            case '7':
                return "4";
            case '6':
                return "5";
            case '5':
                return "6";
            case '4':
                return "7";
            case '3':
                return "8";
            case '2':
                return "9";
            case '1':
                return "-";
            case '0':
                return ".";
            default:
                return String.valueOf(numero);
        }
    }

    //Altera la matriz por defecto con la llave ingresa por el usuario
    private double[][] alterarMatriz(double[][] mat, int llave) {
        double[][] result = new double[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                result[i][j] = mat[i][j] + (llave);
            }
        }
        return result;
    }


    /******************************************************************************************************************************************
     Metodos para hallar la inversa de una matriz
     ******************************************************************************************************************************************/
    private boolean isMatrizInverzaValida(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                return !String.valueOf(mat[i][j]).contains("Infinity") || String.valueOf(mat[i][j]).contains("NaN") ? true : false;
            }
        }
        return true;
    }

    private double[][] matrizInversa(double[][] matriz) {
        double det = 1 / determinante(matriz);
        double[][] nmatriz = matrizAdjunta(matriz);
        multiplicarMatriz(det, nmatriz);
        return nmatriz;
    }

    private void multiplicarMatriz(double n, double[][] matriz) {
        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                matriz[i][j] *= n;
    }

    private double[][] matrizAdjunta(double[][] matriz) {
        return matrizTranspuesta(matrizCofactores(matriz));
    }

    private double[][] matrizCofactores(double[][] matriz) {
        double[][] nm = new double[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                double[][] det = new double[matriz.length - 1][matriz.length - 1];
                double detValor;
                for (int k = 0; k < matriz.length; k++) {
                    if (k != i) {
                        for (int l = 0; l < matriz.length; l++) {
                            if (l != j) {
                                int indice1 = k < i ? k : k - 1;
                                int indice2 = l < j ? l : l - 1;
                                det[indice1][indice2] = matriz[k][l];
                            }
                        }
                    }
                }
                detValor = determinante(det);
                nm[i][j] = detValor * (double) Math.pow(-1, i + j + 2);
            }
        }
        return nm;
    }

    private double[][] matrizTranspuesta(double[][] matriz) {
        double[][] nuevam = new double[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++)
                nuevam[i][j] = matriz[j][i];
        }
        return nuevam;
    }

    private double determinante(double[][] matriz) {
        double det;
        if (matriz.length == 2) {
            det = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            return det;
        }
        double suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            double[][] nm = new double[matriz.length - 1][matriz.length - 1];
            for (int j = 0; j < matriz.length; j++) {
                if (j != i) {
                    for (int k = 1; k < matriz.length; k++) {
                        int indice = -1;
                        if (j < i)
                            indice = j;
                        else if (j > i)
                            indice = j - 1;
                        nm[indice][k - 1] = matriz[j][k];
                    }
                }
            }
            if (i % 2 == 0)
                suma += matriz[i][0] * determinante(nm);
            else
                suma -= matriz[i][0] * determinante(nm);
        }
        return suma;
    }

    /******************************************************************************************************************************************
     Metodos para hallar la inversa de una matriz
     ******************************************************************************************************************************************/

    private void imprimirMatriz(double[][] mat) {
        for (int i = 0; i < mat[1].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[j][i] + " ");
            }
            System.out.println();
        }
    }

    private void imprimirMatrizSinAcsii(double[][] mat) {
        for (int i = 0; i < mat[1].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(((char) mat[j][i]) + " ");
            }
            System.out.println();
        }
    }
}
