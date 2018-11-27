package com.jcondori.main;

public class Main {

    public static void main(String[] args) {
        encryptionJcondori jcondori = new encryptionJcondori();

//        System.out.println(jcondori.encriptar("jcondori"));
//        System.out.println(jcondori.encriptar("jcondori", 100));
//        System.out.println(jcondori.desencriptar("♠♨☺☺❢☺❣♠♠♪♨❢☺❣☻♣♫♪❢☺❣♠☺☻☻❢☺❣♠☎☻☎❢☺❣♠♠♠☺❢☺❣☻♣♨♠❢☺❣♠☺♪♨❢☺"));
        System.out.println(jcondori.desencriptar("♥♦♦☺☺❢☺❣♥♥☎♪♨❢☺❣♥♣♨♫♪❢☺❣♥♥♪☻☻❢☺❣♥♦☎☻☎❢☺❣♥♦♠♠☺❢☺❣♥♥♣♨♠❢☺❣♥♦☺♪♨❢☺", 100));

    }
}
