package OJDevs.ensonglopediaPack;
public enum Reference{
	Book1,Book2;
	
	public String toString(){
		switch(this){
			case Book1: return "Example Book";
			case Book2: return "Error";
			default: return "";
		}
	}
}
