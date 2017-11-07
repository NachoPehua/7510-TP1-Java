package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FactTest {

    @Test
    public void testCreateFact() {
    	
        Fact fact = new Fact("campeon(river)");
        
        Assert.assertFalse(fact.getName().equals("subcampeon"));
        Assert.assertTrue(fact.getName().equals("campeon"));
        
        Assert.assertFalse(fact.getArgs().get(0).equals("boca"));
        Assert.assertTrue(fact.getArgs().get(0).equals("river"));

    }

    @Test
    public void testCreateFactTwoArgs() {
        Fact fact = new Fact("tecnico(gallardo,river)");
        
        Assert.assertTrue(fact.getName().equals("tecnico"));
        
        Assert.assertFalse(fact.getArgs().get(0).equals("river"));
        Assert.assertTrue(fact.getArgs().get(0).equals("gallardo"));
        
        Assert.assertFalse(fact.getArgs().get(1).equals("boca"));
        Assert.assertTrue(fact.getArgs().get(1).equals("river"));

    }

    @Test
    public void testCreateFact2() {
    	List<String> args = new ArrayList<String>();
    	args.add("river");
        Fact fact = new Fact("campeon", args);
        
        Assert.assertFalse(fact.getName().equals("subcampeon"));
        Assert.assertTrue(fact.getName().equals("campeon"));
        
        Assert.assertFalse(fact.getArgs().get(0).equals("boca"));
        Assert.assertTrue(fact.getArgs().get(0).equals("river"));

    }
    
    @Test
    public void testEqualsFacts() {
    	List<String> args = new ArrayList<String>();
    	args.add("river");
        Fact fact = new Fact("campeon", args);
        Fact fact2 = new Fact("campeon", args);
        Fact fact3 = new Fact("subcampeon", args);

        Assert.assertTrue(fact.getName().equals("campeon")==fact2.getName().equals("campeon"));

        Assert.assertTrue(fact.equals(fact2));
        Assert.assertFalse(fact.equals(fact3));
        Assert.assertFalse(fact3.equals(fact2));
    }

}
