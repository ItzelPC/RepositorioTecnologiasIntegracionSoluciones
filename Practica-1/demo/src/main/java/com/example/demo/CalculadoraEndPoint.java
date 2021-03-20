package com.example.demo;

import org.example.calculadora.SumarRequest;
import org.example.calculadora.SumarResponse;


@EndPoint
public class CalculadoraEndPoint{
    @PayLoadRoot(namespace = "http://www.example.org/calculadora", localPart = "SumarRequest")
    @ResponsePayLoad
    public SumarResponse dameSumar (@RequestPayLoad SumarRequest peticion){
        SumarResponse respuesta = new SumarResponse();
        respuesta.setResultado(String.valueOf(
            Integer.parseInt( peticion.getA()) +
            Integer.parseInt( peticion.getB())
        ));
        return respuesta;
    }
}