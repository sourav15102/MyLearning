Sort an array containing only 0,1,2 in O(n), and no auxiliary space.

Keep one pointer at start to monitor 0, one at end to monitor 2, and third pointer will move left->right, if it finds 0, swap itself with 1st pointer and 1st pointer++.