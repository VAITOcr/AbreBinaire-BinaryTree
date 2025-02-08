package arbres.binaires;

import java.text.Normalizer;
import java.util.Date;

import model.Livre;

public class ArbreAVL<T extends Comparable<T>> extends ArbreBinaireDeRechercheRecursive<T> {

    private String normalize(String input) {
        if (input == null)
            return "";
        return input
                .trim() // Supprime les espaces avant/après
                .toLowerCase() // Met tout en minuscule
                .replaceAll("[^a-z0-9]", " ") // Remplace tout caractère non alphanumérique par un espace
                .replaceAll("\\s+", " ") // Remplace plusieurs espaces par un seul
                .trim(); // Re-trim pour être sûr
    }

    public void searchByAuthorAndTitlePrefix(Node<T> node, String author, String title) {
        if (node != null) {
            searchByAuthorAndTitlePrefix(node.getLeft(), author, title);

            Livre livre = (Livre) node.getKey();
            String normalizedAuthor = normalize(livre.getAuteur());
            String normalizedTitle = normalize(livre.getTitre());
            String normalizedPrefix = normalize(title);

            if (normalizedAuthor.equals(normalize(author)) && normalizedTitle.contains(normalizedPrefix)) {
                System.out.println(livre);
            }

            searchByAuthorAndTitlePrefix(node.getRight(), author, title);
        }
    }

    public void searchByAuthorAndTitlePrefix(String author, String titlePrefix) {
        searchByAuthorAndTitlePrefix(root, author, titlePrefix);
    }

    // methode for search by author and title from root
    public void searchByAuthorAndTitle(String author, String title) {
        searchByAuthorAndTitlePrefix(root, author, title);
    }

    public void searchByAuthorAndDate(Node<T> node, String author, Date startDate, Date endDate) {
        if (node != null) {
            searchByAuthorAndDate(node.getLeft(), author, startDate, endDate);

            Livre livre = (Livre) node.getKey();
            if (normalize(livre.getAuteur()).equalsIgnoreCase(author)) {
                Date livreDate = livre.getDatePublication();
                if (livreDate.after(startDate) && livreDate.before(endDate)) {
                    System.out.println(livre);
                }
            }

            searchByAuthorAndDate(node.getRight(), author, startDate, endDate);
        }
    }

    public void afficherParCategorieAuteurTitre(Node<T> node) {
        if (node != null) {
            afficherParCategorieAuteurTitre(node.getLeft());

            Livre livre = (Livre) node.getKey();
            System.out.println(livre.getCategorie() + " - " + livre.getAuteur() + " - " + livre.getTitre());

            afficherParCategorieAuteurTitre(node.getRight());
        }
    }

    public void afficherRuptureStock(Node<T> node) {
        if (node != null) {
            afficherRuptureStock(node.getLeft());

            Livre livre = (Livre) node.getKey();
            if (livre.getQuantiteEnStock() == 0) {
                System.out.println(livre);
            }

            afficherRuptureStock(node.getRight());
        }
    }

    public void genererRapport(Node<T> node) {
        if (node != null) {
            genererRapport(node.getLeft());

            Livre livre = (Livre) node.getKey();
            System.out.println("Titre: " + livre.getTitre());
            System.out.println("Auteur: " + livre.getAuteur());
            System.out.println("Categorie: " + livre.getCategorie());
            System.out.println("Prix: " + livre.getPrix());
            System.out.println("Date de publication: " + livre.getDatePublication());
            System.out.println("Quantite en stock: " + livre.getQuantiteEnStock());
            System.out.println("ISBN: " + livre.getISBN());
            System.out.println("-----------------------------------------------------");

            genererRapport(node.getRight());
        }
    }

    public void genererRapport() {
        genererRapport(root);
    }

    // methode for stock rupture from root
    public void afficherRuptureStock() {
        afficherRuptureStock(root);
    }

    // methode for recursive search by category, author and title from root
    public void afficherParCategorieAuteurTitre() {
        afficherParCategorieAuteurTitre(root);
    }

    // methode for search by author and date from root
    public void searchByAuthorAndDate(String author, Date startDate, Date endDate) {
        searchByAuthorAndDate(root, author, startDate, endDate);
    }

    public void searchByCategoryAndPrice(Node<T> node, String category, double minPrice, double maxPrice) {
        if (node != null) {
            searchByCategoryAndPrice(node.getLeft(), category, minPrice, maxPrice);

            Livre livre = (Livre) node.getKey();
            if (normalize(livre.getCategorie()).equalsIgnoreCase(category) && livre.getPrix() >= minPrice
                    && livre.getPrix() <= maxPrice) {
                System.out.println(livre);
            }

            searchByCategoryAndPrice(node.getRight(), category, minPrice, maxPrice);
            
        }
    }

    // methode for search by category and price from root
    public void searchByCategoryAndPrice(String category, double minPrice, double maxPrice) {
        searchByCategoryAndPrice(root, category, minPrice, maxPrice);
    }

    private int height(Node<T> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private void updateHeight(Node<T> node) {
        if (node != null) {
            node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight()))); // update height
        }
    }

    private int getBalance(Node<T> node) {
        return (node == null) ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> left = node.getLeft(); // save left child
        Node<T> right = left.getRight(); // save right child of left child

        left.setRight(node); // set right child of left child to node
        node.setLeft(right); // set left child of node to right child of left child

        updateHeight(right); // update heights
        updateHeight(left);

        return left; // return new root
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> right = node.getRight(); // save right child
        Node<T> left = right.getLeft(); // save left child of right child

        right.setLeft(node); // set left child of right child to node
        node.setRight(left); // set right child of node to left child of right child

        updateHeight(left); // update heights
        updateHeight(right);

        return right; // return new root
    }

    public Node<T> insert(Node<T> node, T key) {
        if (node == null) {
            return new Node<>(key);
        }

        if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(insert(node.getLeft(), key)); // insert in left subtree
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(insert(node.getRight(), key)); // insert in right subtree
        } else {
            return node; // key already exists, do nothing
        }

        updateHeight(node); // update height

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.getLeft().getKey()) < 0) {
            return rotateRight(node); // left-left case
        }

        if (balance < -1 && key.compareTo(node.getRight().getKey()) > 0) {
            return rotateLeft(node); // right-right case
        }

        if (balance > 1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(rotateLeft(node.getLeft())); // left-right case
            return rotateRight(node);
        }

        if (balance < -1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rotateRight(node.getRight())); // right-left case
            return rotateLeft(node);
        }

        return node;
    }

    public void insert(T key) {
        this.root = insert(this.root, key); // insert in root
    }

    public Node<T> search(T key) {
        return searchNode(key); // search in root
    }

    public Node<T> delete(Node<T> node, T key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(delete(node.getLeft(), key)); // delete in left subtree
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(delete(node.getRight(), key)); // delete in right subtree
        } else {
            if (node.getLeft() == null) {
                return node.getRight(); // left child is null
            } else if (node.getRight() == null) {
                return node.getLeft(); // right child is null
            }

            node.setKey(minValue(node.getRight())); // get successor
            node.setRight(delete(node.getRight(), node.getKey())); // delete successor
        }

        updateHeight(node); // update height

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rotateRight(node); // left-left case
        }

        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return rotateLeft(node); // right-right case
        }

        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft())); // left-right case
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight())); // right-left case
            return rotateLeft(node);
        }

        return node;
    }

    private T minValue(Node<T> node) {
        T minValue = node.getKey();

        while (node.getLeft() != null) {
            minValue = node.getLeft().getKey();
            node = node.getLeft();
        }

        return minValue;
    }

    public void delete(T key) {
        this.root = delete(this.root, key); // delete in root
    }

    public void afficherInorder(Node<T> node) {
        if (root != null) {
            root.afficherInorder(node.getLeft());
            System.out.println(node.getKey());
            root.afficherInorder(node.getRight());
        }
    }

}
