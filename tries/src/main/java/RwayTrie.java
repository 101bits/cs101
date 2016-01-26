import java.util.LinkedList;
import java.util.Queue;

public class RwayTrie<T> {
    private static final int R = 256;
    private Node root;

    private static class Node<T> {
        private T value;
        private Node[] next = new Node[R];
    }

    public void put(String key, T value) {
        root = put(key, value, root, 0);
    }

    private Node put(String key, T value, Node n, int d) {
        if (n == null) {
            n = new Node();
        }
        if (d == key.length()) {
            n.value = value;
            return n;
        }
        char c = key.charAt(d);
        n.next[c] = put(key, value, n.next[c], d + 1);
        return n;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public T get(String key) {
        Node node = get(key, root, 0);
        return node != null ? (T) node.value : null;

    }

    private Node get(String key, Node n, int d) {
        if (n == null || d == key.length()) {
            return n;
        }
        return get(key, n.next[key.charAt(d)], d + 1);
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        Node preNode = get(pre, root, 0);
        collect(preNode, queue, pre);

        return queue;
    }

    public void collect(Node n, Queue<String> queue, String pre) {
        if (n == null) {
            return;
        }
        if (n.value != null) {
            queue.add(pre);
        }
        for (int c = 0; c < R; c++) {
            collect(n.next[c], queue, pre + Character.toString((char) c));
        }
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", pattern, queue);
        return queue;
    }

    private void collect(Node n, String pre, String pattern, Queue<String> queue) {
        if(n == null) {
            return;
        }
        int d = pre.length();
        if(n.value != null && d == pattern.length()) {
            queue.add(pre);
        }
        if (d == pattern.length()) {
            return;
        }
        char next = pattern.charAt(d);
        for (int i = 0; i < R; i++) {
            if (next == i || next == '.') {
                collect(n.next[i], pre + Character.toString((char) i), pattern, queue);
            }
        }
    }

    public String longestPrefix(String s) {
        int longest = search(root, s, 0, 0);
        return s.substring(0, longest);
    }

    private int search(Node n, String s, int d, int length) {
        if (n == null || d == s.length()) {
            return length;
        }

        if (n.value != null) {
            length = d;
        }

        char c = s.charAt(d);
        return search(n.next[c], s, d + 1, length);
    }
}
