package methods;

import model.DigImage;

import java.util.ArrayList;
import java.util.List;

public interface FusionMethod {

    ArrayList<String> fusion(List<List<DigImage>> rankings);
}
