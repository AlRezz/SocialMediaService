    Service description
Service has been built for finding maximum number of  pairs based on shared interests in social network.

    Service architecture
Search service was developed as a distributed, loosely coupled microservice. It employs lightweight communication
protocol such as HTTP. 

    Service environment
As a microservice, search service can work as an independent application. 

    Description
The Hungarian Method was chosen (as known as Kuhn algorithm), because it is a combinatorial optimization algorithm that solves the assignment problem in polynomial time O(n^3) for square matrix.

    Problems
If there were be a lot of entries with a huge lists of interests, we would get a low performance  depends on execution time.
Issues with read and write operations if we stored information in database.        
To avoid problems 
   choose a right isolation level for preventing issues  
   use read-only transactions in controllers for reading information.     
   add cache for result and update it entries based on expiration date  
    
    Misc
POST http://{server}/findpairs

server - address of server

Request example src/resources/request.json
