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
                mensaje = mensaje + this.asciiDesencriptar(String.valueOf(new BigDecimal(matriz[l][i]).setScale(0, RoundingMode.HALF_EVEN).intValue()));
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
                    matriz[c][f] = Integer.valueOf(this.asciiEncriptar(String.valueOf(mensaje.charAt(i))));
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

    private String asciiEncriptar(String letra) {
        switch (letra) {
            case " ":
                return "32";
            case "!":
                return "33";
            case "\"":
                return "34";
            case "#":
                return "35";
            case "$":
                return "36";
            case "%":
                return "37";
            case "&":
                return "38";
            case "'":
                return "39";
            case "(":
                return "40";
            case ")":
                return "41";
            case "*":
                return "42";
            case "+":
                return "43";
            case ",":
                return "44";
            case "-":
                return "45";
            case ".":
                return "46";
            case "/":
                return "47";
            case "0":
                return "48";
            case "1":
                return "49";
            case "2":
                return "50";
            case "3":
                return "51";
            case "4":
                return "52";
            case "5":
                return "53";
            case "6":
                return "54";
            case "7":
                return "55";
            case "8":
                return "56";
            case "9":
                return "57";
            case ":":
                return "58";
            case ";":
                return "59";
            case "<":
                return "60";
            case "=":
                return "61";
            case ">":
                return "62";
            case "?":
                return "63";
            case "@":
                return "64";
            case "A":
                return "65";
            case "B":
                return "66";
            case "C":
                return "67";
            case "D":
                return "68";
            case "E":
                return "69";
            case "F":
                return "70";
            case "G":
                return "71";
            case "H":
                return "72";
            case "I":
                return "73";
            case "J":
                return "74";
            case "K":
                return "75";
            case "L":
                return "76";
            case "M":
                return "77";
            case "N":
                return "78";
            case "O":
                return "79";
            case "P":
                return "80";
            case "Q":
                return "81";
            case "R":
                return "82";
            case "S":
                return "83";
            case "T":
                return "84";
            case "U":
                return "85";
            case "V":
                return "86";
            case "W":
                return "87";
            case "X":
                return "88";
            case "Y":
                return "89";
            case "Z":
                return "90";
            case "[":
                return "91";
            case "\\":
                return "92";
            case "]":
                return "93";
            case "^":
                return "94";
            case "_":
                return "95";
            case "`":
                return "96";
            case "a":
                return "97";
            case "b":
                return "98";
            case "c":
                return "99";
            case "d":
                return "100";
            case "e":
                return "101";
            case "f":
                return "102";
            case "g":
                return "103";
            case "h":
                return "104";
            case "i":
                return "105";
            case "j":
                return "106";
            case "k":
                return "107";
            case "l":
                return "108";
            case "m":
                return "109";
            case "n":
                return "110";
            case "o":
                return "111";
            case "p":
                return "112";
            case "q":
                return "113";
            case "r":
                return "114";
            case "s":
                return "115";
            case "t":
                return "116";
            case "u":
                return "117";
            case "v":
                return "118";
            case "w":
                return "119";
            case "x":
                return "120";
            case "y":
                return "121";
            case "z":
                return "122";
            case "{":
                return "123";
            case "|":
                return "124";
            case "}":
                return "125";
            case "~":
                return "126";
            default:
                return letra;
        }
    }

    private String asciiDesencriptar(String letra) {
        switch (letra) {
            case "32":
                return " ";
            case "33":
                return "!";
            case "34":
                return "\"";
            case "35":
                return "#";
            case "36":
                return "$";
            case "37":
                return "%";
            case "38":
                return "&";
            case "39":
                return "'";
            case "40":
                return "(";
            case "41":
                return ")";
            case "42":
                return "*";
            case "43":
                return "+";
            case "44":
                return ",";
            case "45":
                return "-";
            case "46":
                return ".";
            case "47":
                return "/";
            case "48":
                return "0";
            case "49":
                return "1";
            case "50":
                return "2";
            case "51":
                return "3";
            case "52":
                return "4";
            case "53":
                return "5";
            case "54":
                return "6";
            case "55":
                return "7";
            case "56":
                return "8";
            case "57":
                return "9";
            case "58":
                return ":";
            case "59":
                return ";";
            case "60":
                return "<";
            case "61":
                return "=";
            case "62":
                return ">";
            case "63":
                return "?";
            case "64":
                return "@";
            case "65":
                return "A";
            case "66":
                return "B";
            case "67":
                return "C";
            case "68":
                return "D";
            case "69":
                return "E";
            case "70":
                return "F";
            case "71":
                return "G";
            case "72":
                return "H";
            case "73":
                return "I";
            case "74":
                return "J";
            case "75":
                return "K";
            case "76":
                return "L";
            case "77":
                return "M";
            case "78":
                return "N";
            case "79":
                return "O";
            case "80":
                return "P";
            case "81":
                return "Q";
            case "82":
                return "R";
            case "83":
                return "S";
            case "84":
                return "T";
            case "85":
                return "U";
            case "86":
                return "V";
            case "87":
                return "W";
            case "88":
                return "X";
            case "89":
                return "Y";
            case "90":
                return "Z";
            case "91":
                return "[";
            case "92":
                return "\\";
            case "93":
                return "]";
            case "94":
                return "^";
            case "95":
                return "_";
            case "96":
                return "`";
            case "97":
                return "a";
            case "98":
                return "b";
            case "99":
                return "c";
            case "100":
                return "d";
            case "101":
                return "e";
            case "102":
                return "f";
            case "103":
                return "g";
            case "104":
                return "h";
            case "105":
                return "i";
            case "106":
                return "j";
            case "107":
                return "k";
            case "108":
                return "l";
            case "109":
                return "m";
            case "110":
                return "n";
            case "111":
                return "o";
            case "112":
                return "p";
            case "113":
                return "q";
            case "114":
                return "r";
            case "115":
                return "s";
            case "116":
                return "t";
            case "117":
                return "u";
            case "118":
                return "v";
            case "119":
                return "w";
            case "120":
                return "x";
            case "121":
                return "y";
            case "122":
                return "z";
            case "123":
                return "{";
            case "124":
                return "|";
            case "125":
                return "}";
            case "126":
                return "~";
            default:
                return letra;
        }
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
