package methods.score_based;

import methods.FusionMethod;
import model.DigImage;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class CombANZ implements FusionMethod {
    @Override
    public ArrayList<String> fusion(List<List<DigImage>> rankings) {

        HashMap<String, ArrayList<Double>> scores = new HashMap<>();

        for (List<DigImage> ranking : rankings) {

            for (DigImage image : ranking) {

                if (scores.get(image.getId()) == null) {
                    scores.put(image.getId(), new ArrayList<Double>());
                    scores.get(image.getId()).add(image.getScore());
                } else {
                    scores.get(image.getId()).add(image.getScore());
                }
            }
        }

        int numRankings = rankings.size();
        HashMap<String, Double> aux = new HashMap<>();

        for (Map.Entry<String,ArrayList<Double>> pair : scores.entrySet()) {
            String key = pair.getKey();
            double sum = pair.getValue()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
            aux.put(key, sum/ isIn(key, rankings));
        }


        HashMap<String, Double> sorted = aux
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println(sorted);
        ArrayList<String> ranking = new ArrayList(sorted.keySet());
        return ranking;
    }

    private int isIn(String imageId, List<List<DigImage>> rankings){
        int count=0;
        for (List<DigImage> ranking: rankings){
            for (DigImage imagem: ranking){
                if(imagem.getId().equals(imageId)){
                    count++;
                }
            }
        }
        return count;
    }
}
