import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class RwayTrieTest {
    private RwayTrie<Integer> rwayTrie;

    @Before
    public void setUp() throws Exception {
        rwayTrie = new RwayTrie<Integer>();
    }

    @Test
    public void testRwayTrieNoDuplicateKey() {
        rwayTrie.put("she", 0);
        rwayTrie.put("sells", 1);
        rwayTrie.put("sea", 2);
        rwayTrie.put("shells", 3);
        rwayTrie.put("by", 4);
        rwayTrie.put("the", 5);
        rwayTrie.put("shore", 6);

        assertEquals(0, rwayTrie.get("she").intValue());
        assertEquals(1, rwayTrie.get("sells").intValue());
        assertEquals(2, rwayTrie.get("sea").intValue());
        assertEquals(3, rwayTrie.get("shells").intValue());
        assertEquals(4, rwayTrie.get("by").intValue());
        assertEquals(5, rwayTrie.get("the").intValue());
        assertEquals(6, rwayTrie.get("shore").intValue());
    }

    @Test
    public void testRwayTrieDuplicateKey() {
        rwayTrie.put("she", 0);
        rwayTrie.put("sells", 1);
        rwayTrie.put("she", 2);

        assertEquals(2, rwayTrie.get("she").intValue());
        assertEquals(1, rwayTrie.get("sells").intValue());
    }

    @Test
    public void testAllKeys() throws Exception {
        final Set<String> strings = new HashSet<String>(Arrays.asList("she", "sells", "shells", "by", "sea", "shore"));
        for (final String key : strings) {
            rwayTrie.put(key, 1);
        }

        final Set<String> result = new HashSet<String>();
        Iterable<String> keys = rwayTrie.keys();
        for (String key : keys) {
            result.add(key);
        }

        assertEquals(strings.size(), result.size());
        assertTrue(strings.containsAll(result));
        assertTrue(result.containsAll(strings));
    }

    @Test
    public void testKeysWithPrefix() throws Exception {
        final Set<String> strings = new HashSet<String>(Arrays.asList("she", "sells", "shells", "by", "sea", "shore"));
        for (final String key : strings) {
            rwayTrie.put(key, 1);
        }

        final Set<String> result = new HashSet<String>();
        Iterable<String> keysWithSh = rwayTrie.keysWithPrefix("sh");
        for (String key : keysWithSh) {
            result.add(key);
        }

        assertNotEquals(strings.size(), result.size());
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList("she", "shells", "shore")));
    }
}