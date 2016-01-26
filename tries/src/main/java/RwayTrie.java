import java.util.Iterator;
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
        for(int c = 0; c < R; c++) {
            collect(n.next[c], queue, pre + Character.toString((char) c));
        }
    }
}
