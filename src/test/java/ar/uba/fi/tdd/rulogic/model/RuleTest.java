package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RuleTest {

    @Test
    public void testCreateRuleOneFact() {
        Rule rule = new Rule("rule(X):-fact(X)");
        List<Fact> facts = rule.getFacts();

        Assert.assertTrue(rule.getNombre().equals("rule"));
        Assert.assertTrue(rule.getArgs() == 1);
        Assert.assertTrue(facts.get(0).getName().equals("fact"));
        Assert.assertTrue(facts.get(0).getArgs().get(0).equals("0"));
    }

    @Test
    public void testCreateRuleThreeFacts() {
        Rule rule = new Rule("tio(X,Y,Z):-varon(X),hermano(X,Z),padre(Z,Y)");

        List<Fact> facts = rule.getFacts();

        Assert.assertTrue(rule.getNombre().equals("tio"));
        Assert.assertTrue(facts.get(0).getName().equals("varon"));
        Assert.assertTrue(facts.get(0).getArgs().get(0).equals("0"));
        Assert.assertTrue(facts.get(1).getName().equals("hermano"));
        Assert.assertTrue(facts.get(1).getArgs().get(0).equals("0"));
        Assert.assertTrue(facts.get(1).getArgs().get(1).equals("2"));
        
        Assert.assertFalse(rule.getArgs() == 2);
        Assert.assertTrue(rule.getArgs() == 3);
    }

    @Test
    public void testRuleFacts() {
        Rule rule = new Rule("tio(X,Y,Z):-varon(X),hermano(X,Z),padre(Z,Y)");
        List<String> parameters = new ArrayList<String>();
        
        parameters.add("agustin");
        parameters.add("ignacio");
        parameters.add("sergio");
        
        List<Fact> facts = rule.findFacts(parameters);

        Assert.assertTrue(rule.getNombre().equals("tio"));
        
        Assert.assertFalse(rule.getArgs() == 2);
        Assert.assertTrue(rule.getArgs() == 3);
        
        Assert.assertFalse(facts.get(0).getName().equals("hermano"));
        Assert.assertFalse(facts.get(0).getName().equals("mujer"));
        Assert.assertTrue(facts.get(0).getName().equals("varon"));
        Assert.assertTrue(facts.get(0).getArgs().get(0).equals("agustin"));
        Assert.assertTrue(facts.get(1).getName().equals("hermano"));
        Assert.assertTrue(facts.get(1).getArgs().get(0).equals("agustin"));
        Assert.assertTrue(facts.get(1).getArgs().get(1).equals("sergio"));
        Assert.assertTrue(facts.get(2).getName().equals("padre"));
        Assert.assertTrue(facts.get(2).getArgs().get(0).equals("sergio"));
        Assert.assertTrue(facts.get(2).getArgs().get(1).equals("ignacio"));      

    }
}
