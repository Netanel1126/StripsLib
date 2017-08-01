package test;

import sokoban.PredicateLevelBuilder;
import strips.Clause;
import strips.Plannable;
import strips.Predicate;
import strips.SokPredicate;

public class Test {

	public static void main(String[] args) {
		Predicate p1=new SokPredicate("boxAt", "b1", "5,3");
		Predicate p2=new SokPredicate("boxAt", "b2", "0,0");

		Predicate p4=new SokPredicate("clearAt", "?", "5,3");
		
		Clause effect=new Clause(p4);
		
		
		Clause kb=new Clause(p1,p2);
		System.out.println(kb);
		kb.update(effect);
		System.out.println(kb);
		
		System.out.println((effect instanceof Clause));
		System.out.println((p1 instanceof Clause));
		
		Plannable plannable=PredicateLevelBuilder.readFile("level.txt");
		System.out.println(plannable.getKnowledgebase());
		System.out.println(plannable.getGoal());
	}

}
