Usecase:
1. One thing can be interpreted in multiple ways.

Process:
1. Expression: obj that needs to be interpreted, has method interpret(Context context), which takes context class and interpret the expression according to it.
	1. Terminal expression: like `a`.
	2. Non-terminal expression: like `a+b`
2. Context: which provides context to interpret the expression (both types)


