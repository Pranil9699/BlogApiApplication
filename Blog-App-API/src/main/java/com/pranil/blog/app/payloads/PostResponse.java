package com.pranil.blog.app.payloads;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto> content;
	
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalpages;
	private boolean firstPage;
	private boolean lastPage;
	private String sortBy;
	private String sortDir;
}
