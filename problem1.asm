@5
D=A
@J
M=D
@i
M=1

(LOOP)
@i
D=M

@5
D=D-A

@END
D;JGE

@J
M=M-1

@i
M=M+1

@LOOP
0;JMP

(END)
@0
0;JMP