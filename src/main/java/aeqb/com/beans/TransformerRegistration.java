package aeqb.com.beans;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;


// for temporary store, fake db
public class TransformerRegistration{
  private List<Transformer> transformerRecords;
  static int number = 1;
  private static TransformerRegistration transReg= null;
  
  private TransformerRegistration() {
    transformerRecords = new ArrayList<Transformer>();
  }
  
  public static TransformerRegistration getInstance() {
    if (transReg == null) {
    	transReg = new TransformerRegistration();
      return transReg;
    }
    else {
      return transReg;
    }
  }

  public TransformerResponse addTransformer(Transformer trans) {	  
	  System.out.println("addTransformer: "+trans);
	  
	  TransformerResponse response = null; 
	  
	  if(("A".contentEquals(trans.getType()) || "D".contentEquals(trans.getType())) 
			&& (trans.getStrength() >= 1 && trans.getStrength() <= 10)
			&& (trans.getIntelligence() >= 1 && trans.getIntelligence() <= 10)
			&& (trans.getSpeed() >= 1 && trans.getSpeed() <= 10)
			&& (trans.getEndurance() >= 1 && trans.getEndurance() <= 10)
			&& (trans.getFirepower() >= 1 && trans.getFirepower() <= 10)
			&& (trans.getRank() >= 1 && trans.getRank() <= 10)
			&& (trans.getCourage() >= 1 && trans.getCourage() <= 10)
			&& (trans.getSkill() >= 1 && trans.getSkill() <= 10)) {	
		  trans.setNum(++number);
		  transformerRecords.add(trans);
		  response = new TransformerResponse("OK","Add Success");
	  } else {
		  response = new TransformerResponse("Fail","Parameter error");
	  }	
	  return response;
  }
  
  public TransformerResponse updateTransformer(int regNum, Transformer trans) {
	System.out.println("updateTransformer: "+trans);
	
    for (int i = 0; i < transformerRecords.size(); i++)
    {
      Transformer tmp = transformerRecords.get(i);
      if (tmp.getNum() == regNum) {
        transformerRecords.set(i, trans);//update the new record
        return new TransformerResponse("OK","Update Success");        
      }
    }
    
    return new TransformerResponse("Fail","Update error"); 
  }
  
  public TransformerResponse deleteTransformer(int registrationNumber) {
	System.out.println("deleteTransformer: "+registrationNumber);
    for (int i = 0; i < transformerRecords.size(); i++)
    {
      Transformer tmp = transformerRecords.get(i);
      if (tmp.getNum() == registrationNumber) {
        transformerRecords.remove(i);//update the new record
        return new TransformerResponse("OK","Delete Success"); 
      }
    }
    return new TransformerResponse("Fail","Delete error"); 
  }
  
  public List<Transformer> getTransformerRecords() {
    return transformerRecords;
  }
  
  public TransformerResponse getWinningTeam() {
	// 
	System.out.println("getWinningTeam size: " + transformerRecords.size());
	int battle = 0;
	int aWin = 0;
	int dWin = 0;
	String lastWinName ="";
	String winTeam =  "";
	String loseTeam = "";
	StringBuffer survivorFromLose = new StringBuffer(""); 	
	Transformer winTeamLastone = null;
	
	if(transformerRecords.isEmpty()) {
	    System.out.println("getWinningTeam is empty " );
		return new TransformerResponse("Fail","Transformer list is empty");
	}
	
	//split into two team
	List<Transformer> aRecords=new ArrayList<Transformer>();
	List<Transformer> dRecords=new ArrayList<Transformer>();
	for (int i = 0; i < transformerRecords.size(); i++)
	{
	  Transformer tmp = transformerRecords.get(i);
	  if ("A".contentEquals(tmp.getType())) {
		aRecords.add(tmp);//update the new record	      
	} else {
		  dRecords.add(tmp);//update the new record	      
		  }
		}

	//sort two team
	Collections.sort(aRecords);
	Collections.sort(dRecords);

	// determine algorithm with stack
	Stack<Transformer> stackOfRecordA = new Stack<>();
	Stack<Transformer> stackOfRecordD = new Stack<>();
	
	for (int i = 0; i < aRecords.size(); i++)
	{
	  stackOfRecordA.push(aRecords.get(i));
	}
	
	for (int i = 0; i < dRecords.size(); i++)
	{
	  stackOfRecordD.push(dRecords.get(i));
	}
	
	System.out.println("getWinningTeam A" + aRecords.get(0).getRank()+",A" + aRecords.get(1).getRank()+" ,D" + stackOfRecordD.size());
	
	//if first is empty
	if(stackOfRecordA.empty() && !stackOfRecordD.empty()) {
		System.out.println("getWinningTeam A empty");
		dWin++;
	} else if(!stackOfRecordA.empty() && stackOfRecordD.empty()) {
		System.out.println("getWinningTeam D empty");
		aWin++;
	} else {
		while(!stackOfRecordA.empty() && !stackOfRecordD.empty()) {
			Transformer tmpA = stackOfRecordA.peek();		
			Transformer tmpD = stackOfRecordD.peek();
		
			//Transformer transATop = stackOfRecordA.pop();
			battle++;		
			// battle one on one fight
			System.out.println("getWinningTeam handle battle " + battle);
			
			// Special rule
			 if(("Optimus Prime".contentEquals(tmpA.getName())) ||("Predaking".contentEquals(tmpA.getName()))
					 && ("Optimus Prime".contentEquals(tmpD.getName())) ||("Predaking".contentEquals(tmpD.getName()))){
				 // destroy all
				 stackOfRecordA.clear();
				 stackOfRecordD.clear();
				 break;
			  } else if(("Optimus Prime".contentEquals(tmpA.getName())) || ("Predaking".contentEquals(tmpA.getName()))) {
				  // A win
				  winTeamLastone = tmpA;
				  aWin++;
			  } else if(("Optimus Prime".contentEquals(tmpD.getName())) || ("Predaking".contentEquals(tmpD.getName()))) {
				  // D win
				  winTeamLastone = tmpD;
				  dWin++;
			  } else {
				// regular rules
				  int offCourage = tmpA.getCourage() - tmpD.getCourage();
				  int offStrength = tmpA.getStrength() - tmpD.getStrength();
				  int offSkill = tmpA.getSkill() - tmpD.getSkill();
				  int offOverating = tmpA.getOverallRating() - tmpD.getOverallRating();
				  
				  if(offCourage>=4 && offStrength >=3) {
					// A win
					  winTeamLastone = tmpA;
					  aWin++;
				  } else if(offSkill >= 3){
					// A win
					  winTeamLastone = tmpA;
					  aWin++;
				  } else if(offCourage <= -4 && offStrength <= -3) {
					// D win
					  winTeamLastone = tmpD;
					  dWin++;
				  } else if(offSkill <= -3){
					// D win
					  winTeamLastone = tmpD;
					  dWin++;
				  } else {
					  if(offOverating > 0) {
						  // A win
						  winTeamLastone = tmpA;
						  aWin++;
					  }else if(offOverating < 0) {
						  // D win
						  winTeamLastone = tmpD;
						  dWin++;
					  }else {
						  // destroy both
					  }
				  }
			  }
			 stackOfRecordA.pop();
			 stackOfRecordD.pop();			   
		}
	}	

	if(winTeamLastone != null) {
		lastWinName = winTeamLastone.getName();
	}
		
	if(dWin > aWin) {
		winTeam = "Winning team (Decepticons)";
		loseTeam = "Survivors from the losing team (Autobots)";
		while(!stackOfRecordA.empty()){
			survivorFromLose.append(stackOfRecordA.pop().getName());
			if(!stackOfRecordA.empty()) {
				survivorFromLose.append(",");
			}
		}
	}else if(dWin < aWin) {
		winTeam = "Winning team (Autobots)";
		loseTeam = "Survivors from the losing team (Decepticons)";
		while(!stackOfRecordD.empty()){
			survivorFromLose.append(stackOfRecordD.pop().getName());
			if(!stackOfRecordD.empty()) {
				survivorFromLose.append(",");
			}
		}
	} else {
		return new TransformerResponse("OK", "No winning team");
	}
		
	String strMsg = String.format("battle %d%n%s:%s%n%s:%s", battle, winTeam, lastWinName,loseTeam, survivorFromLose.toString());
	System.out.println("getWinningTeam end");
	return new TransformerResponse("OK", strMsg);
	}
}
