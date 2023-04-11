(*https://stackoverflow.com/questions/21970248/conversion-from-decimal-to-binary-in-sml/21971772#21971772*)
(*synarthsh gia ypologismo ths diadikhs anaparastashs*)
fun binary n =
    let
        fun binary' 0   acc = rev acc
            | binary' num acc = binary' (num div 2) (num mod 2 :: acc)
    in
    binary' n []
end




(*synarthsh poy ananevnei to periexomeno tou pinaka*)
fun change(list) = case list of
                     nil=>[]
                   | first::nil=>[first]
                   | first::[1]=>[first+2]
                   | first::[0]=>[first]
                   | first::[last]=>(first+2) :: [(last-1)]
                   | first::second::others=>case second>0 of
                                              true => first+2 :: second-1 :: others
                                           |  false => first :: change(second::others)



 (*synarthsh pou briskei to plhthos tvn assvn se pinaka*)

 fun number_one (list)=
      let
        val ones=0
      in
        if list = nil then ones
        else ones + hd(list) + number_one(tl(list))
      end


(*synarthsh gia elegxous gia to ti tha ektypvsei*)
(*to k einai o arithmos tvn prosthetevn apo thn ekfvnhsh*)
(*to list exei mesa th dyadikh anaparastash pou tha exoyme brei*)
fun checking (list, k) =
    let
       val here_ones = number_one(list)
    in
       if here_ones > k then []
       else if here_ones = k then list
       else  checking( change(list) , k )       (*prepei na kanei epeksergasia mesv ths change kai meta kalv pali thn checking*)
    end                                         (*h changed list tha exei perissoterous assous mallon gia ayto epanalambanoyme*)
                                                (*mexri to here_ones==kvste na exv osous assous prepeikai ekei termatizei h synarthsh*)




(*synarthsh gia na kanei th diadikasia gia ola ta zeygh*)
(*an exv polla zeygh kanv gia to prvto kai meta anadromika kanv gia ta epomena*)

fun result (t,list) = case list of
                      [] => [[]]
                    | n :: [] => [[]]
                    | n :: k :: [] => [ checking( binary(n) , k) ]
                    | n :: k :: more_things => checking( binary(n) , k) :: result( t-1 , more_things )




(*https://stackoverflow.com/questions/36799572/how-to-print-a-list*)
(*synarthsh gia ektypvsh ths listas*)

fun printthelist (list) = case list of
                   nil=>print ""
                  |first :: [] => (print("[" ^ String.concatWith "," (map Int.toString first) ^ "]\n");
                                                                                  printthelist([]))
                  |first :: others => (print("[" ^ String.concatWith "," (map Int.toString first) ^ "]\n");
                                                                                  printthelist(others))


(*gia to diabasma apo arxeio apo novice.softlab.ntua.gr*)
fun take_from_file file =
    let
	(* A function to read an integer from specified input. *)
        fun readInt input =
	    Option.valOf (TextIO.scanStream (Int.scan StringCvt.DEC) input)

	(* Open input file. *)
    	val inStream = TextIO.openIn file

        (* Read an integer and consume newline. *)
	val n = readInt inStream * 2
	val _ = TextIO.inputLine inStream

        (* A function to read N integers from the open file. *)
	fun readInts 0 acc = rev acc (* Replace with 'rev acc' for proper order. *)
	  | readInts i acc = readInts (i - 1) (readInt inStream :: acc)
    in
   	(n, readInts n [])
    end






fun powers2 file = printthelist(result(take_from_file (file)))
