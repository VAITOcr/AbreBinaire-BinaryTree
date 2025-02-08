package arbres.binaires;

public interface ArbreBinaireDeRecherche<T extends Comparable<T>> extends ArbreBinaire<T> {

    Node<T> searchNode(T key);

    void insertNode(T key);

    void deleteNode(T key);
}
