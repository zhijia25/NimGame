public abstract class NimPlayer {


	private String userName, givenName,familyName;
	private int gameNumber, wonNumber;
	private double ratio;
	private boolean checkAI;
  
   public NimPlayer() {
	   userName="default";
	   familyName="default";
	   givenName="default";
	   gameNumber=0;
	   wonNumber=0;
	   ratio=0;
	   checkAI=false;
   }
	
	public NimPlayer(String theUserName,String theFamilyName,String theGivenName, int theGameNumber,int theWonNumber,double theRatio,boolean theCheckAI){	 
     	userName=theUserName;
     	familyName=theFamilyName;
     	givenName=theGivenName;
     	gameNumber=theGameNumber;
     	wonNumber= theWonNumber;
     	ratio=theRatio;
     	checkAI=theCheckAI;
   }
   
   
	public void setUserName(String theUsername) {
    	userName=theUsername;
    	   }
    
    public void setFamilyName(String theFamilyname) {
    	familyName=theFamilyname;
    	   }
    
    public void setGivenName(String theGivenname) {
    	 givenName=theGivenname;
    }
    
    public void setGameNumber(int theGamenumber) {
    	gameNumber=theGamenumber;
    }

    public void setWonNumber(int theWonnumber) {
    	 wonNumber=theWonnumber;
    }
    public void addGameNumber() {
    	gameNumber=gameNumber+1;
    }
    public void addWonNumber() {
   	 wonNumber=wonNumber+1;
   }
	
	public double setRatio() {
    	ratio=(double)wonNumber/gameNumber;
    	return ratio;
    }
    public String getUserName() {
    	return userName;
    	   }
    
    public String getFamilyName() {
    	return familyName;
    	   }
    
    public String getGivenName() {
    	return givenName;
    }
    
    public int getGameNumber() {
    	return gameNumber;
    }

    public int getWonNumber() {
    	return wonNumber;
    }
    
    public double getRatio(){
    	return this.ratio;
    }
    public boolean getCheck(){
    	return checkAI;
    }
}
