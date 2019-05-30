package methods.order_based;

import methods.FusionMethod;
import model.DigImage;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Rrf implements FusionMethod {
    @Override
    public ArrayList<String> fusion(List<List<DigImage>> rankings) {

        HashMap<String, Double> rfmap = new HashMap<>();

        for (List<DigImage> ranking : rankings) {

            for (DigImage image : ranking) {
                rfmap.putIfAbsent(image.getId(), 0.0);
                int index = ranking.indexOf(image)+1;
                double score = (double)1 / (60 + index);
                double rfscore =  rfmap.get(image.getId()) + score;
                rfmap.put(image.getId(), rfscore);
            }
        }
        HashMap<String, Double> sorted = rfmap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println(sorted);
        ArrayList ranking = new ArrayList(sorted.keySet());
        return ranking;
    }
}
