package methods.score_based;

import methods.FusionMethod;
import model.DigImage;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class CombSum implements FusionMethod {
    @Override
    public ArrayList<String> fusion(List<List<DigImage>> rankings) {

        HashMap<String, ArrayList<Double>> scores = new HashMap<>();

        for (List<DigImage> ranking : rankings) {

            for (DigImage image : ranking) {

                if (scores.get(image.getId()) == null) {
                    scores.put(image.getId(), new ArrayList<>());
                    scores.get(image.getId()).add(image.getScore());
                } else {
                    scores.get(image.getId()).add(image.getScore());
                }
            }
        }

        HashMap<String, Double> aux = new HashMap<>();
        for (Map.Entry<String,ArrayList<Double>> pair : scores.entrySet()) {
            String key = pair.getKey();
            double sum = pair.getValue()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
            aux.put(key, sum);
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
}
