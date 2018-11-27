package com.jcondori.main;

public class Main {

    public static void main(String[] args) {
        encryptionJcondori jcondori = new encryptionJcondori();

        System.out.println(jcondori.encriptar("Demo"));
        System.out.println(jcondori.desencriptar("♠♥☻☺❢☺❣☻♨♠♠❢☺❣☻♠♫☺❢☺❣☻☎♠☺❢☺"));

//        String name = "admin"; // String to check it's value
//        int nameLenght = name.length(); // length of the string used for the loop

//        for (int i = 0; i < nameLenght; i++) {   // while counting characters if less than the length add one
//            char character = name.charAt(i); // start on the first character
//            int ascii = (int) character; //convert the first character
//            System.out.println(character + " = " + ascii); // print the character and it's value in ascii
//        }

//        System.out.println( (char)83);

    }
}
