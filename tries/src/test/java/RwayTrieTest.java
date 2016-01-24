import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RwayTrieTest {

    @Test
    public void testRwayTrieNoDuplicateKey() {
        final RwayTrie<Integer> rwayTrie = new RwayTrie<Integer>();
        rwayTrie.put("she", 0);
        rwayTrie.put("sells", 1);
        rwayTrie.put("sea", 2);
        rwayTrie.put("shells", 3);
        rwayTrie.put("by", 4);
        rwayTrie.put("the", 5);
        rwayTrie.put("shore", 6);

        assertEquals(0,  rwayTrie.get("she").intValue());
        assertEquals(1,  rwayTrie.get("sells").intValue());
        assertEquals(2,  rwayTrie.get("sea").intValue());
        assertEquals(3,  rwayTrie.get("shells").intValue());
        assertEquals(4,  rwayTrie.get("by").intValue());
        assertEquals(5,  rwayTrie.get("the").intValue());
        assertEquals(6,  rwayTrie.get("shore").intValue());
    }

    @Test
    public void testRwayTrieDuplicateKey() {
        final RwayTrie<Integer> rwayTrie = new RwayTrie<Integer>();
        rwayTrie.put("she", 0);
        rwayTrie.put("sells", 1);
        rwayTrie.put("she", 2);

        assertEquals(2, rwayTrie.get("she").intValue());
        assertEquals(1, rwayTrie.get("sells").intValue());
    }
}