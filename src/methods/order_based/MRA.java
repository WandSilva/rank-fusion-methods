package methods.order_based;

import methods.FusionMethod;
import model.DigImage;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class MRA implements FusionMethod {
    @Override
    public ArrayList<String> fusion(List<List<DigImage>> rankings) {

        HashMap<String, Integer> mraHash = new HashMap<>();

        for (List<DigImage> ranking : rankings) {
            for (DigImage image : ranking) {
                mraHash.putIfAbsent(image.getId(), 0);
            }
        }

        int rankSize = rankings.get(0).size();
        int n = 0;
        int o = rankings.size()/2;

        ArrayList<String>result = new ArrayList<>();

        while (n < rankSize){
            for (Map.Entry<String, Integer> pair : mraHash.entrySet()) {
                int c = calculateC(pair.getKey(), rankings, n);
                int m = c + mraHash.get(pair.getKey());
                mraHash.put(pair.getKey(), m);
                if (pair.getValue() > o){
                    if(!result.contains(pair.getKey())) {
                        result.add(pair.getKey());
                    }
                }
            }
            n++;
        }
        return result;
    }

    private int calculateC(String imageId, List<List<DigImage>> rankings, int n) {
        int count = 0;
        for (List<DigImage> ranking : rankings) {
            if (ranking.get(n).getId().equals(imageId)) {
                count++;
            }
        }
        return count;
    }
}
