package compiler5;

public class Parse {
	private Lexer lexer;
	private boolean isLegalStatement = true;
	public Parse(Lexer lexer)
	{
		this.lexer = lexer;
	}
	
	public void statements()
	{
		while(lexer.match(lexer.EOI))
		{	expression();
			if (lexer.match(lexer.SEMI))
			{
				lexer.advance();
			}
			else
			{
				isLegalStatement = false;
				System.out.println("line: " + lexer.yylineno + " Missing semicolon");
			}
		}
		if(isLegalStatement)
		{
			System.out.println("The statement is legal");
		}
	}
	
	public void expression()
	{
		term();
		while(lexer.match(lexer.PLUS))
		{
			lexer.advance();
			term();
		}
		if (lexer.match(Lexer.UNKNOWN_SYMBOL)) {
    		isLegalStatement = false;
    		System.out.println("unknow symbol: " + lexer.yytext);
    		return;
    	}

	}
	
	public void term()
	{
		factor();
		while(lexer.match(lexer.TIMES))
		{
			lexer.advance();
			factor();
		}
	}
	
	
	public void factor()
	{
		
		if (lexer.match(lexer.NUM_OR_ID))
		{
			lexer.advance();   
		}
		else if(lexer.match(lexer.LP))
		{
			lexer.advance();
			expression();
			if(lexer.match(lexer.RP))
			{
				lexer.advance();
			}
			else {
				isLegalStatement = false;
    			System.out.println("line: " + lexer.yylineno + " Missing )");
    			return;
			}
				
		}
		
	}
}








