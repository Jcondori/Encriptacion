package com.jcondori.main;

public class Main {

    public static void main(String[] args) {
        //Libreria
        encryptionJcondori jcondori = new encryptionJcondori();

        //Encriptacion por defecto
        System.out.println(jcondori.encriptar("jcondori"));
        System.out.println(jcondori.desencriptar("♠✋☺☺❢☺❣♠♠♪✋❢☺❣☻♣♫♪❢☺❣♠☺☻☻❢☺❣♠☎☻☎❢☺❣♠♠♠☺❢☺❣☻♣✋♠❢☺❣♠☺♪✋❢☺"));

        //Encriptacion con llave personalizada
        System.out.println(jcondori.encriptar(5, "jcondori"));
        System.out.println(jcondori.desencriptar(5, "♦☺♣☺❢☺❣♥♣✋✋❢☺❣♣♦☺♪❢☺❣♥☻♥☻❢☺❣♥✋♪☎❢☺❣♥♣♫☺❢☺❣♣♦♥♠❢☺❣♥♠☻✋❢☺"));

    }
}
