cycle(Node,Cycle) :-
    cycle(Node,[],Cycle).

cycle(Curr,Visited,Cycle) :-
    member(Curr,Visited),
    !,
    reverse([Curr|Visited],Cycle).
cycle(Curr,Visited,Cycle) :-
    edge(Curr,Next),
    cycle(Next,[Curr|Visited],Cycle).
