%% Dfs starting from a root
dfs(Root) :-
    dfs([Root], []).
%% dfs(ToVisit, Visited)
%% Done, all visited
dfs([],_).
%% Skip elements that are already visited
dfs([H|T],Visited) :-
    member(H,Visited),
    dfs(T,Visited).
%% Add all neigbors of the head to the toVisit
dfs([H|T],Visited) :-
    not(member(H,Visited)),
    findall(Nb,move(H,Nb),Nbs),
    append(Nbs,T, ToVisit),
    dfs(ToVisit,[H|Visited]).


https://stackoverflow.com/questions/37888421/dfs-in-prolog-visiting-a-node-once
