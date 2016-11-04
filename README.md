There must be a single node manager actor instance per JVM.
There can be multiple partitions and hence partition managers under a node manager.
There can be multiple instances of partition manager actor for a given partition in a JVM.
They share the same partition manager object.