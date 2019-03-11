

import java.util.Scanner;

public class Lexer {
   public static final int  EOI = 0;
    public static final int  SEMI = 1;
    public static final int  PLUS = 2;
    public static final int  TIMES = 3;
    public static final int  LP = 4;
    public static final int  RP = 5;
    public static final int  NUM = 6;
    public static final int  INT = 7;
    public static final int  EQ = 8;
    public static final int  ID = 9;
    
   private int lookAhead = -1;  // 当前的标签 
   public String yytext = "";  // 当前分析子字符串
   public int yyleng = 0;
   public int yylineno = 0;
   private String input_buffer = "";
   private String current = ""; 
   
   private boolean isId(char c) {
   	if (Character.isAlphabetic(c) == true ||
    		    c == '_' || c == '$') {
    		return true;
    	}   
    	return false;
   }
   private boolean isNum(char c) {
	   	if (Character.isDigit(c) == true) {
	    		return true;
	    	}   
	    	return false;
	   }
   private boolean isKeyWord(String c) {
	   	if (c.equals("int")) {
	    		return true;
	    	}   
	    	return false;
	   }
   
   private int lex() { 
	   
    	while (true) { 
  		
    		while (current == "") {
    		    Scanner s = new Scanner(System.in);
    		    
    		    while (true) {
    		    	String line = s.nextLine();
    		    	//System.out.print(line);
    		    	if (line.equals("end")) {
    		    		break;
    		    	}
    		    	input_buffer += line;
    		    }
    		    s.close();  
    		    
    		    if (input_buffer.length() == 0) {
    		    	current = "";
    		    	return EOI;
    		    }
    		    
   		    current = input_buffer;
   		    ++yylineno;//当前的行号
   		    current.trim();
   		}//while (current != "")
   		    for (int i = 0; i < current.length(); ) {
   		    	yyleng = 0;
   		    	yytext = current.substring(0, 1);
   		    	switch (current.charAt(i)) {
   		    	case ';': current = current.substring(1); return SEMI;
   		    	case '+': current = current.substring(1); return PLUS;
   		    	case '*': current = current.substring(1);return TIMES;
   		    	case '(': current = current.substring(1);return LP;
   		    	case ')': current = current.substring(1);return RP;
   		    	case '=': current = current.substring(1);return EQ;
   		    	case '\n':
   		    	case '\t':
   		    	case ' ': current = current.substring(1); break;
   		    	default:
   		    		if(isNum(current.charAt(i)) == true ){
   		    			while (isNum(current.charAt(i))) {
   		    				i++;
   		    				yyleng++;
   		    				
   		    			}
   		    			yytext = current.substring(0, yyleng);
   	   		    		current = current.substring(yyleng); 
   	   		    			if (isKeyWord(yytext) )
   	   		    				return INT;
   	   		    		return NUM;
   		    		}
   		    		else if (isId(current.charAt(i)) == true)
   		    		{	
   		    			while (isId(current.charAt(i))) {
		    				i++;
		    				yyleng++;
		    			}
   		    			yytext = current.substring(0, yyleng);
   	   		    		current = current.substring(yyleng); 
   		    			if (isKeyWord(yytext) )
   		    				return INT;
   		    			else
   		    				return ID;
	    		
   		    		}
   		    		
   		    		else
   		    		{
   		    			System.out.println("Ignoring illegal input: " + current.charAt(i));
   		    		}

   		    		break;
   		    	} //switch (current.charAt(i))
   		    }//  for (int i = 0; i < current.length(); i++) 
   	}//while (true)	
   }//lex()
   
   public boolean match(int token) {
   	if (lookAhead == -1) {
   		lookAhead = lex();
   	}
   	return token == lookAhead;
   }
   
   public void advance() {
   	lookAhead = lex();
   }
   
   
   public void runLexer() {
   	while (!match(EOI)) {
   		System.out.println("Token: " + token() + " ,Symbol: " + yytext );
   		advance();
   	}
   }
   
   private String token() {
   	String token = "";
   	switch (lookAhead) {
   	case EOI:
   		token = "EOI";
   		break;
   	case PLUS:
   		token = "PLUS";
   		break;
   	case TIMES:
   		token = "TIMES";
   		break;
   	case NUM:
   		token = "NUM";
   		break;
   	case SEMI:
   		token = "SEMI";
   		break;
   	case LP:
   		token = "LP";
   		break;
   	case RP:
   		token = "RP";
   		break;
   	case EQ:
   		token = "EQ";
   		break;	
   	case INT:
   		token = "INT";
   		break;
   	case ID:
   		token = "ID";
   		break;	
   	}
   	return token;
   }

}
