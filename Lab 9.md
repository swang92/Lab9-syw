### Lab 9

**Question 1**

TreeMap, HashMap and myHashMap should result in all the same word frequencies. TreeMap and Hashmaps are more efficient then our implementation with linkedlist and arraylists.


**Question 2**

Math.floorMod is used isntead of % so that we still get a positive value as a remainder even if the hashcode is negative. 

**Question 3**

O(n) because we are using the getSize method for our linkedlist but also using a for loop to iterate through our ArrayList that holds the buckets of LinkedLists. 

**Question 4**

This implementation has better performance, correctness and reliability (save the potential of collisions but that can be somewhat avoided with using a prime number for table size). I think the nodes had an easier implementation because you're not having to iterate essentially twice (through the LinkedList and the ArrayList) and just being able to either point to Nodes or access/remove directly.  
 

