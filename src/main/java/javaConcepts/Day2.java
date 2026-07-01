package javaConcepts;

/*
    Arraylist
    linkedlist
    hashmap
    hashset
    treeset
    treemap
    linkedhashmap
    linked hashset
    priority queue
    arraydeque
    ...


    1: arraylist
    it is a resizable array
    you can have insertion, delete,
    allows duplicate elements
    allows "Null" value
    implements, list, RandomAccess interfaces.
    RandomAccess interface is a marker interface telling algortithms
    like you can access elements by index, which means time complexity
    is O(1)
    it not synchronized (no thread safe).

    get(index) - O(1)
    add(element) - O(1) - best, O(N) - worst case
    add(index, element) - O(N)
    remove(index) - O(N)
    size() - O(1)

    how it resizes:
        default initial capacity is 10,
        when the capacity is exceeded, it grows
        newCapacity = oldCapacity + (oldCapacity >> 1); = 1.5 * old capacity

        Growth processing:
            call Arrays.copyOf() -> copy all elements into new large place
            -> time complexity is O(N)

      how put/insertion function works?
        1: we need to ensure capacity - > check if array needs to grow
        2: if needed, then call resize/gorw() to resize
        3: places new element at the end

       *** Linkedlist
       it is doubly linked list,
       it allows fast insertion/ removal at both ends and in the middle
       because it implements deque, it can be used as stack or queue
       No indexed access - no implement randomaccess
       not synchronized
       it does not have resize

       addFirst/addLast - O(1)
       removeFirst/removeLast - O(1)
       get(index) - O(n)
       add(Index, element) O(N) to find the position, and O(1) to insert and link


       how add function works?
            last node -> <- new Node
       1: create new Node with prev = last, next = null, item =e
       2: if list was empty, first = newNode, last.next = newNode
       3: last  = newNode; size++


       ***hashmap
       it offers average O(1) time for get and put
       does allow ONE Null key and multiple Null values
       it has resize function

       In java 8, hashmap uses linked list / red-black tree (self balancing tree)

       get     prev worst case: O(n),  new worst case: O(logn)
       containsKey  prev worst case O(N) ...........  O(logn)

       table: [linked list = 8] [ ] [] [] [] [] [] [] = 8 buckets

       how and when hashmap change linked list <-> tree
       1: linked list - > tree
            after insertion, a bucket's linkedlist length reaches:
            TREEIFY_THRESHOLD = 8,  -> this is your bucket size
            table size >=  MIN_TREEIFY_CAPACITY = 64 -> this is your table size
       2: UNTREEIFY_THRESHOLD = 6

       how put() works in hashmap
       1: first: compute hash value : hash(key): (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
           "^"-> this XOR
       2: compute bucket index: (n - 1) & hash where n is length of your table
       3: if the bucket is empty,  insert a new Node directly
       4: if the bucket is occupied :
            - walk the linked list (or tree) comparing hash and equals()
            to check if the key already exits
            - if key exits - replace the value and return the old value
            - if key doesnot exit - append a new node at the end of the linked list
            - if the length of linked list reaches 8 after insertion - attempt treeify
        5: increment size: if size > threshold , then call resize function
        6: return null or the old value


   Linked Hashmap -
   it shares a lot think with hashmap
   like time complexity for get. put. remove - they are same
   linked hashmap maintains a doubly linked list and insertion order
   or access order (LRU style)

   how put function works in linked hashmap
   same as hashmap

   TreeMap
   it just uses red - black tree
   all elements are sorted by key (it uses comparator)
   time complexity for get, put, remove, firstKey, lastKey,containsKey - O(logn)
   since it just tree , tree map does not have resize function

   how put works
   1: if tree is empty, create root, done.
   2: otherwise, traverse from root, comparing the new key with each nodes

   3:insert the new node as tree leaf at the found index

   Hashset - unique key
   hash set is backed by hashmap
   time complexity is same as hashmap
   also allows one null element
   no order for key
   linked list and tree conversion - it is same as hashmap
   how put works
   it same as hashmap

   linked hashset : maintains insertion, backed by linked hashmap instead of a plain hashmap

   TreeSet : stores unique, sorted key, backed by a treeMap


   Priority queue: search leetcode question  like top K
   it is a queue where elements are ordered by priority
   when you call poll(), it always return the smallest/ highest
   priority element.
   does not allow null element
   not thread safe

   time complexity
   offer/add - O(logn)
   poll/remove O(logn)
   peek() - O(1)

   Array Deque -
   array double ended queue,
   it is resizable array,
   allowing insertion/removal at both ends in O(1)
   can act as a stack or a queue
   is generally faster than stack and linked list foe some case

   not tread safe,
   does not allow null element

   time complexity
   addfirst/ addLast - O(1)
   removeFirst/removeLast - O(1)
   get(index) - O(n) -no indexed access















 */
public class Day2 {
}
