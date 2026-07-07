package javaConcepts;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *What is a thread in java?
 * Single thread:  [task 1] -> [task2]...
 * multi threading: thread 1 ->[ task 1]
 *                  thread 2 -> [task 2]
 *                  ....
 *                  all running at the same time
 * // old way to create one thread
 * class MyThread extends Thread{
 *
 * @Override
 * public void run(){
 *     // this your task implementation
 * }
 * }
 *
 * // new way -> runnable functional interface
 * Thread t = new Thread(() -> {
 *     sout("Running thread ->" + Thread.currentThread().getName());
 *
 * })
 *
 * the problems with creating threads manually:
 * 1: how do you know how many threads you need when your system is running
 * 2: creating a thread and destory a thread are expensive!!!!
 * 3: no reuse
 *
 * how can we solve above problems? -> thread pool -> a place that holds a lot threads
 * Thread pool: let's say your thread pool can only have 5 threads
 *  thread 1 ->idle
 *  thread 2 -> running taskA
 *  thread 3 -> running task B
 *  thread 4 -> idle
 *  thread 5 -> working on task C
 *  if you want to submit task D and E to thread pool and then thread 1 and thread 4 will
 *  work on it
 *  if you want to submit task F to thread pool , if there is not idle worker, then you
 *  have to wait outside.
 *
 *
 *  Executor vs ExecutorService vs Executors
 *  Executor : it is an interface and only has one method:
 *      void execute(Runnable command); -> will execute a task
 *
 *
 *  Executor Service: it is same with executor, but it provides more
 *  useful functionality: submitting task that returns a result
 *  , shutting the pool down safely. waiting for tasks to complete
 *
 *      Runnable vs Callable:
 *          runnable: return voids and cannot throw checked exception
 *          Callable: return a value of type T, and throw checked exception
 *
 * Executors:
 *  this class allows you to create different threads
 *
 *  newFixedthreadpool(n) -> a pool with exactly n threads always
 *  newCachedThreadPool -> a pool that creates a new thread as needed, but reuses idle threads and kill threads that stay idle for 60 seconds
 *  newSingleThreadExecutor -> a pool with 1 thread
 *  newScheduledThreadPool() ->
 *  newWorkStealPool() ->
 *
 *  but we do not use Executors in real world
 *  what we use is : ThreadPoolExecutor
 *  the reason is that you can have bounded tasks in task queue
 *  which mean you are trying to avoid -> OutOfMemoryError.
 *
 *
 *  execute function v s submit() function
 *  execute(): fire - and - forget, no result, no way to know if/how it failed
 *  submit(): still no result,  but you get a future you can check for completion/ execeptions
 *
 *
 * Future and Completable Future
 * Completable Future implements Future class, but add a huge set
 * of extra features that allow you manage your tasks
 * like you can chain what happens after a task completes,
 * or combine multiple async tasks together
 * or handle exceptions
 *
 *
 *
 * Java 21 new feature -> virtual thread
 * A thread is the smallest unit of execution in java
 *
 * how java create a thread -> your thread will be managed by OS
 * your java code - new Thread(() ->{doWork()}) -> JVM
 *  -> make a system call -> OS -> create OS thread , allocates 1BM memory
 *  adds to OS scheduler -> decides when and where it runs -> cup cores
 *
 *
 *  Virtual threads are lightweight threads managed by the JVM
 *  instead of the OS
 *
 *  your java code ->
 *  Thread.ofVirtual().start((0-> dowork())
 *  -> JVM Scheduler <- fully managed inside the JVM
 *  -> mounts virtual thread onto a carrier thread, unmounts when blocking, remount when ready.
 *  -> carrier thread (OS thread, platform thread)
 *  ->OS scheduler
 *  -> CPU cores
 *
 *  when you create a virtual thread, the JVM create a lightweight object on the heap
 * a virtual thread is an object!!!
 *
 * Java thread -> 1:1 mapping -> OS thread
 * Java virtual thread -> M:N mapping -> OS
 *
 *
 *
 *What is JVM
 * it stands for java virtual machine
 * it is used for running and compilling java code
 *
 * your java code -> Java compiler -> turns your java code->
 * bytecode: .class file
 * -> JVM: class loader -> will load .class file
 *      Execution engine: it is for interpreting and executing the
 *      bytecode that is loaded into memory by the class loader
 *    runtime data areas: it is a memory to store bytecode, objects....
 *
 *   Memory: three -> memory area, heap and stack
 *   heap -> important !!!!
 *   the heap is used for managing ans storing objects
 *   The components of heap are young generation, old generation
 *   and metaspace
 *   young generation:  this is a place where newly created objects are stored
 *   User user = new User("Matt") -> newly created -> will be stored in young generation
 *   the components of yong generation:
 *      1: eden space: the main area where most of the new objects are created
 *      2: survivor spaces: S0 and S1. one used as the source and the other used as the target for moving live objects after a GC
 *    Old generation: when objects in the survivor spaces survive multiple GC, they are eventually moved to
 *    the old generation.
 *   stack: is used to store data for method invocations.
 *
 *
 *
 * how do we do an object is alive!?
 * you can use GC roots
 * it is a starting points the GC uses to find out which objects
 * are reachable (in use) and which are garbage( safe to delete)
 *  it is like a family tree : GC roots are the top of tree
 *  and any object connected to root - directly or through a chain of references
 *  is considered "alive" . Anything floating with no path back to a root gets collected.
 *
 *  GC - : garbage collection
 *  1:  mark - sweep
 *  starting from GC roots, traverse all reachable objects and mark them
 *  as "alive"
 *  sweep: means delete all objects that are not reachable and free memory.
 *
 *  2:Mark - compact
 *  mark: same as above - find all reachable objects
 *  compact: moving all surviving objects together so that you have larger space to store more objects
 *   -> we care eliminating fragmentation.
 *
 *   3: copying
 *   1: divide memory into two halves - one (in use) and one "empty"
 *   2: only allocate objects in the active half
 *   3: when it is time to collect: copy all surviving objects into the empty half
 *   then treat the old half as free and swap the roles of the two halves
 *
 * stop-the-world (pause): stop your application
 * G1 GC:
 *  1:Initial mark phase: mark GC roots
 *  2: root region scanning - scan survivor regions for references into the old generation
 *  3: concurrent marking: traverse the whole heap to find all reachable object while your application keeps runing
 *  4: remark phase: stop the world happens here. to finalize marking, catch any changes
 *  that happened during concurrent marking phase
 *  5: cleanup
 *
 *  CMS : concurrent mark sweep
 *      1: initial mark
 *      2: concurrent mark
 *      3: remark
 *      4: concurrent sweep
 *
 *
 *  thread states:
 *  1: new -> like you call "Thread myThread = new Thread()" but myThread.start() has not been called yet
 *  2: runnable -> thread is running, or it is ready to go (your thread has CPU resource)
 *  3: blocked -> thread is waiting to acquire a lock
 *  4: waiting -> the thread is waiting for another thread to do something
 *      4.1: timed - waiting
 *  5: terminated: the thread has finished.
 *
 *
 *  Thread.wait() -  it belongs to Thread class
 *             it is a static method on Thread
 *             pauses the current thread to at least the given number of milliseconds
 *             **Does not release any locks the thread currently holds
 *  Object.wait()
 *      - an instance method, available on every object,
 *      this one must be called inside a synchronized block on that same object
 *      **this one will release the lock
 *   Object.notify() / Object.notifyAll()
 *      must be called inside a synchronized block on the same object
 *      - notify wakes up one thread that's currently waiting on that object
 *      - notifyAll wakes up all threads.....
 *
 *
 *
 *    ******volatile keyword******
 *    **1: visibility - every writes/updates to a volatile variable is immediately flushed to
 *    main memory, and every read of it goes straight to main memory - other thread will know this
 *    value is updated by other thread.
 *    Thread A
 *    [local memory - shared variable C]
 *                                              [main memory: shared variable C ]
 *    Thread B
 *    [local memory - shared variable C]
 *    **2:prevent to reordering
 *      int a = 1
 *      int b = 2
 *      int c = 3
 *      after you compile your code -> CPU will execute code in this ordering
 *      int c = 3
 *      int a = 1
 *      int b = 2
 *      cpu thinks this ordering will give us better performance
 *
 *      with volatile:
 *      volatile int a = 1
 *      volatile int b  = 2;
 *      volatile int c = 3
 *      after you compile your code
 *      cpu will execute in this ordering
 *       volatile int a = 1
 *  *     volatile int b  = 2;
 *  *     volatile int c = 3
 *  it does not reorder your code!!!!!
 *
 *
 *  the synchronized keyword
 *   it ensures that only one thread at a time can execute
 *   give block of code for a given object
 *   it gives you thread safe!!
 *   Synchronized method
 *   public synchronized void increment(){
 *       count++;
 *   }
 *
 *   synchronized block
 *   private final Object lock = new Object();
 *   public void increment(){
 *       synchronized (lock){
 *           count++;
 *       }
 *   }
 *   synchronized static method
 *      public static synchronized void increment(){
 *  *       count++;
 *  *   }
 *
 *  what actually happens under the hood
 *  every java object has a monitor (lock), when a thread enters a
 *  synchronized block
 *  1: it tires to acquire the monitor lock for that object
 *  2: if no other thread holds it, it get the lock and proceeds
 *  3: if another thread already holds it, this thread goes into block state
 *  4: when the thread exits the synchronized block, it automatically release the lock. the current thread will get it
 *
 *
 *
 *
 * Pessimistic locker vs optimistic lockers
 * the core idea of pessimistic locker
 * 1: it assumes that  conflicts are likely happen,
 * so it locks a resource before doing any work on it
 *  - blocking every other thread from touching that shared resource until
 *  the current thread is finished and releases the lock.
 *  common pessimistic locker:
 *  synchronized keyword
 *  reentrantlock
 *
 *  Optimistic locker
 *  the core idea:
 *  it assumes that conflicts are rare
 *  instead of locking upfront, it just does the work
 *  then checks at the end whether the data was still what it expected - if yes, commit , if no ,retry it
 *
 *  common examples:
 *  atomicInteger/Long/Boolean....
 *
 *  what "atomic" means?
 *  an operation is atomic if it appears to happen as a single
 *  or if any errors/exceptions happen, none of them will be executed
 *
 *  CAS : compare and set
 *  compareAndSet(expectedValue, newValue){
 *      if(currentValue == expectedValue){
 *          currentValue = newValue
 *          return true
 *      }else{
 *          return false
 *      }
 *  }
 *
 *  The ABA problem
 *  what it is?
 *  CAS only checks "is the current value still equal to what I last time saw"
 *  it has no way to know if the value changed and then changed back in between: A->B->A
 *  we can use atomicStampedReference class
 *
 *  ConcurrentHashMap:
 *   it is a thread safe map implementation designed for high - concurrency access
 *
 *   the underlying structure is the same bucket array used bu hashmap
 *   same treeification rules applied.
 *   same red-black tree
 *   same linked list
 *
 *   read function in concurrent hashmap is lock-free
 *   no all of functions are in concurrent hashmap are thread safe
 *   putIfAbsent
 *   computeifAbsent
 *   compute
 *   get() - read function - lock free read
 *
 *   key point to remember:
 *   1: concurrent hashmap : does not allow null keys or null values!!!!
 *
 *   blocking queue
 *   it is an interface that support operations which wait when
 *   queue is in an unusable state
 *   we have pointers in blocking queue:
 *   1: add pointer - insert
 *   2: remove pointer - remove elements
 *
 *   insertion in blocking queue:
 *   add(e) -> throw an exception if the queue is full
 *   offer(e) -> return false/null instead of giving you exception ot blocking
 *   put(e) -> block until space available
 *
 *   remove
 *   remove() -> throw an exception if queue is empty
 *   take() -> block until an element  becomes available
 *   poll() -> return null instead of give an exception or blocking
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class Day4 {
    private final ReentrantLock lock = new ReentrantLock();
    private Integer count  =0;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(()-> System.out.println("running"));
        Future<?> future1 = pool.submit(() -> System.out.println("running"));

        boolean done = future1.isDone();
        Future<Integer> f2 = pool.submit(()->{
            Thread.sleep(1000);
            return 100;
        });
        Integer result = f2.get();

        CompletableFuture<Integer> cfA = CompletableFuture.supplyAsync( () -> 1);
        CompletableFuture<Integer> cfB = CompletableFuture.supplyAsync( () ->2);

        CompletableFuture<Integer> combined = cfA.thenCombine(cfB, (a,b) -> a + b);
        System.out.println(combined.get());

        CompletableFuture<Void> all = CompletableFuture.allOf(cfA, cfB);
        all.get();

        CompletableFuture<Object> any = CompletableFuture.anyOf(cfA,cfB);
        System.out.println(any.get());


        AtomicInteger counter = new AtomicInteger(0);
        counter.get(); //read current value
        counter.set(5);
        counter.incrementAndGet(); // ++ count, atomically, return new value
        counter.addAndGet(10);// count +=10, atomically
        counter.compareAndSet(15,100);// counter =100


        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    }
    public void increment(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();// MUST manually unlock
        }
    }
}
