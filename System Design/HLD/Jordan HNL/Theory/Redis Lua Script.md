#### **What is Redis Atomic Lua Scripting?**

Redis **Lua scripting** allows executing multiple Redis commands **atomically** in a single **transaction-like** operation. Lua scripts ensure that all commands inside the script are executed **sequentially and without interruption**, preventing race conditions in concurrent environments.
#### **What is Redis Atomic Lua Scripting?**

Redis **Lua scripting** allows executing multiple Redis commands **atomically** in a single **transaction-like** operation. Lua scripts ensure that all commands inside the script are executed **sequentially and without interruption**, preventing race conditions in concurrent environments.


### Atomicity:
Atomicity means that an operation (or a set of operations) executes **as a single, indivisible unit**, ensuring that no other operations can **interrupt** or modify the data in between.
### **🔍 Example: Incrementing an Integer with 100 Threads**

- Suppose **100 threads** are incrementing a counter **simultaneously**.
- If **not atomic**, a race condition can occur:
    - **All threads read `counter = 5` at the same time.**
    - Each thread **increments it to 6**.
    - Each thread writes **`counter = 6`**.
    - The final value is **6 instead of 105**!

🔴 **Problem Without Atomicity**:
```text
Initial counter: 5
Thread 1 reads: 5, increments → Writes 6
Thread 2 reads: 5, increments → Writes 6
Thread 3 reads: 5, increments → Writes 6
...
Final counter: 6  ❌ (Incorrect)

```

