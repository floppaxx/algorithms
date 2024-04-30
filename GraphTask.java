import java.util.*;

/** Container class to different classes, that makes the whole
 * set of classes one class formally.
 */
public class GraphTask {


    // Function to find the furthest vertex from a given vertex using BFS
    /**
     * Find the furthest vertex from a given vertex using BFS
     * 
     * @param adjacencyMatrix
     * @param vertex
     * @return Result object containing the max distance and the furthest vertices
     */
    public Result findFurthestVertex(int[][] adjacencyMatrix, int vertex) {
      Queue<Integer> queue = new LinkedList<>();
      boolean[] visited = new boolean[adjacencyMatrix.length];
      int[] distances = new int[adjacencyMatrix.length];

     if (vertex < 1 || vertex > adjacencyMatrix.length) {
         throw new IllegalArgumentException("Invalid starting vertex " + vertex);
      }

      // Enqueue the starting vertex
      queue.add(vertex);
      visited[vertex - 1] = true;
      distances[vertex - 1] = 0;

      // Initialize the lastVisitedVertices list to store the furthest vertices
      List<Integer> furthestVertices = new ArrayList<>();
      furthestVertices.add(vertex);
      int maxDistance = 0;

      while (!queue.isEmpty()) {
          int currentVertex = queue.poll();

          // Visit all adjacent vertices
          for (int i = 0; i < adjacencyMatrix.length; i++) {
              if (adjacencyMatrix[currentVertex - 1][i] == 1 && !visited[i]) {
                  queue.add(i + 1);
                  visited[i] = true;
                  distances[i] = distances[currentVertex - 1] + 1;
                  if (distances[i] > maxDistance) {
                      maxDistance = distances[i];
                      furthestVertices.clear();
                      furthestVertices.add(i + 1);
                  } else if (distances[i] == maxDistance) {
                      furthestVertices.add(i + 1);
                  }
              }
          }
      }

         return new Result(maxDistance, furthestVertices);
      }

      public class Result {
         private int maxDistance;
         private List<Integer> furthestVertices;

         public Result(int maxDistance, List<Integer> furthestVertices) {
            this.maxDistance = maxDistance;
            this.furthestVertices = furthestVertices;
         }

         public int getMaxDistance() {
            return maxDistance;
         }

         public List<Integer> getFurthestVertices() {
            return furthestVertices;
         }
      }
  

   /** Main method. */
   public static void main (String[] args) {
      GraphTask a = new GraphTask();
      a.run();
   }

   /** Actual main method to run examples and everything. */
   public void run() {
      Graph g = new Graph ("G");
      int[][] matrix = g.createRandomSimpleGraph(50,50);
      System.out.println(g);

      
      // Test case 1: Starting from vertex 0
      //Result result = findFurthestVertex(matrix, 0);
      //Test case 2: Starting from vertex that is not in the graph
      //Result result = findFurthestVertex(matrix, 51);
      //Test case 3: Test case with a graph with only one vertex
      //Result result = findFurthestVertex(new int[][]{{1}}, 1);
      //Test case 4: Test case with a graph with only two vertices
      //Result result = findFurthestVertex(new int[][]{{1, 1}, {1, 1}}, 1);
      //Test case 5: Test case with 2000 vertices
      int[][] matrix2 = g.createRandomSimpleGraph(2000, 2000);
      Result result = findFurthestVertex(matrix2, 1);
      int maxDistanceFromGiven = result.getMaxDistance();
      List<Integer> furthestVerticesFromGiven = result.getFurthestVertices();

      System.out.println("Furthest vertex from given vertex: " + furthestVerticesFromGiven);
      System.out.println("Max distance from given vertex: " + maxDistanceFromGiven);

   }



   /*
    * 
    */
   class Vertex {

      private String id;
      private Vertex next;
      private Arc first;
      private int info = 0;
      // You can add more fields, if needed

      Vertex (String s, Vertex v, Arc e) {
         id = s;
         next = v;
         first = e;
      }

      Vertex (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }

      // TODO!!! Your Vertex methods here!
   }


   /** Arc represents one arrow in the graph. Two-directional edges are
    * represented by two Arc objects (for both directions).
    */
   class Arc {

      private String id;
      private Vertex target;
      private Arc next;
      private int info = 0;
      // You can add more fields, if needed

      Arc (String s, Vertex v, Arc a) {
         id = s;
         target = v;
         next = a;
      }

      Arc (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }

      // TODO!!! Your Arc methods here!
   } 


   /** This header represents a graph.
    */ 
   class Graph {

      private String id;
      private Vertex first;
      private int info = 0;
      // You can add more fields, if needed

      Graph (String s, Vertex v) {
         id = s;
         first = v;
      }

      Graph (String s) {
         this (s, null);
      }

      @Override
      public String toString() {
         String nl = System.getProperty ("line.separator");
         StringBuffer sb = new StringBuffer (nl);
         sb.append (id);
         sb.append (nl);
         Vertex v = first;
         while (v != null) {
            sb.append (v.toString());
            sb.append (" -->");
            Arc a = v.first;
            while (a != null) {
               sb.append (" ");
               sb.append (a.toString());
               sb.append (" (");
               sb.append (v.toString());
               sb.append ("->");
               sb.append (a.target.toString());
               sb.append (")");
               a = a.next;
            }
            sb.append (nl);
            v = v.next;
         }
         return sb.toString();
      }

      public Vertex createVertex (String vid) {
         Vertex res = new Vertex (vid);
         res.next = first;
         first = res;
         return res;
      }

      public Arc createArc (String aid, Vertex from, Vertex to) {
         Arc res = new Arc (aid);
         res.next = from.first;
         from.first = res;
         res.target = to;
         return res;
      }

      /**
       * Create a connected undirected random tree with n vertices.
       * Each new vertex is connected to some random existing vertex.
       * @param n number of vertices added to this graph
       */
      public void createRandomTree (int n) {
         if (n <= 0)
            return;
         Vertex[] varray = new Vertex [n];
         for (int i = 0; i < n; i++) {
            varray [i] = createVertex ("v" + String.valueOf(n-i));
            if (i > 0) {
               int vnr = (int)(Math.random()*i);
               createArc ("a" + varray [vnr].toString() + "_"
                  + varray [i].toString(), varray [vnr], varray [i]);
               createArc ("a" + varray [i].toString() + "_"
                  + varray [vnr].toString(), varray [i], varray [vnr]);
            } else {}
         }
      }

      /**
       * Create an adjacency matrix of this graph.
       * Side effect: corrupts info fields in the graph
       * @return adjacency matrix
       */
      public int[][] createAdjMatrix() {
         info = 0;
         Vertex v = first;
         while (v != null) {
            v.info = info++;
            v = v.next;
         }
         int[][] res = new int [info][info];
         v = first;
         while (v != null) {
            int i = v.info;
            Arc a = v.first;
            while (a != null) {
               int j = a.target.info;
               res [i][j]++;
               a = a.next;
            }
            v = v.next;
         }
         return res;
      }

      /**
       * Create a connected simple (undirected, no loops, no multiple
       * arcs) random graph with n vertices and m edges.
       * Returns the adjacency matrix of the graph.
       * @param n number of vertices
       * @param m number of edges
       */
      public int[][] createRandomSimpleGraph (int n, int m) {
         if (n <= 0)
            return new int[0][0];
         if (n > 2500)
            throw new IllegalArgumentException ("Too many vertices: " + n);
         if (m < n-1 || m > n*(n-1)/2)
            throw new IllegalArgumentException 
               ("Impossible number of edges: " + m);
         first = null;
         createRandomTree (n);       // n-1 edges created here
         Vertex[] vert = new Vertex [n];
         Vertex v = first;
         int c = 0;
         while (v != null) {
            vert[c++] = v;
            v = v.next;
         }
         int[][] connected = createAdjMatrix();
         int edgeCount = m - n + 1;  // remaining edges
         while (edgeCount > 0) {
            int i = (int)(Math.random()*n);  // random source
            int j = (int)(Math.random()*n);  // random target
            if (i==j) 
               continue;  // no loops
            if (connected [i][j] != 0 || connected [j][i] != 0) 
               continue;  // no multiple edges
            Vertex vi = vert [i];
            Vertex vj = vert [j];
            createArc ("a" + vi.toString() + "_" + vj.toString(), vi, vj);
            connected [i][j] = 1;
            createArc ("a" + vj.toString() + "_" + vi.toString(), vj, vi);
            connected [j][i] = 1;
            edgeCount--;  // a new edge happily created
         }

         // Print the adjacency matrix to visualize the graph
         // converting each row as string
         // and then printing in a separate line
         //for (int[] row : connected)
         //System.out.println(Arrays.toString(row));

         return connected;

      }

   }
} 

