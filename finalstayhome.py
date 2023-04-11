from collections import deque
import sys

def Answer(realtable,myline,mycolumn):
    finalanswer=""
    while realtable[myline,mycolumn] != 'S' and realtable[myline,mycolumn] != 's':
        #perimenv na ftasei sthn arxh
        temp = realtable[(myline,mycolumn)][0].upper()
        if temp == 'D':
            myline = myline - 1
        elif temp == 'U':
            myline = myline + 1
        elif temp == 'R':
            mycolumn = mycolumn - 1
        elif temp == 'L':
            mycolumn = mycolumn + 1
        finalanswer = temp + finalanswer;
    return finalanswer;



def sotos_can_here(a):
    if((a == ".") or (a == "T")):
        return True
    return False



def sotos_was_here(a,b,t):  #exei paei o sotos alla o oxi o corona
    if (((a == "L") or (a == "R") or (a == "U") or (a == "D")) and (b == t)):
        return True
    return False


def air_sotos(a,b,t):
    if (((a == "LA") or (a == "RA") or (a == "UA") or (a == "DA")) and (b == t)):
        return True
    return False



def corona_easy_here(a):
    if((a == ".") or (a == "T")):
        return True
    return False



def and_corona_here(a):
    if((a == "S") or (a == "L") or (a == "R") or(a == "U") or (a == "D")):
        return True
    return False



def another_corona_here(a):
    if((a == "A") or (a == "SA") or (a == "LA") or (a == "RA") or(a == "UA") or (a == "DA")):
        return True
    return False



def lexiko(a,b):  #briskei lexikografika mikrotero
  if(a>b):
    return a;
  return b;



def update_sotos(myline, mycolumn, time, realtable, timetable, line, column, sotos_friend):
    if(myline!=0):
        if(sotos_can_here(realtable[(myline-1,mycolumn)])):
            realtable.update({(myline-1, mycolumn):"U"})
            timetable.update({(myline-1, mycolumn):time})
            sotos_friend.append((myline-1, mycolumn, time))
        elif(realtable[(myline-1,mycolumn)] == "A"):
            realtable.update({(myline-1, mycolumn):"UA"})
            timetable.update({(myline-1, mycolumn):time})
            sotos_friend.append((myline-1, mycolumn, time))
        elif(sotos_was_here(realtable[(myline-1, mycolumn)], timetable[(myline-1,mycolumn)], time)):
            realtable.update({(myline-1, mycolumn):lexiko(realtable[(myline-1, mycolumn)], "U")})
        elif(air_sotos(realtable[(myline-1, mycolumn)], timetable[(myline-1,mycolumn)], time)):
            realtable.update({(myline-1, mycolumn):(lexiko(realtable[(myline-1, mycolumn)], "U")+"A")})

    if(myline!=line - 1):
        if(sotos_can_here(realtable[(myline+1,mycolumn)])):
            realtable.update({(myline+1, mycolumn):"D"})
            timetable.update({(myline+1, mycolumn):time})
            sotos_friend.append((myline+1, mycolumn, time))
        elif(realtable[(myline+1,mycolumn)] == "A"):
            realtable.update({(myline+1, mycolumn):"DA"})
            timetable.update({(myline+1, mycolumn):time})
            sotos_friend.append((myline+1, mycolumn, time))
        elif(sotos_was_here(realtable[(myline+1, mycolumn)], timetable[(myline+1,mycolumn)], time)):
            realtable.update({(myline+1, mycolumn):lexiko(realtable[(myline+1, mycolumn)], "D")})
        elif(air_sotos(realtable[(myline+1, mycolumn)], timetable[(myline+1,mycolumn)], time)):
            realtable.update({(myline+1, mycolumn):(lexiko(realtable[(myline+1, mycolumn)], "D")+"A")})


    if(mycolumn!=0):
        if(sotos_can_here(realtable[(myline,mycolumn-1)])):
            realtable.update({(myline, mycolumn-1):"L"})
            timetable.update({(myline, mycolumn-1):time})
            sotos_friend.append((myline, mycolumn-1, time))
        elif(realtable[(myline,mycolumn-1)] == "A"):
            realtable.update({(myline, mycolumn-1):"LA"})
            timetable.update({(myline, mycolumn-1):time})
            sotos_friend.append((myline, mycolumn-1, time))
        elif(sotos_was_here(realtable[(myline, mycolumn-1)], timetable[(myline,mycolumn-1)], time)):
            realtable.update({(myline, mycolumn-1):lexiko(realtable[(myline, mycolumn-1)], "L")})
        elif(air_sotos(realtable[(myline, mycolumn-1)], timetable[(myline,mycolumn-1)], time)):
            realtable.update({(myline, mycolumn-1):(lexiko(realtable[(myline, mycolumn-1)], "L")+"A")})


    if(mycolumn!=column - 1):
        if(sotos_can_here(realtable[(myline,mycolumn+1)])):
            realtable.update({(myline, mycolumn+1):"R"})
            timetable.update({(myline, mycolumn+1):time})
            sotos_friend.append((myline, mycolumn+1, time))
        elif(realtable[(myline,mycolumn+1)] == "A"):
            realtable.update({(myline, mycolumn+1):"RA"})
            timetable.update({(myline, mycolumn+1):time})
            sotos_friend.append((myline, mycolumn+1, time))
        elif(sotos_was_here(realtable[(myline, mycolumn+1)], timetable[(myline,mycolumn+1)], time)):
            realtable.update({(myline, mycolumn+1):lexiko(realtable[(myline, mycolumn+1)], "R")})
        elif(air_sotos(realtable[(myline, mycolumn+1)], timetable[(myline,mycolumn+1)], time)):
            realtable.update({(myline, mycolumn+1):(lexiko(realtable[(myline, mycolumn+1)], "R")+"A")})








def update_corona(myline, mycolumn, realtable, line, column, corona_friend):
    check = False #gia elegxo aera
    if(myline!=0):
        if(corona_easy_here(realtable[(myline-1,mycolumn)])):
            realtable.update({(myline-1, mycolumn):"W"})
            corona_friend.append((myline-1, mycolumn))
            check = (False or check)
        elif(and_corona_here(realtable[(myline-1,mycolumn)])):
            realtable.update({(myline-1, mycolumn): realtable[(myline-1,mycolumn)].lower()})
            corona_friend.append((myline-1, mycolumn))
            check = (False or check)
        elif(another_corona_here(realtable[(myline-1,mycolumn)])):
            realtable.update({(myline-1, mycolumn):realtable[(myline-1,mycolumn)].lower()})
            corona_friend.append((myline-1, mycolumn))
            check = (True or check)


    if(myline!=line - 1):
        if(corona_easy_here(realtable[(myline+1,mycolumn)])):
            realtable.update({(myline+1, mycolumn):"W"})
            corona_friend.append((myline+1, mycolumn))
            check = (False or check)
        elif(and_corona_here(realtable[(myline+1,mycolumn)])):
            realtable.update({(myline+1, mycolumn): realtable[(myline+1,mycolumn)].lower()})
            corona_friend.append((myline+1, mycolumn))
            check = (False or check)
        elif(another_corona_here(realtable[(myline+1,mycolumn)])):
            realtable.update({(myline+1, mycolumn):realtable[(myline+1,mycolumn)].lower()})
            corona_friend.append((myline+1, mycolumn))
            check = (True or check)



    if(mycolumn!=0):
        if(corona_easy_here(realtable[(myline,mycolumn-1)])):
            realtable.update({(myline, mycolumn-1):"W"})
            corona_friend.append((myline, mycolumn-1))
            check = (False or check)
        elif(and_corona_here(realtable[(myline,mycolumn-1)])):
            realtable.update({(myline, mycolumn-1): realtable[(myline,mycolumn-1)].lower()})
            corona_friend.append((myline, mycolumn-1))
            check = (False or check)
        elif(another_corona_here(realtable[(myline,mycolumn-1)])):
            realtable.update({(myline, mycolumn-1):realtable[(myline,mycolumn-1)].lower()})
            corona_friend.append((myline, mycolumn-1))
            check = (True or check)


    if(mycolumn!=column - 1):
        if(corona_easy_here(realtable[(myline,mycolumn+1)])):
            realtable.update({(myline, mycolumn+1):"W"})
            corona_friend.append((myline, mycolumn+1))
            check = (False or check)
        elif(and_corona_here(realtable[(myline,mycolumn+1)])):
            realtable.update({(myline, mycolumn+1): realtable[(myline,mycolumn+1)].lower()})
            corona_friend.append((myline, mycolumn+1))
            check = (False or check)
        elif(another_corona_here(realtable[(myline,mycolumn+1)])):
            realtable.update({(myline, mycolumn+1):realtable[(myline,mycolumn+1)].lower()})
            corona_friend.append((myline, mycolumn+1))
            check = (True or check)

    return check






def update_aircorona(myline, mycolumn, realtable, air_friend):
        if(another_corona_here(realtable[(myline,mycolumn)])):
                realtable.update({(myline, mycolumn):realtable[(myline,mycolumn)].lower()})
                air_friend.append((myline, mycolumn))
                return True



def main():
  realtable = {}    #orizv to dictionary gia ta dedomena mou
  timetable = {}    #dictionary gia ton xrono
  file = open(str(sys.argv[1]),"r")
  ##file = open("stayhome.in2","r")
  sotos = deque()   #oura gia tis theseis tou sotou
  corona = deque()  #oura gia tis theseis tou iou
  sotos_friend = deque()
  corona_friend = deque()
  air = deque()     #oura gia aerodromia
  air_friend = deque()
  final_line = 0    #theseis spitiou
  final_column = 0
  line = 0          #grammes
  time = 0          #xronometro
  mytime = 0        #metrhths-xronometro gia aera
  for x in file:
    column = 0
    a = len(x) - 1
    for column in range(a):
      timetable[(line,column)] = 0
      realtable[(line,column)] = x[column]
      if (x[column] == "A"):
        air.append((line,column)) #den kratav xrono gia aerodromia
      if (x[column] == "S"):
        sotos.append((line,column,time))
      if (x[column] == "W"):
        corona.append((line,column)) #den kratav xrono gia corona
      if (x[column] == "T"):      #kratav th thesh tou spitiou
        final_line = line
        final_column = column
    line = line+1
  rows = line #kratav ton arithmo tvn sthlvn
  #edv teleivnei to diabasma kai to perasma ston pinaka
  time = time + 1

  while(realtable[(final_line,final_column)] == "T"): #oso den exei ftasei o ios sto spiti

    #elegxo gia kinhsh sotou
    while(sotos): #oso h lista sotos den einai adia
      (k_line,k_column,k_time)=sotos.pop() #bgazv th thesh tou sotou
      if ((realtable[(k_line,k_column)] == "S") or (realtable[(k_line,k_column)] == "U") or (realtable[(k_line,k_column)] == "D") or (realtable[(k_line,k_column)] == "R") or (realtable[(k_line,k_column)] == "L") or (realtable[(k_line,k_column)] == "DA") or (realtable[(k_line,k_column)] == "UA") or(realtable[(k_line,k_column)] == "LA") or (realtable[(k_line,k_column)] == "RA")):
        update_sotos(k_line, k_column, time, realtable, timetable, rows, a, sotos_friend)
      #an einai thesj pou mporei na paei o sotos thn enhmervnv


    if((realtable[((final_line,final_column))] == "S") or (realtable[((final_line,final_column))] == "U") or (realtable[((final_line,final_column))] == "D") or (realtable[((final_line,final_column))] == "L") or (realtable[((final_line,final_column))] == "R")):
      break # eftase asfalhs spiti opote telos opote thelei kai synarthsh na typvnei to apotelesma edv

    if (not sotos_friend): #an den mporei na epektathei allo o sotos kai den eftase spiti
      print("IMPOSSIBLE")
      return

    sotos = sotos_friend    #pernav ta stoixeia ston svto gia na treksei sthn update
    sotos_friend = deque()  #epanarxikopoiv thn ouraa

    #elegxos gia epektash apo aera

    if(time%2 == 1 and time>=mytime and mytime!=0):
      while(air):
        if(time == mytime):
          (a_line,a_column) = air.pop()
          place = update_aircorona(a_line,a_column,realtable,air_friend)
          #place = corona_move(a_line,a_column,0,0,realtable,air_friend)
        else:
          (a_line,a_column) = air.pop()
          place = update_corona(a_line, a_column, realtable, rows, a, air_friend)
        if(realtable[(final_line,final_column)] == "W"):
          print("IMPOSSIBLE")
          return
      air = air_friend
      air_friend = deque()

#elegxo gia kinhsh corona

    if(time%2 == 0):   #an einai xronos pou proxvraei o corona
      while(corona):   #oso exv shmeia epektashs tou corona
        (m_line, m_column) = corona.pop()
        place = update_corona(m_line, m_column, realtable, rows, a, corona_friend)
        if(place == True and mytime == 0):
          mytime = time + 5

    #afou epektatheis opou mpporeis kai kaneis kai elegxo gia aera
      if(realtable[((final_line,final_column))] == "W"):
        print("IMPOSSIBLE")
        return            #tote eftase o corona sto spiti
      corona = corona_friend
      corona_friend = deque()
    time = time + 1
  print(time)
  print(Answer(realtable,final_line,final_column))
  return
if __name__== "__main__":
    main()
