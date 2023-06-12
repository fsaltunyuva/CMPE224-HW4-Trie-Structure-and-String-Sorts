//-----------------------------------------------------
// Title: Trie Implementation
// Authors: Robert Sedgewick
// Section: 4
// Assignment: 4
// Description: This class directly references the codes from the lecture slides inspired from Sedgewick's Algorithms book
//-----------------------------------------------------

public class Trie<Value> {

    static int R = 256; //Basing on extended ASCII as we discussed in class

    Node root; //root of the trie
    int n; //number of keys in trie

    public boolean search(String arg){ //Search method that takes the String value and returns if the entered value exist or not
        return get(arg) != null; //Checking if the get method returns null or not
    }

    //Put method inspired from the Sedgewick's Algorithm Book
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.value == null) n++;
            x.value = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }
    //#Put method inspired from the Sedgewick's Algorithm Book

    //Get method inspired from the Sedgewick's Algorithm Book
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }
    //#Get method inspired from the Sedgewick's Algorithm Book

    //To access all keys to reverse all words and add them into another trie (These methods are also copied from the Sedgewick's Algorithm book
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.value != null) results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    //#To access all keys to reverse all words and add them into another trie (These methods are also copied from the Sedgewick's Algorithm book

    static class Node { //We made the Node class an inner class because VPL has limitations for the count of files when uploading
        Object value;
        Node[] next = new Node[256]; //R value from the Trie (Presumption Extended ASCII)
    }
}
