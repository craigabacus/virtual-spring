For the project:
This works:
mvn clean test
mvn clean test -Dtest=VirtualSpringApplicationTests
mvn clean test -Dtest= VirtualTest
mvn clean test -Dtest= VirtualTest -Djdk.tracePinnedThreads=full

This hangs:
mvn clean test -Djdk.tracePinnedThreads=full
mvn clean test -Dtest=VirtualSpringApplicationTests -Djdk.tracePinnedThreads=full
