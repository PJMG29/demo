package com.example.demo;

/***
 *
 *
 * Q1: Why does the JVM divide the heap into Young and Old generations instead of treating it as one big region?
 *
 * Key point to hit: Based on the "weak generational hypothesis" — most objects die young. Keeping a small, frequently-collected Young Generation makes the common case (collecting lots of short-lived garbage) fast, while the rarely-collected Old Generation only has to deal with objects that have already proven they're long-lived — avoiding the cost of repeatedly scanning long-lived objects during every collection.
 *
 * Q2: What are GC Roots, and why can't the JVM just use simple reference counting instead?
 *
 * Key point to hit: GC Roots (local variables on the stack, static fields, active threads, JNI references) are the starting points for a reachability analysis — anything not reachable from a root is garbage. Reference counting fails on cyclic references (two objects referencing each other but unreachable from any root) — it would never free them, since each still has a non-zero reference count from the other. Reachability-from-roots correctly identifies such cycles as garbage.
 *
 *What's the difference between the BLOCKED and WAITING thread states?
 *
 * Key point to hit: BLOCKED specifically means the thread is waiting to acquire a lock (e.g., stuck trying to enter a synchronized block held by another thread). WAITING means the thread voluntarily paused itself (via wait(), join(), or park() with no timeout) and is waiting for another thread to explicitly wake it up (notify()/notifyAll(), or the joined thread finishing) — no lock contention is necessarily involved.
 *
 *
 *
 * What's the difference between Thread.sleep() and Object.wait()?
 *
 * Key point to hit: sleep() is a static method that pauses the current thread for a fixed time and does not release any locks it holds. wait() is an instance method that must be called inside a synchronized block, releases the lock on that object while waiting, and only resumes when another thread calls notify()/notifyAll() (or a timeout passes, if one was given) — it's specifically designed for coordinating between threads, not just pausing execution.
 *
 *
 * What two guarantees does the volatile keyword provide, and what does it NOT guarantee?
 *
 * Key point to hit: It guarantees (1) visibility — writes are immediately flushed to main memory and reads always see the latest value, and (2) ordering — it prevents certain instruction reordering around it (via memory barriers), establishing a happens-before relationship. It does not make compound operations like count++ atomic — two threads can still race on a read-modify-write sequence even if the variable is volatile.
 *
 *What does synchronized guarantee beyond just "one thread at a time"?
 *
 * Key point to hit: In addition to mutual exclusion, entering/exiting a synchronized block establishes a happens-before relationship — any changes a thread made before releasing a lock are guaranteed to be visible to the next thread that acquires that same lock. So synchronized provides both mutual exclusion and the same kind of visibility guarantee that volatile provides, but for an entire block of code rather than a single variable.
 *
 *
 * When would you choose optimistic locking over pessimistic locking?
 *
 * Key point to hit: Choose optimistic locking when conflicts are expected to be rare and operations are relatively short — since no thread ever blocks, throughput is generally higher when contention is low. Choose pessimistic locking when conflicts are frequent or critical sections are long/expensive to redo — because with optimistic locking, frequent conflicts mean frequent retries, which can end up being more wasteful than simply waiting for a lock upfront.
 *
 * How does CAS achieve atomicity without using a traditional lock?
 *
 * Key point to hit: CAS is implemented as a single atomic CPU instruction that checks-and-updates a memory location in one indivisible hardware step, so there's no window of time in which another thread could interleave between the "check" and the "update." This lets Java build lock-free data structures (like AtomicInteger) that use a retry loop (get current value, compute new value, attempt CAS, retry on failure) instead of blocking threads with a lock.
 *
 *
 * What's the fundamental tradeoff between pessimistic and optimistic locking?
 *
 * Key point to hit: Pessimistic locking assumes conflicts are likely and blocks upfront to prevent them, at the cost of thread blocking and context-switch overhead. Optimistic locking assumes conflicts are rare and avoids blocking entirely, checking only at the end and retrying on conflict — cheap when contention is genuinely low, but wasteful (many failed retries) if contention turns out to be high.
 *
 * What does "atomic" mean, and why isn't count++ atomic on a plain int?
 *
 * Key point to hit: An atomic operation appears as one indivisible step to every other thread — no partial/intermediate state is ever observable. count++ is actually three separate steps (read, add, write-back); if two threads interleave these steps, an increment can be lost, since both might read the same starting value before either writes back.
 *
 *
 * Why doesn't ConcurrentHashMap allow null keys or values, even though HashMap allows a null key?
 *
 * Key point to hit: In a concurrent context, if map.get(key) returns null, there's an inherent ambiguity between "the key isn't present" and "the key is present but mapped to null" — resolving that ambiguity safely would require an extra containsKey() check, which itself could be stale by the time you check it in a multithreaded environment. Disallowing null entirely removes this ambiguity at the source.
 *
 *
 * What's the difference between offer(), put(), and add() on a BlockingQueue when the queue is full?
 *
 * Key point to hit: add() throws an exception (IllegalStateException) if the queue is full. offer() returns false immediately instead of throwing or blocking. put() blocks the calling thread until space becomes available — this blocking behavior is the defining feature of a BlockingQueue and is what makes it ideal for producer-consumer coordination without any manual wait()/notify() code.
 *
 * /

public class Day4and5Inteview {
}
