package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rule {
    private String nombre;
    private List<Fact> facts;
    private int args;

    public Rule(String str) {
        String [] ruleParsed = str.split(":-");
        String [] rule = ruleParsed[0].replace(")","").split("\\(");
        String [] args = rule[1].split(",");
        String [] facts = ruleParsed[1].split("\\),");
        this.nombre = rule[0];
        this.facts = new ArrayList<Fact>();
        this.args = args.length;
        this.zipMap(args,facts);
        this.setFacts(facts);
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Fact> getFacts() {
        return this.facts;
    }

    public int getArgs() {
        return this.args;
    }
    
    private void zipMap(String [] args, String [] facts) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < facts.length; j++) {
            	facts[j] = facts[j].replace(args[i], String.valueOf(i));
            }
        }
    }
    private void setFacts(String [] facts) {
        for (String fact : facts) {
            this.facts.add(new Fact(fact));
        }
    }

    public List<Fact> findFacts(List<String> param) {
        List<Fact> facts = new ArrayList<Fact>();
        for (Fact fact : this.facts) {
        	facts.add(new Fact(fact));
        }
        for (int i = 0; i < this.args; i++) {
            for (Fact fact : facts) {
                List<String> vals = fact.getArgs();
                for (int j = 0; j < vals.size(); j++) {
                    if (vals.get(j).equals(String.valueOf(i))) {
                    	vals.set(j, param.get(i));
                    }
                }
            }
        }
        return facts;
    }
    
}
