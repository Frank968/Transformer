package aeqb.com.beans;

public class Transformer implements Comparable< Transformer >{
	String name;
	String type; //Autobot, Decepticons
	int strength;
	int intelligence;
	int speed;
	int endurance;
	int firepower;
	
	int rank;
	int courage;
	int skill;
	int num;
	
	public Transformer(String name, String type, int strength,int intelligence,int speed,	
			int endurance,int firepower,int rank,int courage, int skill) {
		num = 0;
		this.name=name;
		this.type = type;
		this.strength = strength;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.firepower = firepower;
		this.rank = rank;
		this.courage = courage;
		this.skill = skill;
	}
	
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getStrength() {
		return strength;
	}


	public void setStrength(int strength) {
		this.strength = strength;
	}


	public int getIntelligence() {
		return intelligence;
	}


	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getEndurance() {
		return endurance;
	}


	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}


	public int getFirepower() {
		return firepower;
	}


	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public int getCourage() {
		return courage;
	}


	public void setCourage(int courage) {
		this.courage = courage;
	}


	public int getSkill() {
		return skill;
	}


	public void setSkill(int skill) {
		this.skill = skill;
	}

    //Strength + Intelligence + Speed +    Endurance + Firepower
    public int getOverallRating() {
		return this.strength +  this.intelligence + this.speed + this.endurance + this.firepower;
    }
	
    @Override
    public String toString() {
        return "Transformer:" + name + "," + type + "," + strength + "," + intelligence + "," + speed + ","+firepower + ","+rank + ","+courage + ","+skill;
    }
    
    @Override
    public int compareTo(Transformer o) {
        if(this.getRank() > o.getRank()) {
        	return -1;
        } else if(this.getRank() < o.getRank()){
        	return 1;
        } else {
        	return 0;
        }
    }
}
