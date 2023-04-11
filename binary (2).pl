/*https://stackoverflow.com/questions/20437673/prolog-putting-elements-in-a-list-for-a-decimal-to-binary-conversion*/
dec2bin(0,[0]).
dec2bin(1,[1]).
dec2bin(N,L):-
    N > 1,
    X is N mod 2,
    Y is N // 2,
    dec2bin(Y,L1),
    L = [X|L1].


/*https://stackoverflow.com/questions/9875760/sum-of-elements-in-list-in-prolog/9876309*/
list_sum([],0).
list_sum([Head|Tail], TotalSum):-
list_sum(Tail, Sum1),
TotalSum is Head+Sum1.

count_the_ones(N,Sum):-
dec2bin(N,L),
list_sum(L,Sum).

change([X1],[X2]):-
  X2 is X1.

change([X1,Z1],[X2]):-
  Z1 = 0,
  X2 is X1.

change([X1,Z1],[X2]):-
  Z1 = 1,
  X2 is X1 + 2.


change([X1,Z1],[X2,Z2]):-
  Z1 > 0,
  X2 is X1 + 2,
  Z2 is Z1 - 1.

change([X1,Z1|Y1],[X2,Z2|Y2]):-
 Z1 > 0,
 X2 is X1 + 2,
 Z2 is Z1 - 1,
 Y2 = Y1.

change([X1,Z1|Y1],[X2,Z2|Y2]):-
 Z1 = 0,
 X2 is X1,
 change([Z1|Y1],[Z2|Y2]).

change([X1,Z1|Y1],[X2,Z2|Y2]):-
 Z1 < 0,
 X2 is X1,
 change([Z1|Y1],[Z2|Y2]).






checking(Number_One,K,_,New_List):-
 Number_One > K ,
 New_List = [].


checking(Number_One,K,Old_List,New_List):-
 Number_One = K ,
 New_List = Old_List.


checking(Number_One,K,Old_List,Newer_List):-
 Number_One < K,
 change(Old_List,New_List),
 list_sum(New_List,Number_Ones),
 checking(Number_Ones,K,New_List,Newer_List).


real_checking(N,K,New_List):-
 count_the_ones(N,Ones),
 dec2bin(N,L),
 checking(Ones,K,L,New_List).

 read_and_do(0, _Stream, Answers, Final_Answers):-
   Final_Answers = Answers,!.

 read_and_do(M, Steam, CurrentAnswers, Answers):-
     read_line(Steam, [N, K]),
     real_checking(N, K, ResultR),
     append(CurrentAnswers, [ResultR], NewCurrentAnswers),
     NewM is M - 1,
     read_and_do(NewM, Steam, NewCurrentAnswers, Answers).


 powers2(File, Answers) :-
     open(File, read, Stream),
     read_line(Stream, M),
     [H | _] = M,
     read_and_do(H, Stream, [], Answers).



 read_line(Stream, L):-
     not(at_end_of_stream(Stream)),
     read_line_to_codes(Stream, Line),
     atom_codes(Atom, Line),
     atomic_list_concat(Atoms,' ', Atom),
     maplist(atom_number, Atoms, L).
