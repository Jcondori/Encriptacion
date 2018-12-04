package com.jcondori.main;

public class Main {

    public static void main(String[] args) {

        //Encriptacion sin Desencriptacion
        System.out.println(CifradoJcondori.encriptarFinal("jcondori"));

        //Encriptacion por defecto
        System.out.println(CifradoJcondori.encriptar("jcondori"));
        System.out.println(CifradoJcondori.desencriptar("♠✋☺☺❢☺❣♠♠♪✋❢☺❣☻♣♫♪❢☺❣♠☺☻☻❢☺❣♠☎☻☎❢☺❣♠♠♠☺❢☺❣☻♣✋♠❢☺❣♠☺♪✋❢☺"));

        //Encriptacion con llave personalizada
        System.out.println(CifradoJcondori.encriptar(-5, "jcondori"));
        System.out.println(CifradoJcondori.desencriptar(-5, "♫♫☺❢☺❣☻♣✋❢☺❣☼♫♦♥❢☺❣☼☻☻✋❢☺❣♪♪☎❢☺❣♫☺❢☺❣☼♫♦☎❢☺❣☼☎☻❢☺"));

    }
}
