# UMT_Software_Technical_Test
This is the solution that I came up with for the technical test from the UMT Software, I choose to write the solution in Java.

#The Problem:
The given problem was:
"In a given integer array A, we must move every element of A to either list B or list C. (B and C
initially start empty.)
Return true if and only if after such a move, it is possible that the average value of B is equal to
the average value of C, and B and C are both non-empty."

#The Solution:
I first did a prunning to check if the given list can be split into the two sublists so they can have the same average, and like this we manage to exclude the ones  that are not checking this criteria from the start. Having the given list separated into 2 sublists B and C with the lengths D, respectively E. We know that sum(B(i))/D = sum(C(i))/E, from here we can demonstrate that sum(B(i)) = (D * sum(A))/the_length_of_A; and this is the formula that the prunning is based on. After this, with the use of dynamic programming is created a data structure that holds the sum of subsets( first of 0 item, 1 item , then 2 items and so on, until we have m = n/2 + 1 items), this is stored in a list in which the elements are TreeSets. After that we go through the set and see if the current value is found somewhere else in the set, we use an iterator to find the index of an element and check if this can be found or not in other set. The input that needs to be given for this programm to behave corespondingly is a list on a single row, its items need to be separated by a space character(" "), I implemented a function that reads this and creates the list that will be given to the other functions so it can verify if it checks the criteria or not.The output will be true if the given list can be split into 2 sublists that have the same average , or false if it can not.
