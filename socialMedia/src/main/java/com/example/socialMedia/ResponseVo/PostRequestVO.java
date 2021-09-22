package com.example.socialMedia.ResponseVo;

import java.util.List;

public class PostRequestVO {
	
	List<String> content;
	
	int createdUserId;

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	
	public PostRequestVO(List<String> content, int createdUserId) {
		super();
		this.content = content;
		this.createdUserId = createdUserId;
	}
	
	

	public PostRequestVO() {
		super();
	}

	@Override
	public String toString() {
		return "PostRequestVO [content=" + content + ", createdUserId=" + createdUserId + "]";
	}
	
	
}
