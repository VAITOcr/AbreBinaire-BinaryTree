package arbres.binaires;

public class ArbreBinaireSimple<T extends Comparable<T>> implements ArbreBinaire<T> {
    protected Node root;

    @Override
    public Node getRoot() {
        return root;
    }
}
