import java.util.ArrayList;
import java.util.List;

/**
 * Java code for Dynamic Programming based solution for 0-1 Knapsack problem.
 * Created by Ali Kheiri on 1400/10/17.
 */
public class PackChallengeSolver {

    static void pack(int packMaxWeight, String productsProperties) {

        packMaxWeight *= 100;
        List<Long> ids = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        String[] productsPropertiesParts = productsProperties.split("[)]");
        int i = 0;
        for (String productProperties : productsPropertiesParts) {
            productProperties = productProperties.replaceAll("[^\\d,.]", "");
            String[] property = productProperties.split("[,]");
            ids.add(Long.parseLong(property[i]));
            weights.add((int) (Double.parseDouble(property[i + 1]) * 100));
            values.add(Integer.parseInt(property[i + 2]));
        }
        int j, w;
        int n = productsPropertiesParts.length;
        int[][] K = new int[n + 1][packMaxWeight + 1];

        // Build table K[][] in bottom up manner
        for (j = 0; j <= n; j++) {
            for (w = 0; w <= packMaxWeight; w++) {
                if (j == 0 || w == 0)
                    K[j][w] = 0;
                else if (weights.get(j - 1) <= w)
                    K[j][w] = Math.max(values.get(j - 1) + K[j - 1][w - weights.get(j - 1)], K[j - 1][w]);
                else
                    K[j][w] = K[j - 1][w];
            }
        }

        // stores the result of pack
        int result = K[n][packMaxWeight];
        System.out.println(result);

        w = packMaxWeight;
        for (j = n; j > 0 && result > 0; j--) {

            // either the result comes from the top
            // (K[j-1][w]) or from (val[j-1] + K[j-1]
            // [w-wt[j-1]]) as in pack table. if
            // it comes from the latter one it means
            // the item is included.
            if (result == K[j - 1][w])
                continue;
            else {

                // This item is included.
                System.out.print(ids.get(j - 1) + " ");

                // Since this weight is included its
                // value is deducted
                result = result - values.get(j - 1);
                w = w - weights.get(j - 1);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        String productsProperties = "(1,85.31,$29) (2,14.55,$74) (3,3.98,$16) (4,26.24,$55) (5,63.69,$52) (6,76.25,$75) (7,60.02,$74) (8,93.18,$35) (9,89.95,$78)";
        int packMaxWeight = 75;
        pack(packMaxWeight, productsProperties);
    }
}