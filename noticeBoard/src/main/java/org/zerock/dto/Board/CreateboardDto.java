package org.zerock.dto.Board;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateboardDto {
	
	private String title;
	private String contents;
	private String identity;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	

	
}
