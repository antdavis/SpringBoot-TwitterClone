package com.cooksys.socialmediaprojectteam4.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaprojectteam4.dtos.ContextDto;
import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
	
	private final TweetService tweetService;

	@GetMapping
	public List<TweetResponseDto> getAllTweets() {
		return tweetService.getAllTweets();
	}

	@GetMapping("/{id}")
	public TweetResponseDto getTweetById(@PathVariable Long id) {
		return tweetService.getTweetById(id);
	}

	@GetMapping("/{id}/context")
	public ContextDto getTweetContext(@PathVariable Long id) {
		return tweetService.getTweetContext(id);
	}
	
	@PostMapping("/{id}/reply")
	public TweetResponseDto replyToTweet(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.replyToTweet(id, tweetRequestDto);
	}

	@GetMapping("/{id}/replies")
	public List<TweetResponseDto> getTweetReplies(@PathVariable Long id) {
		return tweetService.getTweetReplies(id);
	}

	@GetMapping("/{id}/likes")
	public List<UserResponseDto> getTweetLikes(@PathVariable Long id) {
		return tweetService.getTweetLikes(id);
	}

	@GetMapping("/{id}/tags")
	public List<HashtagDto> getTweetHashtags(@PathVariable Long id) {
		return tweetService.getTweetHashtags(id);
	}

	@GetMapping("/{id}/reposts")
	public List<TweetResponseDto> getTweetReposts(@PathVariable Long id) {
		return tweetService.getTweetReposts(id);
	}

	@GetMapping("/{id}/mentions")
	public List<UserResponseDto> getTweetUsersMentioned(@PathVariable Long id) {
		return tweetService.getTweetUsersMentioned(id);
	}
	
	@PostMapping
	public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.createTweet(tweetRequestDto);
	}

	@PostMapping("/{id}/like")
	public void likeTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
		tweetService.likeTweet(id, credentialsDto);
	}

	@PostMapping("/{id}/repost")
	public TweetResponseDto retweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
		return tweetService.retweet(id, credentialsDto);
	}
	
	@DeleteMapping("/{id}")
	public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
		return tweetService.deleteTweet(id, credentialsDto);
	}
}
