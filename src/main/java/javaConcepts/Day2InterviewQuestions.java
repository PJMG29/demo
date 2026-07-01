package javaConcepts;

/**
 *
 * # Why does ArrayList.add() have amortized O(1) time, but worst case O(n)?
 *
 * Key point to hit: Distinguish amortized from worst case. Most add() calls are O(1) because there's spare capacity. Occasionally, when capacity is exceeded, a resize (Arrays.copyOf) happens,
 * costing O(n) to copy all elements. But because capacity grows geometrically (1.5x), these expensive resizes become exponentially rarer, so the average cost per add over many operations is still O(1). Mention "geometric growth" explicitly — a naive fixed-increment growth strategy would make add() truly O(n) amortized, which interviewers use as a trick follow-up.
 *
 *# Why did HashMap introduce red-black trees in Java 8?
 *
 * Key point to hit: Before Java 8, a bucket with many hash collisions was a linked list, degrading get/put to O(n) in the worst case (e.g., adversarial input or poor hashCode() causing many collisions).
 * Treeifying long buckets (≥8 nodes) into red-black trees bounds worst-case lookup at O(log n). Also mention the reason it's conditional on table size ≥ 64 — for small tables, resizing is a cheaper fix than the overhead of maintaining a tree.
 *
 * $$$important$$$:
 * # Walk through what happens internally when you call hashMap.put(key, value).
 *
 * Key point to hit: (1) compute hash(key) using the supplemental hash spreading (h ^ (h >>> 16)); (2) compute bucket index via (n-1) & hash; (3) check bucket — empty → insert; else traverse the chain/tree comparing hash+equals;
 *
 * (4) replace if key found, else append (tail, Java 8+) or insert into tree; (5) treeify if chain reaches 8 nodes and table ≥ 64; (6) increment size, resize if size > threshold. The strongest answers explain why the extra hash-spreading step exists — to reduce collisions when hashCode() implementations only vary in high bits.
 *
 *
 * # What's the actual difference between HashSet and HashMap internally?
 *
 * Key point to hit: HashSet is literally a thin wrapper around a HashMap<E, Object>, where set elements become map keys and all values point to a shared dummy constant object (PRESENT). add() is just map.put(e, PRESENT) == null. This shows you understand composition-over-reimplementation in the JDK source.
 *
 *
 * # Why is ArrayDeque generally preferred over Stack and LinkedList for stack/queue use cases?
 *
 * Key point to hit: Stack extends the legacy Vector, which is synchronized — adding unnecessary locking overhead in single-threaded use. LinkedList has per-node memory overhead (object header + 2 references per element) and worse cache locality (nodes scattered in heap memory).
 * ArrayDeque uses a contiguous circular array — better cache performance, no synchronization overhead, and O(1) amortized operations at both ends. The official Java docs themselves recommend ArrayDeque over Stack/LinkedList for this reason.
 *
 *  $$important$$
 *  you need to know popular functions' time complexity in each collections
 *  And what collections allows you to have null value
 *
 *
 *  Why can't TreeMap/TreeSet contain null keys/elements, while HashMap/HashSet can?
 *
 * Key point to hit: TreeMap must compare keys to place them in sorted order (compareTo() or a Comparator),
 * and comparing against null throws NullPointerException. HashMap only needs hashCode()/equals(), and it special-cases null by always placing it in bucket 0. Bonus point: a custom Comparator could theoretically handle nulls, but the natural-ordering default cannot.
 */
 
public class Day2InterviewQuestions {

}
