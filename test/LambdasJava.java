package com.pryjda.app.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdasJava {

    public static void main(String[] args) {

        UnaryOperator<Integer> zmiennaLambda = x -> x * x;
        System.out.println(zmiennaLambda.apply(3));

        Function<Integer, String> zmiennaLabda2 = x -> {
            Integer y = x * 100;
            return y.toString();
        };

        System.out.println(zmiennaLabda2.apply(100));

//lambdaMethod(item -> )

        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<String> listaStrings = lista.stream().map(item -> item.toString() + "coś").collect(Collectors.toList());
        System.out.println(listaStrings);

        Stream<String> streamOfStrings = lista.stream().map(item -> item.toString() + "coś");

        List<String> listaStringsNo2 = streamOfStrings.collect(Collectors.toList());
//        List<String> listaStringsNo3 = streamOfStrings.collect(Collectors.toList());
        System.out.println(listaStringsNo2);
//        System.out.println(listaStringsNo3);
        System.out.println(lista);
    }

    //    public <T> boolean lambdaMethod(Predicate<T> predicate){
    public static boolean lambdaMethod(Predicate<Integer> predicate) {

        return predicate.test(444);
    }


}
