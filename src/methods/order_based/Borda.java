package methods.order_based;

import methods.FusionMethod;
import model.DigImage;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Borda implements FusionMethod {


    @Override
    public ArrayList<String> fusion(List<List<DigImage>> rankings) {

        HashMap<String, Double> votes = new HashMap<>();

        for (List<DigImage> ranking : rankings) {

            int numVoted = 0;
            double vote;

             for (DigImage image : ranking) {

                if (votes.get(image.getId()) == null) {
                    votes.put(image.getId(), 1.0 / (numVoted + 1));
                } else {
                    vote = votes.get(image.getId());
                    votes.put(image.getId(), vote + (1.0 / (numVoted + 1)));
                }
                numVoted++;
            }
        }

        HashMap<String, Double> sorted = votes
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
