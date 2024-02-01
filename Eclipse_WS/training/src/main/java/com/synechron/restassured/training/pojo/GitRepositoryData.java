package com.synechron.restassured.training.pojo;

public class GitRepositoryData 
{
	private String name =null;
	private String description =null;
	private String homepage =null;
	private boolean is_private =false;
	private boolean is_template =true;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getHomepage() {
		return homepage;
	}
	
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	public boolean isIs_private() {
		return is_private;
	}
	
	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}
	public boolean isIs_template() {
		return is_template;
	}
	public void setIs_template(boolean is_template) {
		this.is_template = is_template;
	}
	
	
	

}
