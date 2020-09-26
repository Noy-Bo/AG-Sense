package Enums;

public enum Errors {
	DATABASE(0, "A database error has occurred."),
	DUPLICATE_USER(1, "This user already exists.");
	

	  private final int code;
	  private final String description;

	  Errors(int i, String string)
	  {
		  this.code = i;
		    this.description = string;
	  }



	  
	  public String getDescription() {
	     return description;
	  }

	  public int getCode() {
	     return code;
	  }

	  @Override
	  public String toString() {
	    return code + ": " + description;
	  }
	  
	}