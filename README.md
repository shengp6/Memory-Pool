# Memory-Pool
This project is to store a series of strings into a memory pool,
which is a byte array. Users can insert or remove strings, 
and print the free blocks of byte array. We use a handle to indicate 
the start position of a specific string, and store the handle in hash table.
When the users insert a new string, the project will decide to put the string
into an available free block in the memory, or resize the memory pool. 
