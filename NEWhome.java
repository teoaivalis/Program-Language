import java.io.*;
import java.util.*;

public class StayHome {
  public static class coord{
      public int x;
      public int y;
      public int t;
      public coord(int a,int b,int c){
          x=a; y=b; t=c;
      }
      public int get_x(){
          return x;
      }
      public int get_y(){
          return y;
      }
      public int get_t(){
          return t;
      }
  }
  public static class tup{
        public int x;
        public int y;
    public tup(int a,int b){
        x=a; y=b;
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
  }

  public static void Printer(String Board [][],int N,int M){
      for(int i = 0; i < M; i++){
          for(int j = 0; j < N; j++){
              System.out.print((Board[i][j]));
          }
          System.out.println();
      }
      System.out.println();
  }

  public static String output(String world[][], int i, int j){
    String answer = "";
    while((world[i][j].compareTo("S")!=0) &&(world[i][j].compareTo("s")!=0)){
      String temp1;
      char temp2;
      String temp;
      temp1 = world[i][j].toUpperCase();
      temp2 = temp1.charAt(0);
      temp= String.valueOf(temp2);
      if (temp.compareTo("D")==0) {
        i--;
      }
      if (temp.compareTo("U")==0) {
        i++;
      }
      if (temp.compareTo("R")==0) {
        j--;
      }
      if (temp.compareTo("L")==0) {
        j++;
      }
      answer = temp + answer;
    }
    return answer;
  }


  public static boolean sotos_can_here(char a){
      if (a.compareTo(".")== 0 || a.compareTo("T")== 0)
          return true;
      return false;
  }

  public static boolean sotos_was_here(char a, char b, char t){
      if (((a.compareTo("L")== 0) || (a.compareTo("R")== 0) || (a.compareTo("U")== 0) || (a.compareTo("D")== 0)) && (b == t)) {
          return true;
        }
      return false;
  }

  public static boolean air_sotos(String a, char b, char t){
      if ((a.compareTo("LA")== 0 || a.compareTo("RA")== 0 || a.compareTo("UA")== 0 || a.compareTo("DA")== 0) && b == t)
          return true;
      return false;
  }

  public static boolean corona_easy_here(char a){
      if (a.compareTo(".")== 0 || a.compareTo("T")== 0)
          return true;
      return false;
  }

  public static boolean and_corona_here(char a){
      if (a.compareTo("S")== 0 || a.compareTo("L")== 0 || a.compareTo("R")== 0 || a.compareTo("U")== 0 || a.compareTo("D")== 0)
          return true;
      return false;
  }

  public static boolean another_corona_here(String a){
      if (a.compareTo("A")== 0 || a.compareTo("SA")== 0 || a.compareTo("LA")== 0 || a.compareTo("RA")== 0 || a.compareTo("UA")== 0 || a.compareTo("DA")== 0)
          return true;
      return false;
  }





  public static boolean corona_move (int y, int z, int i, int j, String realtable[][], Queue <tup> corona_friend){
    tup a1= new tup(y+i, z+j);
    if((realtable[y+i][z+j].compareTo(".") == 0) || (realtable[y+i][z+j].compareTo("T") == 0)){
       realtable[y+i][z+j] = "W";
       corona_friend.add(a1);
     }
     else if(realtable[y+i][z+j].compareTo("S") == 0){
       realtable[y+i][z+j] = "s";
       corona_friend.add(a1);
     }
     else if(realtable[y+i][z+j].compareTo("L") == 0){
       realtable[y+i][z+j] = "l";
       corona_friend.add(a1);
     }
     else if(realtable[y+i][z+j].compareTo("R") == 0){
       realtable[y+i][z+j] = "r";
       corona_friend.add(a1);
     }
     else if(realtable[y+i][z+j].compareTo("U") == 0){
       realtable[y+i][z+j] = "u";
       corona_friend.add(a1);
     }
     else if(realtable[y+i][z+j].compareTo("D") == 0){
       realtable[y+i][z+j] = "d";
       corona_friend.add(a1);
     }
     else if((realtable[y+i][z+j].compareTo("A") == 0) or (realtable[y+i][z+j].compareTo("DA") == 0) or (realtable[y+i][z+j].compareTo("RA") == 0) or (realtable[y+i][z+j].compareTo("UA") == 0) or (realtable[y+i][z+j].compareTo("LA") == 0)){
       String a;
       String b;
       a = world[y+i][z+j].toLowerCase();
       b = String.valueOf(a);
       realtable[y+i][z+j] = b;
       corona_friend.add(a1);
       return true;
     }
     return false;
  }



  public static boolean update_corona (int y, int z, char realtable[][], int rows, int a, Queue <tup> corona_friend){
    boolean A;
    A = False;
     if(y!=0){
       A = (corona_move(y, z, -1, 0, realtable, corona_friend) || A);
     }
     if(y!=rows-1){
       A = (corona_move(y, z, 1, 0, realtable, corona_friend) || A);
     }
     if(z!=0){
       A = (corona_move(y, z, 0, -1, realtable, corona_friend) || A);
     }
     if(z!=a-1){
       A = (corona_move(y, z, 0, 1, realtable, corona_friend) || A);
     }
     return A;
  }




  public static boolean update_aircorona (int myline, int mycolumn, char realtable[][], Queue <coord> air_friend){
    if(another_corona_here(realtable[myline][mycolumn])){
      realtable[myline][mycolumn] = realtable[myline][mycolumn].toLowerCase();
      coord temp3 =new coord((myline, mycolumn);
      air_friend.add(temp3);
      return True;
    }
  }








  public static void update_sotos (int myline, int mycolumn, int time, String realtable[][], int timetable[][], int line, int column, Queue <coord> sotos_friend){
    if(myline!=0){
      if(sotos_can_here(realtable[myline-1][mycolumn])){
        realtable[myline-1][mycolumn] = "U";
        timetable[myline-1][mycolumn] = time;
        coord temp1 =new coord((myline-1, mycolumn, time);
        sotos_friend.add(temp1);
      }
      else if(realtable[myline-1][mycolumn].compareTo("A")==0){
        realtable[myline-1][mycolumn] = "UA";
        timetable[myline-1][mycolumn] = time;
        coord temp2 =new coord(myline-1, mycolumn, time);
        sotos_friend.add(temp2);
      }
      else if(sotos_was_here(realtable[myline-1][mycolumn], timetable[myline-1][mycolumn], time)){
        if(realtable[myline-1][mycolumn].compareTo("U") > 0){
          realtable[myline-1][mycolumn] = String.valueOf(realtable[myline-1][mycolumn]);
        }
        else{
          realtable[myline-1][mycolumn] = String.valueOf("U");
        }//dn kserv an thelei autakia edv
        //realtable[myline-1][mycolumn]=lexiko(realtable[myline-1][mycolumn], "U");
      }
      else if(air_sotos(realtable[myline-1][mycolumn], timetable[myline-1][mycolumn], time)){
        //////////////////////////////////
        String r;
        r = realtable[myline-1][mycolumn];
        char taf;
        taf = r.charAt(0);
        char m;
        m = "U".charAt(0);
        String sym = "A";
        char character = sym.charAt(0);
        int m1 = m + character;
        int inum2 = Integer.parseInt(r);
        int inum3 = Integer.parseInt(String.valueOf(m1));
        int p;
        p = choose(inum2, inum3);
        realtable[myline-1][mycolumn] = String.valueOf(p);
        ////////////////////////////////
        //realtable[myline-1][mycolumn]=(lexiko(realtable[myline-1][mycolumn], "U")+"A");
      }
    }

    if(myline!=line - 1){
      if(sotos_can_here(realtable[myline+1][mycolumn])){
        realtable[myline+1][mycolumn] = "D";
        timetable[myline+1][mycolumn] = time;
        coord temp1 =new coord(myline+1, mycolumn, time);
        sotos_friend.add(temp1);
      }
      else if(realtable[myline+1][mycolumn].compareTo("A")==0){
        realtable[myline+1][mycolumn] = "DA";
        timetable[myline+1][mycolumn] = time;
        coord temp2 =new coord(myline+1, mycolumn, time);
        sotos_friend.add(temp2);
      }
      else if(sotos_was_here(realtable[myline+1][mycolumn], timetable[myline+1][mycolumn], time)){
        if(realtable[myline+1][mycolumn].compareTo("D") > 0){
          realtable[myline+1][mycolumn] = String.valueOf(realtable[myline+1][mycolumn]);
        }
        else{
          realtable[myline+1][mycolumn] = String.valueOf("D");
        }
        //realtable[myline+1][mycolumn]=lexiko(realtable[myline+1][mycolumn], "D");
      }
      else if(air_sotos(realtable[myline+1][mycolumn], timetable[myline+1][mycolumn], time)){
        ////////////////////////////////////////////
        String r;
        r = realtable[myline+1][mycolumn];
        char taf;
        taf = r.charAt(0);
        char m;
        m = "D".charAt(0);
        String sym = "A";
        char character = sym.charAt(0);
        int m1 = m + character;
        int inum2 = Integer.parseInt(r);
        int inum3 = Integer.parseInt(String.valueOf(m1));
        int p;
        p = choose(inum2, inum3);
        realtable[myline+1][mycolumn] = String.valueOf(p);
        //realtable[myline+1][mycolumn]=(lexiko(realtable[myline+1][mycolumn], "D")+"A");
      }
    }

    if(mycolumn!=0){
      if(sotos_can_here(realtable[myline][mycolumn-1])){
        realtable[myline][mycolumn-1] = "L";
        timetable[myline][mycolumn-1] = time;
        coord temp1 =new coord(myline, mycolumn-1, time);
        sotos_friend.add(temp1);
      }
      else if(realtable[myline][mycolumn-1].compareTo("A")==0){
        realtable[myline][mycolumn-1] = "LA";
        timetable[myline][mycolumn-1] = time;
        coord temp2 =new coord(myline, mycolumn-1, time);
        sotos_friend.add(temp2);
      }
      else if(sotos_was_here(realtable[myline][mycolumn-1], timetable[myline][mycolumn-1], time)){
        if(realtable[myline][mycolumn-1].compareTo("L") > 0){
          realtable[myline][mycolumn-1] = String.valueOf(realtable[myline][mycolumn-1]);
        }
        else{
          realtable[myline][mycolumn-1] = String.valueOf("L");
        }
        //realtable[myline][mycolumn-1]=lexiko(realtable[myline][mycolumn-1], "L");
      }
      else if(air_sotos(realtable[myline][mycolumn-1], timetable[myline][mycolumn-1], time)){
        /////////////////////////////////////////
        String r;
        r = realtable[myline][mycolumn-1];
        char taf;
        taf = r.charAt(0);
        char m;
        m = "L".charAt(0);
        String sym = "A";
        char character = sym.charAt(0);
        int m1 = m + character;
        int inum2 = Integer.parseInt(r);
        int inum3 = Integer.parseInt(String.valueOf(m1));
        int p;
        p = choose(inum2, inum3);
        realtable[myline][mycolumn-1] = String.valueOf(p);
        //realtable[myline][mycolumn-1]=(lexiko(realtable[myline][mycolumn-1], "L")+"A");
      }
    }


    if(mycolumn!=column - 1){
      if(sotos_can_here(realtable[myline][mycolumn+1])){
        realtable[myline][mycolumn+1] = "R";
        timetable[myline][mycolumn+1] = time;
        coord temp1 =new coord(myline, mycolumn+1, time);
        sotos_friend.add(temp1);
      }
      else if(realtable[myline][mycolumn+1].compareTo("A")==0){
        realtable[myline][mycolumn+1] = "RA";
        timetable[myline][mycolumn+1] = time;
        coord temp2 =new coord(myline, mycolumn+1, time);
        sotos_friend.add(temp2);
      }
      else if(sotos_was_here(realtable[myline][mycolumn+1], timetable[myline][mycolumn+1], time)){
        if(realtable[myline][mycolumn+1].compareTo("R") > 0){
          realtable[myline][mycolumn+1] = String.valueOf(realtable[myline][mycolumn+1]);
        }
        else{
          realtable[myline][mycolumn+1] = String.valueOf("R");
        }
        //realtable[myline][mycolumn+1]=lexiko(realtable[myline][mycolumn+1], "R");
      }
      else if(air_sotos(realtable[myline][mycolumn+1], timetable[myline][mycolumn+1], time)){
        /////////////////////////////////////////////////////
        String r;
        r = realtable[myline][mycolumn+1];
        char taf;
        taf = r.charAt(0);
        char m;
        m = "R".charAt(0);
        String sym = "A";
        char character = sym.charAt(0);
        int m1 = m + character;
        int inum2 = Integer.parseInt(r);
        int inum3 = Integer.parseInt(String.valueOf(m1));
        int p;
        p = choose(inum2, inum3);
        realtable[myline][mycolumn+1] = String.valueOf(p);
        //realtable[myline][mycolumn+1]=(lexiko(realtable[myline][mycolumn+1], "R")+"A");
      }
    }


  }  //EDV KLEINEI O sotos





  public static void main(String [] args) {
    try{
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String line;
        int N,M,t,now,Ty,Tz;
        Ty = 0;
        Tz = 0;
        String [][] realtable = new String[1000][1000];
        int [][] timetable = new int[1000][1000];
        N = 0;
        M = 0;
        t = 0;
        now = 0;
        Queue <coord> sotos = new LinkedList<>();
        Queue <coord> sotos_friend = new LinkedList<>();
        Queue <tup> corona = new LinkedList<>();
        Queue <tup> corona_frend = new LinkedList<>();
        Queue <tup> air = new LinkedList<>();
        Queue <tup> air_friend = new LinkedList<>();
        while ((line = in.readLine()) != null) {
            if (M == 0){
                N = line.length();
            }
            for (int i=0; i<N; i++){
                char temp = line.charAt(i);
                realtable[M][i] =  String.valueOf(temp);
                timetable[M][i] = 0;
                if(temp == 'T'){
                  Ty = M;
                  Tz = i;
                }
                else if(temp == 'S') {
                  coord temp1 = new coord(M,i,0);
                  sotos.add(temp1);
                }
                else if (temp =='W') {
                    tup temp2 = new tup(M,i);
                    corona.add(temp2);
                }
                else if (temp =='A') {
                  tup temp3 = new tup(M,i);
                  air.add(temp3);
                }
            }
            M++;
        }
        in.close ();
        int rows;
        int a;
        rows = M;
        a = N;
        t++;
        while(realtable[Ty][Tz].compareTo("T") == 0){
          // Printer(world,N,M);
          while(sotos.peek() != null){
            coord temp1= new coord(0,0,0);
            temp1 = sotos.poll();
            int a1,a2,a3;
            a1 = temp1.get_x();
            a2 = temp1.get_y();
            a3 = temp1.get_t();
            if ((realtable[a1][a2].compareTo("S") == 0) || (realtable[a1][a2].compareTo("U") == 0) || (realtable[a1][a2].compareTo("D") == 0) || (realtable[a1][a2].compareTo("R") == 0) || (realtable[a1][a2].compareTo("L") == 0) || (realtable[a1][a2].compareTo("DA") == 0) || (realtable[a1][a2].compareTo("RA") == 0) || (realtable[a1][a2].compareTo("UA") == 0) || (realtable[a1][a2].compareTo("LA") == 0)){
              update_sotos(a1, a2, t, realtable, timetable, M, N, sotos_friend);
            }
          }
          if ((realtable[Ty][Tz].compareTo("S") ==0) || (realtable[Ty][Tz].compareTo("U") ==0) || (realtable[Ty][Tz].compareTo("D") ==0) || (realtable[Ty][Tz].compareTo("R") ==0) || (realtable[Ty][Tz].compareTo("L") ==0)) {
            break;
          }
          if (sotos_friend.peek() == null) {
            System.out.println("IMPOSSIBLE");
            return;
          }
          sotos = new LinkedList<>(sotos_friend);
          sotos_friend.clear();
          if (t%2==1 && t>=now && now!=0){
            while (air.peek() != null){
              boolean B;
              if(t==now){
                tup temp3 = new tup(0,0);
                temp3 = air.poll();
                int c1,c2;
                c1 = temp3.get_x();
                c2 = temp3.get_y();
                B = update_aircorona(c1, c2, realtable, air_friend);
              }
              else{
                tup temp3= new tup(0,0);
                temp3 = airports.poll();
                int c1,c2;
                c1 = temp3.get_x();
                c2 = temp3.get_y();
                B = update_corona(c1, c2, realtable, rows, a, corona_friend);
              }
              if (world[Ty][Tz].compareTo("W")==0){
                System.out.println("IMPOSSIBLE");
              return;
              }
            }
            air = new LinkedList<>(air_friend);
            air_friend.clear();
          }
          if (t%2==0){
            while (corona.peek() !=null) {
              tup temp2= new tup(0,0);
              temp2 = corona.poll();
              int b1,b2;
              b1 = temp2.get_x();
              b2 = temp2.get_y();
              boolean B;
              B = update_corona(b1, b2, realtable, rows, a, corona_friend);
              if (B == true && now == 0){
                now= t+5;
              }
            }
            if (realtable[Ty][Tz].compareTo("W")==0){
              System.out.println("IMPOSSIBLE");
              return;
            }
            corona = new LinkedList<>(corona_friend);
            corona_friend.clear();
          }
            t++;
            }
            System.out.println(t);
            System.out.println(output(realtable, Ty, Tz));
            return;
        }
    catch(IOException e) {
             e.printStackTrace ();
         }
  }
}
