public class Main {

    public static void main(String[] args) {
        String productsProperties = "(1,85.31,$29) (2,14.55,$74) (3,3.98,$16) (4,26.24,$55) (5,63.69,$52) (6,76.25,$75) (7,60.02,$74) (8,93.18,$35) (9,89.95,$78)";
        int packMaxWeight = 75;
        PackChallengeSolver.pack(packMaxWeight, productsProperties);
    }
}
