public class Product {

    private int id;
    private double weight;
    private int value;

    public Product(final int id, final double weight, final int value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
    }

    public int getId() {
        return this.id;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getValue() {
        return this.value;
    }

    public int getNormalizedWeight(int normalizer) {
        return (int) (this.weight * normalizer);
    }
}
