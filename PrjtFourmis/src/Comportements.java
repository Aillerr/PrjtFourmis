
public enum Comportements {
	NOTHING, GO, GO_LEFT, GO_RIGHT, GO_UP, GO_DOWN, RECOLT, DEPOSE, GO_HOME, IS_FOOD, IS_HOME;
	
	public boolean isQuestion() { //Rajouter les comportements IS_....
    	return (this.equals(Comportements.IS_FOOD) || this.equals(Comportements.IS_HOME));
    }
}
