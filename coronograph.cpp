/*based on :
https://www.geeksforgeeks.org/sort-algorithms-the-c-standard-template-library-stl/
https://www.geeksforgeeks.org/calculate-number-nodes-subtrees-using-dfs/
https://www.geeksforgeeks.org/merge-sort/
*/
#include <iostream>
#include <list>
#include <limits.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
// variables to be used
// in both functions
const int N=100000;
// arrays required to color the
// graph, store the parent of node
int color[N];
int par[N];
int mark[N];
vector<int> graph[N];
vector<int> cycles[N];
int count1[N];
int counter=0;

void number_of_nodes(int s, int e);
// Function to mark the vertex with
// different colors for different cycles
void dfs_cycle(int u, int p, int color[],
               int mark[], int par[], int& cyclenumber)
{

    // already (completely) visited vertex.
    if (color[u] == 2) {
        return;
    }

    // seen vertex, but was not completely visited -> cycle detected.
    // backtrack based on parents to find the complete cycle.
    if (color[u] == 1) {

        cyclenumber++;
        int cur = p;
        mark[cur] = cyclenumber;

        // backtrack the vertex which are
        // in the current cycle thats found
        while (cur != u) {
            cur = par[cur];
            mark[cur] = cyclenumber;
        }
        return;
    }
    par[u] = p;

    // partially visited.
    color[u] = 1;

    // simple dfs on graph
    for (int v : graph[u]) {

        // if it has not been visited previously
        if (v == par[u]) {
            continue;
        }
        dfs_cycle(v, u, color, mark, par, cyclenumber);
    }

    // completely visited.
    color[u] = 2;
}

// add the edges to the graph
void addEdge(int u, int v)
{
    graph[u].push_back(v);
    graph[v].push_back(u);
}

// Function to print the cycles
int list_Cycles(int edges, int mark[], int& cyclenumber)
{

    // push the edges that into the
    // cycle adjacency list
    for (int i = 1; i <= edges; i++) {
        if (mark[i] != 0){
            cycles[mark[i]].push_back(i);
            counter=counter + 1;
          }
    }
      return cyclenumber;
    /*// print all the vertex with same cycle
    for (int i = 1; i <= cyclenumber; i++) {
        // Print the i-th cycle
        cout << "Cycle Number " << i << ": ";
        for (int x : cycles[i])
            cout << x << " ";
        cout << endl;
    }*/
}

void number_of_nodes_root(int s){
  vector<int>::iterator u;
  count1[s] = 1;

  for (u = graph[s].begin(); u != graph[s].end(); u++) {

      for(int x : cycles[1]){
        if((*u)==x){
          goto BREAK;
        }
      }
    /*  if((*u)==x){   //bgainei kai apo to allo for kai paei sthn epomenh epanalhpsy tou megalou
        break;       //forloop
      }*/
        number_of_nodes(*u,s);
        // update count[] value of parent using
        // its children
        count1[s] += count1[*u];
      BREAK: continue;
      }

  return;
  }




// function to calculate no. of nodes in subtree
void number_of_nodes(int s, int e){
    vector<int>::iterator u;
    count1[s] = 1;
    for (u = graph[s].begin(); u != graph[s].end(); u++) {

        // condition to omit reverse path
        // path from children to parent
        if (*u == e)
            continue;

        // recursive call for DFS
        number_of_nodes(*u, s);

        // update count[] value of parent using
        // its children
        count1[s] += count1[*u];
    }
    return;
}

void merge(int arr[], int l, int m, int r)
{
    int i, j, k;
    int n1 = m - l + 1;
    int n2 =  r - m;

    /* create temp arrays */
    int L[n1], R[n2];

    /* Copy data to temp arrays L[] and R[] */
    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1+ j];

    /* Merge the temp arrays back into arr[l..r]*/
    i = 0; // Initial index of first subarray
    j = 0; // Initial index of second subarray
    k = l; // Initial index of merged subarray
    while (i < n1 && j < n2)
    {
        if (L[i] <= R[j])
        {
            arr[k] = L[i];
            i++;
        }
        else
        {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    /* Copy the remaining elements of L[], if there
       are any */
    while (i < n1)
    {
        arr[k] = L[i];
        i++;
        k++;
    }

    /* Copy the remaining elements of R[], if there
       are any */
    while (j < n2)
    {
        arr[k] = R[j];
        j++;
        k++;
    }
}

void mergesort(int arr[], int l, int r)
{
    if (l < r)
    {
        // Same as (l+r)/2, but avoids overflow for
        // large l and h
        int m = l+(r-l)/2;

        // Sort first and second halves
        mergesort(arr, l, m);
        mergesort(arr, m+1, r);

        merge(arr, l, m, r);
    }
}




int main(int argc, char **argv){

int T ;    //plhthos grafvn
ifstream infile;
infile.open(argv[1]);
infile >> T ;   //diabazv plhthos grafvn
for(int t=0; t<T; t++){


 int total_vertices=0;
 counter=0;
 int V,edges;
 infile >> V >> edges;



int edge1,edge2;
for (int i=0; i<edges;i++){
  infile >> edge1 >> edge2;
  addEdge(edge1,edge2);
}


// store the numbers of cycle
    int cyclenumber = 0;

// call DFS to mark the cycles
    dfs_cycle(1, 0, color, mark, par, cyclenumber);

// function to print the cycles
  int number_of_cycles=list_Cycles(edges, mark, cyclenumber);
  //cout<<number_of_cycles;
  int beer[counter];

  if(number_of_cycles==1){
    int b=0;

    for(int y : cycles[1]){
      number_of_nodes_root(y);
      beer[b]=count1[y];
      total_vertices+=beer[b];
      b++;
    }
  if(total_vertices!=V){
    cout<<"NO  CORONA"<<endl;
    break ;
    //goto NEXT;
  }
  cout<<"CORONA "<<counter<<endl;
  mergesort(beer,0,(counter-1));
  for(int l=0; l<counter-1; l++){
    cout<<beer[l]<<" ";

  }
  cout<<beer[counter-1]<<endl;
  }
  else{
    cout<<"NO CORONA"<<endl;
  }

for(int i=0; i<N; i++){
  vector<int>::iterator it = graph[i].begin();
  vector<int>::iterator iti = graph[i].end();
  graph[i].erase(it, iti);
  vector<int>::iterator isa = cycles[i].begin();
  vector<int>::iterator isi = cycles[i].end();
  cycles[i].erase(isa, isi);
}
for(int j=0; j<N; j++){
  color[j]=0;
  par[j]=0;
  mark[j]=0;
  count1[j]=0;
}

//  NEXT : continue;
}
return 0;
}
