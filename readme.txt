Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
For deque, I chose to use a doubly-linked list to implement. This was because
it gave me constant access to both the front and the back of the list. It
also allowed me to traverse the list in linear time because each Node referenced
the one in front and behind of itself.

For randomized queue, I chose to use an array to implement. This was because the
randomized component of the data-type required that I be able to access the middle
of the queue in constant time. This could only be done through an array.

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~32n  bytes
*** RandomizedQueue utilizes a resizing array which resizes itself down once
25% capacity is reached. In the worst case, the array is only 25% full with 8
byte references per index. This leads it to have 32n bytes of memory. ***

Deque:              ~48n  bytes
*** Deque utilizes a doubly linked list. Each Node has a reference to the
previous Node (8 bytes), the next Node (8 bytes), its contained Item (8 bytes),
over head (16 bytes), and an additional 8 bytes for being a non-static inner
class. ***




/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
N/A

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
Sabyha (TA) 02/13/2023



/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
N/A


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
N/A
