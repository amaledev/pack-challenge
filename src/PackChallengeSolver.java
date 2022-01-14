import java.util.ArrayList;
import java.util.List;

/**
 * Java code for Dynamic Programming based solution for 0-1 Knapsack problem.
 * Created by Ali Kheiri on 1400/10/17.
 * Modified by Ali Kheiri on 1400/10/24.
 */
public class PackChallengeSolver {

    private static List<Product> normalizeProductInputData(String inputData) {
        String[] rawProductList = inputData.split("[)]");
        List<Product> products = new ArrayList<>();
        for (String productProperties : rawProductList) {
            productProperties = productProperties.replaceAll("[^\\d,.]", "");
            String[] property = productProperties.split("[,]");
            int id = Integer.parseInt(property[0]);
            double weight = Double.parseDouble(property[1]);
            int value = Integer.parseInt(property[2]);
            products.add(new Product(id, weight, value));
        }
        return products;
    }

    public static void pack(int packMaxWeight, String productsProperties) {
        final int weightNormalizer = 100;
        packMaxWeight *= weightNormalizer;
        List<Product> products = normalizeProductInputData(productsProperties);
        int[][] cachedWeights = new int[products.size() + 1][packMaxWeight + 1];

        // Build table cachedWeights[][] in bottom up manner
        for (int i = 0; i <= products.size(); i++) {
            for (int w = 0; w <= packMaxWeight; w++) {
                if (i == 0 || w == 0)
                    cachedWeights[i][w] = 0;
                else {
                    var product = products.get(i - 1);
                    if (product.getNormalizedWeight(weightNormalizer) <= w) {
                        cachedWeights[i][w] = Math.max(product.getValue() + cachedWeights[i - 1][w - product.getNormalizedWeight(weightNormalizer)], cachedWeights[i - 1][w]);
                    } else
                        cachedWeights[i][w] = cachedWeights[i - 1][w];
                }
            }
        }
        getResult(packMaxWeight, products, cachedWeights);
    }

    private static void getResult(int packMaxWeight, List<Product> products, int[][] cachedWeights) {
        // stores the result of pack
        int result = cachedWeights[products.size()][packMaxWeight];
        System.out.println(result);

        int w = packMaxWeight;
        for (int i = products.size(); i > 0 && result > 0; i--) {
            if (result == cachedWeights[i - 1][w])
                continue;
            else {
                var product = products.get(i - 1);
                System.out.print(product.getId() + " ");
                result = result - product.getValue();
                w = w - product.getValue();
            }
        }
    }
}