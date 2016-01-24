public class RwayTrie<T> {
    private static class Node<T> {
        private T value;
        private Node[] next = new Node[R];
    }

    private static final int R = 256;
    private Node<T> root;

    public void put(final String key, final T value) {
        root = put(key, value, root, 0);
    }

    private Node put(final String key, final T value, Node x, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.value = value;
            return x;
        }
        final char c = key.charAt(d);
        x.next[c] = put(key, value, x.next[c], d + 1);
        return x;
    }

    public T get(final String key) {
        Node node = get(root, key, 0);
        return node != null ? (T) node.value : null;
    }

    private Node get(Node x, final String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }

        return get(x.next[key.charAt(d)], key, d + 1);
    }
}
