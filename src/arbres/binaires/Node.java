package arbres.binaires;

public class Node<T extends Comparable<T>> {
    T key;
    Node<T> left;
    Node<T> right;

    Integer height;

    public Node(T key) {
        this.key = key;
        this.height = 1;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
//        var key = this.getKey() != null ? this.getKey() : "null";
        var gauche = this.getLeft() != null ? this.getLeft().getKey() : "null";
        var droite = this.getRight() != null ? this.getRight().getKey() : "null";
        return "\nLe Noeud " + this.getKey() + " a { gauche : " + gauche + ", droite: " + droite + "}";
    }
}