package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseTest {

	 @Test
	 public void testAddFacts() throws InvalidDBLineException {
		KnowledgeBase kBase = new KnowledgeBase();
	    List<String> args = new ArrayList<String>();
	    args.add("river");
	    Fact fact = new Fact("campeon", args);
	    Fact fact2 = new Fact("bicampeon", args);
		kBase.addFact(fact);    
		Assert.assertTrue(kBase.getFacts().contains(fact));
		Assert.assertFalse(kBase.getFacts().contains(fact2));
		kBase.addFact(fact2);    
		Assert.assertTrue(kBase.getFacts().contains(fact2));
	 }
	 
	 @Test
	 public void testAddRules() throws InvalidDBLineException {
		KnowledgeBase kBase = new KnowledgeBase();
		Rule rule = new Rule("tio(X,Y,Z):-varon(X),hermano(X,Z),padre(Z,Y)");
		Rule rule2 = new Rule("tia(X,Y,Z):-mujer(X),hermano(X,Z),padre(Z,Y)");
		kBase.addRule(rule);    
		Assert.assertTrue(kBase.getRules().contains(rule));
		Assert.assertFalse(kBase.getRules().contains(rule2));
		kBase.addRule(rule2);    
		Assert.assertTrue(kBase.getRules().contains(rule2));
	 }
	 
    @Test
    public void testInvalidQueriesInDB() throws InvalidDBLineException {
    	ParserKB parserKb = new ParserKB();
        KnowledgeBase kBase = parserKb.create(new File("").getAbsolutePath().concat("\\src\\main\\resources\\rules.db"));

        try {
        	kBase.answer("");
            Assert.assertTrue(false);
        } catch (InvalidQException e) {
        }
        try {
        	kBase.answer("().");
            Assert.assertTrue(false);
        } catch (InvalidQException e) {
        }
        try {
        	kBase.answer("mujer(a,)).");
            Assert.assertTrue(false);
        } catch (InvalidQException e) {
        }
        try {
        	kBase.answer("12312");
            Assert.assertTrue(false);
        } catch (InvalidQException e) {
        }
        try {
        	kBase.answer(").");
            Assert.assertTrue(false);
        } catch (InvalidQException e) {
        }
        try {
        	kBase.answer("  ");
            Assert.assertTrue(false);
        } catch (InvalidQException e) {
        }

        try {
        	kBase.answer("madre");
            Assert.assertTrue(false);
        } catch (InvalidQException e) {
        }
    }

    @Test
    public void testValidRuleQueriesDB() throws InvalidDBLineException, InvalidQException {
    	ParserKB parserKb = new ParserKB();
        KnowledgeBase kBase = parserKb.create(new File("").getAbsolutePath().concat("\\src\\main\\resources\\rules.db"));
 
        Assert.assertTrue(kBase.answer("varon(hector)."));
        Assert.assertTrue(kBase.answer("mujer(cecilia)."));
        Assert.assertTrue(kBase.answer("hermano(roberto, nicolas)."));         
        Assert.assertTrue(kBase.answer("hijo(pepe,juan)."));
        Assert.assertTrue(kBase.answer("hijo(alejandro,roberto)."));
        Assert.assertTrue(kBase.answer("hija(cecilia,roberto)."));
        Assert.assertTrue(kBase.answer("tio(nicolas,alejandro,roberto)."));
        Assert.assertTrue(kBase.answer("tio(nicolas,cecilia,roberto)."));

        Assert.assertFalse(kBase.answer("mujer(hector)."));
        Assert.assertFalse(kBase.answer("varon(beatriz)."));
        Assert.assertFalse(kBase.answer("hija(pepa,juan)."));
        Assert.assertFalse(kBase.answer("tio(pepa,juan,carlos)."));
        Assert.assertFalse(kBase.answer("hijo(juan,pepa)."));

    }
}
