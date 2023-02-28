package com.cooksys.socialmediaprojectteam4.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TweetRequestDto {

	private String content;
	
	private CredentialsDto credentials;
}
