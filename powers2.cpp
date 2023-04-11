#include <stdio.h>
#include <math.h>
#include <vector>
#include <iostream>
#include <algorithm>
#include <fstream>
#include <string>
#include <stack>
using namespace std;

int result(int res[32], int K, int j, int i){
  //k arithos prosthetevn, j arithmos tvn assvn sthn dyadikh anaparastash(ara posous exv xrhsimopoihsei)
  //i einai h thesh sto binary sthn opoia briskomai
  //res einai o pinakas me ta apotelesmata
  if(K==j){
    return j;
  }
  int exp = (int) pow(2,i); //ypologizv thn dynamh tou 2 sthn opoia briskomai
  if((K-j)>=exp){
    res[0]=res[0]+exp;  //ta kanv ola assous
    res[i]=res[i]-1;
    j=j+(exp-1);            //ta xrhsimopoihmena einai j kai osous assous ebala
    return j;
  }
  else{   //otan spame se dentro
     res[i]=res[i]-1;         // an spasv to 64 se 32 kai 32 bgazv ton asso pou                                //eixa sto64
     res[i-1]=res[i-1]+2;     // kai bazv 2 neous assous sto 32
     j=j+1;                         // kai auksanv to j kata 1 giati anti gia to 1
     /*if(j==K){
       return j;
     } */                           //krathmeno asso tou 64 exv 2 tou 32
     j=result(res,K,j,i-1);
     j=result(res,K,j,i-1);
     return j;
  }
 }




 int main(int argc, char **argv){

 int T ;
 ifstream infile;
 infile.open(argv[1]);
 infile >> T ;
 int N, K ;
 for(int t=0; t<T; t++){
   infile >> N >> K;
   int binary[32];     //edv bazv th diadikh anaparastash kathe arithmou
   int res[32];        //pinakas gia ta telika apotelesmata
   for(int l=0; l<32; l++){
     binary[l]=0;            //arxikopoiv ton binary sto mhden
     res[l]=0;                 //arxikopoiv ton res sto mhden
   }
   int j=0;              //gia to posous assous kratav==theseis pou exv asso ston binary
   int n=N;       //to kanv arxika isv me to dvthen N kai kathe fora to diairv me to 2


   int k=0;        // metrhths gia na broume mexri poio shmeio exei ftasei o teleytaios assos gia thn anaparastash
 //briskv dyadikh anaparastash kai gemizv ton binary mexri na ftasv sto mhden
  while(n>0){
    binary[k]=n%2;
   // cout<<binary[k]<<" ";
    if(binary[k]==1){
      j++;        //krata ena k gia kathe asso sthn dyadikh anaparastash
    }
    n=n/2;  //pav sthn prohgoumenh dynamh tou 2
    k++;
  }
  int last_one=k-1;   //edv brisketai o teleytaios assos sthn diadikh anaparastash
   //kai mexri ekei tha prepei na exei h apanthsh giati meta tha einia ola mhden

   for(int i=0; i<32; i++){
   res[i]=binary[i];
  }
   if (N == 0 || K == 0) {        //an mas dvsei to mhden na to grapsoume me mhden prostheteous
     cout<<"[]"<<endl;
     //exit(0);
   }
   else if (K < j) {                //an oi assoi einai perissoteroi apo to k pou mas dinei kanei exit
       cout<<"[]"<<endl;       //giati den ginetai na grafei kapvs
     //exit(0);
   }
   else if (K > N) {               //an to k einai megalytero apo ton arithmo den mporei na grafei
       cout<<"[]"<<endl;         //oute mono measoous opote kanei exit
     //exit(0);
   }
   else if(K==j) {
     cout<<"[";
     for(int it=0; it<last_one; it++){
      cout<<binary[it]<<",";
    }
    cout<<binary[last_one]<<"]"<<endl;
    //exit(0);
   }
   else{
     for(int u=1; u<=last_one; u++){
       if(binary[u]==0){
         binary[u]=binary[u]+0;
       }
       else if((binary[u]==1)&&(j<K)){  //an exv asso sthn dyadikh anaparastash
         j=result(res,K,j,u);
       }
     }
     int h=31;
     while(res[h]!=1){
       h=h-1;
     }

      cout<<"[";
      for(int r=0; r<h; r++){
       cout<<res[r]<<",";
      }
      cout<<res[h]<<"]"<<endl;
   }



   //tvra tha kanv thn ekthpvsh tou binary meta thn epeksergasia me thn synarthsh


 }
 return 0;
 }
