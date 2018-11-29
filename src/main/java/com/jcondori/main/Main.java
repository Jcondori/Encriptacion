package com.jcondori.main;

public class Main {

    public static void main(String[] args) {

        //Encriptacion sin Desencriptacion
        System.out.println(encryptionJcondori.encriptarFinal("jcondori"));

        //Encriptacion por defecto
        System.out.println(encryptionJcondori.encriptar("jcondori"));
        System.out.println(encryptionJcondori.desencriptar("♠✋☺☺❢☺❣♠♠♪✋❢☺❣☻♣♫♪❢☺❣♠☺☻☻❢☺❣♠☎☻☎❢☺❣♠♠♠☺❢☺❣☻♣✋♠❢☺❣♠☺♪✋❢☺"));

        //Encriptacion con llave personalizada
        System.out.println(encryptionJcondori.encriptar(-5, "jcondori"));
        System.out.println(encryptionJcondori.desencriptar(-5, "♫♫☺❢☺❣☻♣✋❢☺❣☼♫♦♥❢☺❣☼☻☻✋❢☺❣♪♪☎❢☺❣♫☺❢☺❣☼♫♦☎❢☺❣☼☎☻❢☺"));

    }
}
