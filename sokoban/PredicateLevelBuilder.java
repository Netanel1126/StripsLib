package sokoban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;

import strips.Action;
import strips.Clause;
import strips.Plannable;
import strips.Predicate;

public class PredicateLevelBuilder {

	public static Clause getGoal(Clause kb){
		Clause goal=new Clause(null);
		for(Predicate p : kb.getPredicates()){
			if(p.getType().startsWith("tar")){
				goal.add(new Predicate("boxAt", "?", p.getValue()));
			}
		}
		return goal;		
	}
	
	public static Clause getKB(ArrayList<char[]> level){
		Clause kb=new Clause(null);
		int boxCount=0;
		int targetCount=0;
		for(int i=0;i<level.size();i++){
			for(int j=0;j<level.get(i).length;j++){
				switch(level.get(i)[j]){
				case '#':kb.add(new Predicate("wallAt", "", i+","+j));break;
				case ' ':kb.add(new Predicate("clearAt", "", i+","+j));break;
				case 'A':kb.add(new Predicate("sokobanAt", "", i+","+j));break;
				case 'b':boxCount++;kb.add(new Predicate("boxAt", "b"+boxCount, i+","+j));break;
				case '@':targetCount++;kb.add(new Predicate("targetAt", "t"+targetCount, i+","+j));break;
				}
			}
		}
		return kb;		
	}
	
	
	public static Plannable readFile(String fileName){
		try{
			
			ArrayList<char[]> level=new ArrayList<>();
			BufferedReader in=new BufferedReader(new FileReader(fileName));
			String line;
			while((line=in.readLine())!=null){
				level.add(line.toCharArray());
			}
			in.close();
			Clause kb=getKB(level);
			Clause goal=getGoal(kb); 
			Plannable plannable=new Plannable() {
				
				@Override
				public Set<Action> getsatisfyingActions(Predicate top) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Action getsatisfyingAction(Predicate top) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Clause getKnowledgebase() {
					return kb;
				}
				
				@Override
				public Clause getGoal() {
					return goal;
				}
			};			
			return plannable;
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
