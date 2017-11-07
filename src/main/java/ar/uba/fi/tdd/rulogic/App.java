package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.InvalidDBLineException;
import ar.uba.fi.tdd.rulogic.model.InvalidQException;
import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import ar.uba.fi.tdd.rulogic.model.ParserKB;

import java.io.File;
import java.util.Scanner;

public class App
{
	public static void main(String[] args) {
	    App app = new App();

	    ParserKB parserkb = new ParserKB();
        KnowledgeBase knowledgeBase = null;
        try {
        	 knowledgeBase = parserkb.create(new File("").getAbsolutePath().concat("\\src\\main\\resources\\rules.db"));            
        } catch (InvalidDBLineException e) {
            System.out.println("Invalid database");
            System.exit(1);
        }

        app.answer(knowledgeBase);
    }

    private void answer(KnowledgeBase knowledgeBase) {
	    String EXIT_WORD = "q";
        System.out.println("Aprete q para salir");
        System.out.println("Ingrese sus consultas");
        Scanner scan = new Scanner(System.in);
        String query;
	    while (true) {
	        query = scan.nextLine();
	        if (EXIT_WORD.equals(query)) {
	        	scan.close();
	        	break;
	        }
	        try {System.out.println((knowledgeBase.answer(query) ? "Si" : "No"));
	        }
            catch (InvalidQException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
