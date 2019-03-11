package compiler5;
public class Compiler {

	public static void main(String[] args) {
		Lexer lexer = new Lexer();
		Parse parse = new Parse(lexer);
		parse.statements();
	}
}
