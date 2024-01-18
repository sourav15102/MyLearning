https://medium.com/geekculture/multisource-bfs-for-your-faang-coding-interviews-d5177753f507
https://codeforces.com/blog/entry/54310

The basic idea of the problem is that we have the tool to traverse the tree using bfs, but what if we need to perform the action from multiple sources, the bruteforce approach will be an overkill and wont be an optimized solution.

Approach: With a single source bfs, we put our source in queue at starting, with multiple source, we will do the same thing, i.e, put all sources at once in queue. 