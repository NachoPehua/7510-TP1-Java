package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserKB {

    public KnowledgeBase create(String dbPath) throws InvalidDBLineException {
        String db = getDbString(dbPath);
        List<String> dbList =  Arrays.asList(db.split("\n"));
        validateDB(dbList);

        return getDatabase(dbList);
    }

    private String getDbString(String dbPath) {
        String dbString = null;
        try {
        	dbString = this.dbToString(dbPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return dbString.replace(".","").replace("\t","").replace(" ", "");
    }

    private String dbToString(String path) throws IOException {
        BufferedReader  buffReader= new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line = buffReader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = buffReader.readLine();
        }
        
        buffReader.close();
        return stringBuilder.toString();
    }



   private void validateDB(List<String> databaseStringList) throws InvalidDBLineException {
        for (String linea : databaseStringList) {
            if (!(isValidFact(linea) || isValidRule(linea))) {
            	 throw new InvalidDBLineException(""); 
           }
        }
    }
    private Boolean isValidFact(String linea) {
        String regex = "^[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\)";
        return linea.matches(regex);
    }

    private Boolean isValidRule(String linea) {
        String regex = "^[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\):-[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\)(,[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\))*";
        return linea.matches(regex);
    }
    
    private KnowledgeBase getDatabase(List<String> databaseStringList) {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
         for (String line : databaseStringList) {
             if (!line.contains(":-")) {
                 Fact fact = new Fact(line);
                 knowledgeBase.addFact(fact);
             }

             else {
                 Rule rule = new Rule(line);
                 knowledgeBase.addRule(rule);
             }
         }

         return knowledgeBase;
    }

}
