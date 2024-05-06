/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author User
 */
public class RivalRace {
    static String[] cities = {"Saffron City", "Lavender Town","Carulean CIty","Celadon City",
        "Vermillion City","Fuschia City","Cinnabar Island", "Pallet Town","Viridian City","Pewter City"};
    private LinkedList<Integer> adj =new LinkedList<>();
    private int V; //number of vertices
    
    private static final int INF = Integer.MAX_VALUE;
    
     public void call(){
         RivalRace rc = new RivalRace();
         
         int[][] weightedGraph = {
                {0, 3, 6, 4, 3, 0, 0, 0, 0, 0},
                {3, 0, 9, 0, 5, 11, 0, 0, 0, 0},
                {6, 9, 0, 0, 0, 0, 0, 0, 0, 12},
                {4, 0, 0, 0, 0, 10, 0, 0, 0, 0},
                {3, 5, 0, 0, 0, 7, 0, 0, 0, 0},
                {0, 11, 0, 10, 7, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 0, 7, 0, 0},
                {8, 11, 0, 0, 0, 0, 7, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 5, 0, 8},
                {0, 0, 12, 0, 0, 0, 0, 0, 8, 0}
        
         };
        Random random = new Random();
        // Randomly choose one int[] value to indicate the destination
        int destination = random.nextInt(5,weightedGraph.length);
        rc.showRivalMap();
        System.out.println("The battle has begun! Your rival Gary has challenged you to a race to "+ cities[destination]);
         System.out.println("");
        dijkstra(weightedGraph, 0, destination);
    }

     
     
     public static void dijkstra(int[][] graph, int src, int dest) {
        int V = graph.length;
        //keep track the shortest distance between every node 
        int[] dist = new int[V];
        int[] prev = new int[V];
        boolean[] visited = new boolean[V];
        //put all unvisited node as infintiy 
        Arrays.fill(dist, INF);
        //set the initial node as 0
        dist[src] = 0;
        
        //Open a priority queue to have the comparable set to the first one always be the shortest distance form the src 
        //By default, the smaller value will have the higher priority
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v]));
        pq.add(src);
        
        //**** For every iteration , the pq will have multiple element which allow the line is always shorter from the src
        //Comparator will ensure that it always has to be the shortest distance******
        //This means that even if a vertex u has traveled further away from the source, 
        //if there is a shorter path to a neighbor v through u, v will be considered for exploration before other vertices that are farther away.
        while (!pq.isEmpty()) {
            int u = pq.poll();
            
            //pop it out to stated that is visited
            visited[u] = true;
            
            //if the u reach the destination, then shortest distance found
            if (u == dest) {
                System.out.println("Shortest distance from Saffron City to " + cities[dest] + ": " + dist[u]);
                System.out.println("");
                printPath(prev, src, dest);// Store u as the previous city for v
                System.out.println("");
                System.out.println("Good Luck !!!!!!!!!!!!!!!!!!");
                return;
            }
            
            //check the adjacent vertex to have a shorter path thru src
            // check 1 :Ensures that the neighbor v has not been visited yet
            // check 2 : Checks if there is an edge between u and v
            // check 3: Checks if the distance to v through u is shorter than the current known shortest distance to v. 
            // [u][v] to check for every row, the respective vertex will have edges with which neighbour
            //dist[u] + graph[u][v] , is to check which wan having shorter distance than the dist[v] compared by the comparator
            //Even if a vertex u has already been visited and its distance is not the shortest path, if there is a shorter path to a neighbor v through u,
            //the algorithm will update the distance to v and explore v further.
            for (int v = 0; v < V; v++) {          
                if (!visited[v] && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v]=u;
                    pq.add(v);
                }
            }
        }

        System.out.println("No path found from vertex " + src + " to vertex " + dest);
    }
     
     
      public static void printPath(int[] prev, int src, int dest) {
        System.out.print("Shortest path: ");
        LinkedList<String> path = new LinkedList<>();
        int current = dest;
        while (current != src) {
            path.addFirst(cities[current]);
            current = prev[current];
        }
        path.addFirst(cities[src]);
        System.out.println(String.join(" -> ", path));
    }

     public void showRivalMap(){
         System.out.println(" ________________________________________________________________________________________");
         System.out.println("                                                                                   ");
         System.out.println("    | Pewter City | ------------12------------| Verulean City |--------------9                                                                       ");
         System.out.println("           |                                           |                     |                            ");
         System.out.println("           |                                           |                     |                            ");
         System.out.println("           |                                           6                     |                            ");
         System.out.println("           8                                           |                     |          ");
         System.out.println("           |                                           |                     |                            ");
         System.out.println("           |              |Celadon City|---4---| Saffron City |---3---| Lavender Town |                                ");
         System.out.println("           |                    |                     |                 /                                  ");
         System.out.println("           |                    |                     |                /                                  ");
         System.out.println("           |                    |                     |               /                                  ");
         System.out.println("    | Viridian City |           |                     |              /                                             ");
         System.out.println("           |                    |                     3             5                                      ");
         System.out.println("           |                    |                     |            /                                       "); 
         System.out.println("           |                    |                     |           /                                       "); 
         System.out.println("           |                    |                     |          /                                       "); 
         System.out.println("           |                    |                     |         /                                       "); 
         System.out.println("           5                    |                     |        /                                             ");
         System.out.println("           |                    |              |Vermillion City|                                            ");
         System.out.println("           |                    10                 /                                              "); 
         System.out.println("    | Pallet Town |             |                 /                                                       ");
         System.out.println("           |                    |                /                                               ");
         System.out.println("           |                    |               /                                               ");
         System.out.println("           |                    |              /                                               ");
         System.out.println("           |                    |             7                                                "); 
         System.out.println("           7                    |            /                                                     ");
         System.out.println("           |                    |           /                                               "); 
         System.out.println("           |                    |          /                                               "); 
         System.out.println("           |                    |         /                                               ");
         System.out.println("           |                    |        /                                               "); 
         System.out.println("    |Cinnabar Island|---5---|Fuschia City|                                                                                  ");   
         System.out.println("                                                                                   ");   
         System.out.println("________________________________________________________________________________________");
     }
}
