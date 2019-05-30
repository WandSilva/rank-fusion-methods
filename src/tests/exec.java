package tests;

import methods.order_based.Borda;
import methods.order_based.MRA;
import methods.order_based.RRF;
import methods.score_based.*;
import model.DigImage;

import java.util.ArrayList;
import java.util.List;

public class exec {
    public static void main(String[] args){

        List<DigImage> ranking1 =  List.of(
                new DigImage("A", 0.9),
                new DigImage("B", 0.7),
                new DigImage("C", 0.4));

        List<DigImage> ranking2 =  List.of(
                new DigImage("A", 1.0),
                new DigImage("B", 0.6),
                new DigImage("C", 0.5));

        List<DigImage> ranking3 =  List.of(
                new DigImage("D", 0.8),
                new DigImage("B", 0.7),
                new DigImage("C", 0.6));

        List<DigImage> ranking4 =  List.of(
                new DigImage("D", 0.8),
                new DigImage("A", 0.7),
                new DigImage("C", 0.6));

        List<DigImage> ranking5 =  List.of(
                new DigImage("A", 0.8),
                new DigImage("D", 0.7),
                new DigImage("C", 0.6));

        List<List<DigImage>> rankings = List.of(ranking1, ranking2, ranking3, ranking4, ranking5);
        ArrayList<String> result;

        System.out.println("Borda:");
        Borda borda = new Borda();
        result = borda.fusion(rankings);
        //System.out.println(result);


        System.out.println("Combmax:");
        CombMax combMax = new CombMax();
        result = combMax.fusion(rankings);
        //System.out.println(result);

        System.out.println("Combmin:");
        CombMin combMin = new CombMin();
        result = combMin.fusion(rankings);
        //System.out.println(result);

        System.out.println("Combsum:");
        CombSum combSum = new CombSum();
        result = combSum.fusion(rankings);
        //System.out.println(result);

        System.out.println("Combmed:");
        CombMed combMed =  new CombMed();
        result = combMed.fusion(rankings);
        //System.out.println(result);

        System.out.println("CombMNZ:");
        CombMNZ combMNZ = new CombMNZ();
        result = combMNZ.fusion(rankings);
        //System.out.println(result);

        System.out.println("CombANZ:");
        CombANZ combANZ = new CombANZ();
        result = combANZ.fusion(rankings);
        //System.out.println(result);

        System.out.println("RRF:");
        RRF rrf = new RRF();
        result =  rrf.fusion(rankings);
        //System.out.println(result);


        System.out.println("MRA:");
        MRA mra = new MRA();
        result =  mra.fusion(rankings);
        System.out.println(result);

    }
}
