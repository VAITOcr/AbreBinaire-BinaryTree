package arbres.binaires;

public class ArbreBinaireDeRechercheRecursive<T extends Comparable<T>>
        extends ArbreBinaireSimple<T> implements ArbreBinaireDeRecherche<T> {

    @Override
    public void insertNode(T key) {
        root = insertNodeRecursive(root, key);
    }

    private Node<T> insertNodeRecursive(Node<T> node, T key) {
        if (node == null) {
            return new Node<>(key);
        }

        if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(insertNodeRecursive(node.getLeft(), key));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(insertNodeRecursive(node.getRight(), key));
        }

        return node;
    }

    @Override
    public Node<T> searchNode(T key) {
        return searchNodeRecursive(root, key);
    }

    private Node<T> searchNodeRecursive(Node<T> node, T key) {
        if (node == null || node.getKey().equals(key)) {
            return node;
        }

        if (key.compareTo(node.getKey()) < 0) {
            return searchNodeRecursive(node.getLeft(), key);
        } else {
            return searchNodeRecursive(node.getRight(), key);
        }
    }

    @Override
    public void deleteNode(T key) {
        root = deleteNodeRecursive(root, key);
    }

    private Node<T> deleteNodeRecursive(Node<T> node, T key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(deleteNodeRecursive(node.getLeft(), key));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(deleteNodeRecursive(node.getRight(), key));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                return null;
            }

            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            Node<T> minNode = findMin(node.getRight());
            node.setKey(minNode.getKey());
            node.setRight(deleteNodeRecursive(node.getRight(), minNode.getKey()));
        }

        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

}
