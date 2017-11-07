package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.util.List;

public class ParserKBTest {

    @Test
    public void testCreateInvalidDB() {
    	ParserKB parserkb = new ParserKB();
        try {
        	parserkb.create(new File("").getAbsolutePath().concat("\\src\\test\\resources\\invalid.db"));
            Assert.assertTrue(false);
        } catch (InvalidDBLineException e) {
            Assert.assertTrue(e.getMessage().equals(""));
        }
    }
    
    @Test
    public void testCreateDB() throws InvalidDBLineException {
    	ParserKB parserKb = new ParserKB();
        KnowledgeBase kBase = parserKb.create(new File("").getAbsolutePath().concat("\\src\\main\\resources\\rules.db"));
        
        List<Fact> facts = kBase.getFacts();
        List<Rule> rules = kBase.getRules();

        Assert.assertTrue(facts.get(0).getName().equals("varon"));
        Assert.assertTrue(facts.get(0).getArgs().get(0).equals("juan"));
        Assert.assertTrue(facts.get(1).getName().equals("varon"));
        Assert.assertTrue(facts.get(1).getArgs().get(0).equals("pepe"));

        Assert.assertTrue(rules.get(0).getNombre().equals("hijo"));
        Assert.assertTrue(rules.get(0).getArgs() == 2);
        Assert.assertTrue(rules.get(0).getFacts().get(0).getName().equals("varon"));
        Assert.assertTrue(rules.get(0).getFacts().get(0).getArgs().get(0).equals("0"));
        Assert.assertTrue(rules.get(0).getFacts().get(1).getName().equals("padre"));
        Assert.assertTrue(rules.get(0).getFacts().get(1).getArgs().get(0).equals("1"));
        Assert.assertTrue(rules.get(0).getFacts().get(1).getArgs().get(1).equals("0"));
    }

}
