package com.imooc.demo.firestTest;

import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        players.forEach((play)-> System.out.println(play));
    }
}
