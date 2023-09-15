/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Permite saber si la expresión en la lista está balanceada
     * o no. Cada elemento de la lista es un elemento. DEBE OBlIGATORIAMENTE
     * USARSE EL ALGORITMO QUE ESTÁ EN EL ENUNCIADO.
     */
    static boolean estaBalanceada(List<String> expresion) {
        Stack<String> delimitadores = new Stack<>();
        for (String elemento: expresion) {
            if ( elemento.equals("(") || elemento.equals("[") || elemento.equals("{")){
                delimitadores.push(elemento);
            } else if (elemento.equals(")") || elemento.equals("]") || elemento.equals("}")) {
                if(delimitadores.isEmpty()){
                    return false;
                }
                String delimitadorApertura = delimitadores.pop();
                if(esParejaCierre(delimitadorApertura,elemento) == false){
                    return false;

                }
            }
        }



        return delimitadores.isEmpty();
    }
    public static boolean esParejaCierre(String delimitadorApertura, String delimitadorCierre){
        if(delimitadorApertura.equals("(") && delimitadorCierre.equals(")")){
            return true;
        }
        if(delimitadorApertura.equals("[") && delimitadorCierre.equals("]")){
            return true;
        }
        if(delimitadorApertura.equals("{") && delimitadorCierre.equals("}")){
            return true;
        }
        return false;
    }

    /**
     * Transforma la expresión, cambiando los símbolos de agrupación
     * de corchetes ([]) y llaves ({}) por paréntesis ()
     */
    static void reemplazarDelimitadores(List<String> expresion) {
        for (int i = 0; i < expresion.size(); i++) {
            String caracter = expresion.get(i);
             if(caracter.equals("[")){
                 expresion.set(i,"(");
             }
            else if(caracter.equals("]")){
                expresion.set(i,")");
            }
            else if(caracter.equals("{")){
                expresion.set(i,"(");
            }
            else if(caracter.equals("}")){
                expresion.set(i,")");
            }

        }
    }

    /**
     * Realiza la conversión de la notación infija a postfija
     * @return la expresión convertida a postfija
     * OJO: Debe usarse el algoritmo que está en el enunciado OBLIGATORIAMENTE
     */
    static List<String> convertirAPostfijo(List<String> expresion) {
        Stack<String> pila = new Stack<>();
        List<String> salida = new ArrayList<>();
        for (String caracter:expresion ) {
            if(isOperadorAritmetico(caracter)){
                pila.push(caracter);
            }
            else if(caracter.equals("(") ){
                continue;
            } else if (caracter.equals(")")  ) {
                String elemento_tope = pila.pop();
                salida.add(elemento_tope);
            }
            else{
                salida.add(caracter);
            }


        }


        return salida;
    }
    public static boolean isOperadorAritmetico(String caracter){
        if(caracter.equals("+")|| caracter.equals("-") || caracter.equals("*") || caracter.equals("/") || caracter.equals("%")){
            return true;
        }
        return false;
    }
    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();
        for (String caracter:expresion) {
            try{
                int numero = Integer.parseInt(caracter);
                pila.push(numero);
            }

            catch (Exception e ){
                int valor1 = pila.pop();
                int valor2 = pila.pop();
                String operador = caracter;
                int resultado_operacion = 0;
                if(operador.equals("+")){
                    resultado_operacion= valor2 + valor1;
                }
                else if(operador.equals("-")){
                    resultado_operacion= valor2 - valor1;
                }
                else if(operador.equals("*")){
                    resultado_operacion= valor1 * valor2;
                }
                else if(operador.equals("%")){
                    resultado_operacion= valor2 % valor1;
                }
                else if(operador.equals("/")){
                    resultado_operacion= valor2 / valor1;
                }
                pila.push(resultado_operacion);
            }
        }

        // TODO: Realiza la evaluación de la expresión en formato postfijo

        return pila.pop();
    }

}
