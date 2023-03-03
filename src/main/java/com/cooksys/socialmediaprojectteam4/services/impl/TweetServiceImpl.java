package com.cooksys.socialmediaprojectteam4.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.ContextDto;
import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.Hashtag;
import com.cooksys.socialmediaprojectteam4.entities.Tweet;
import com.cooksys.socialmediaprojectteam4.entities.User;
import com.cooksys.socialmediaprojectteam4.exceptions.BadRequestException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotAuthorizedException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotFoundException;
import com.cooksys.socialmediaprojectteam4.mappers.HashtagMapper;
import com.cooksys.socialmediaprojectteam4.mappers.TweetMapper;
import com.cooksys.socialmediaprojectteam4.mappers.UserMapper;
import com.cooksys.socialmediaprojectteam4.repositories.HashtagRepository;
import com.cooksys.socialmediaprojectteam4.repositories.TweetRepository;
import com.cooksys.socialmediaprojectteam4.repositories.UserRepository;
import com.cooksys.socialmediaprojectteam4.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;
  private final TweetMapper tweetMapper;
  private final HashtagMapper hashtagMapper;
  private final UserMapper userMapper;
  private final HashtagRepository hashtagRepository;
  private final UserRepository userRepository;
  private final UserServiceImpl userService;

  public Tweet getTweet(Long id) {
    if (tweetRepository.findByIdAndDeletedFalse(id).isEmpty()) {
      throw new NotFoundException("Tweet with ID " + id + " does not exist.");
    }
    return tweetRepository.findByIdAndDeletedFalse(id).get();
  }

  public void validateCredentials(User databaseCredentials, CredentialsDto credentialsDto) {
    if (credentialsDto == null)
      throw new NotAuthorizedException("Credentials must be provided!");
    if (!databaseCredentials.getCredentials().getUsername().equalsIgnoreCase(credentialsDto.getUsername())
        || !databaseCredentials.getCredentials().getPassword().equals(credentialsDto.getPassword())) {
      throw new NotAuthorizedException("Credentials do not match!");
    }
  }

  public void validateTweetCredentials(User databaseCredentials, TweetRequestDto tweetRequestDto) {
    if (tweetRequestDto.getCredentials() == null)
      throw new NotAuthorizedException("Credentials must be provided!");
    if (!databaseCredentials.getCredentials().getUsername()
        .equalsIgnoreCase(tweetRequestDto.getCredentials().getUsername())
        || !databaseCredentials.getCredentials().getPassword().equals(tweetRequestDto.getCredentials().getPassword())) {
      throw new NotAuthorizedException("Credentials do not match!");
    }
  }

  private Hashtag createHashtag(String label) {
    Hashtag hashtagToCreate = new Hashtag();
    hashtagToCreate.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
    hashtagToCreate.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));
    hashtagToCreate.setLabel(label);
    return hashtagToCreate;
  }

//	Helper method to find #'s and @'s when creating a tweet
  private List<String> filterData(String content, String contentToFind) {
    List<String> stringList = new ArrayList<>();
    for (String string : Arrays.asList(content.split(" "))) {
      if (string.contains(contentToFind)) {
        if (string.charAt(0) == contentToFind.charAt(0))
          stringList.add(string.substring(1));
      }
    }
    return stringList;
  }

  public void registerHashtags(Tweet tweet) {
//		Grabs all hashtags from tweet's content and loops through all hashtags from tweet
    for (String label : filterData(tweet.getContent(), "#")) {
//			for each hashtag, uses "findByLabel" method and checks if hashtag exists in database
//			If doesn't exist, uses "createHashtag" method to create new hashtag with label
      if (hashtagRepository.findByLabel(label).isEmpty()) {
        Hashtag createdHashtag = createHashtag(label);
//				Saves hashtag to database using "saveAndFlush" method
        hashtagRepository.saveAndFlush(createdHashtag);
//				new hashtag obj is added to list of hashtags in the tweet obj
        tweet.getHashtags().add(createdHashtag);
      } else {
//				If exists, grabs hashtag from database using "findByLabel" method
        Hashtag hashtagToUpdate = hashtagRepository.findByLabel(label).get();
//				"setTweets" method is used on hashtag to add current tweet to list of tweets with hashtag
        hashtagToUpdate.setTweets(hashtagRepository.findByLabel(label).get().getTweets());
//				Updates the last used timestamp of hashtag
        hashtagToUpdate.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));
//				Updated hashtag is now saved to database 
        hashtagRepository.saveAndFlush(hashtagToUpdate);
//				Updated hashtag is added to list of hashtags in tweet obj
        tweet.getHashtags().add(hashtagToUpdate);
      }
    }
  }

  public void registerMentions(Tweet tweet) {
//		filterData is helper function above
//		Returns list of usernames mentioned in the tweet by using @ to find usernames
    for (String string : filterData(tweet.getContent(), "@")) {
      Optional<User> optionalUser = userRepository.findByCredentialsUsername(string);
      if (!optionalUser.isEmpty()) {
        optionalUser.get().getUserMentions().add(tweet);
        tweet.getMentionedUsers().add(optionalUser.get());
      }
    }
  }

  @Override
  public List<TweetResponseDto> getAllTweets() {
    List<Tweet> tweets = tweetRepository.findAllByDeletedFalse();
    Collections.sort(tweets);
    return tweetMapper.entitiesToDtos(tweets);
  }

  @Override
  public TweetResponseDto getTweetById(Long id) {
    return tweetMapper.entityToDto(getTweet(id));
  }

  @Override
  public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
//		Checks if tweet is empty
    if (tweetRequestDto.getContent() == null || tweetRequestDto.getContent().isEmpty()
        || tweetRequestDto.getContent().isBlank()) {
      throw new BadRequestException("Tweet cannot be empty!");
    }
//		Checks if username and password field is empty
    if (tweetRequestDto.getCredentials() == null) {
      throw new BadRequestException("Username and password is required!");
    }
    if (tweetRequestDto.getCredentials().getUsername() == null) {
      throw new BadRequestException("Username is required!");
    }
    if (tweetRequestDto.getCredentials().getPassword() == null) {
      throw new BadRequestException("Password is required!");
    }
//		Checks if username and password match
    validateTweetCredentials(userService.getUserByCredentials(tweetRequestDto.getCredentials()), tweetRequestDto);
//		}

    Tweet tweetToCreate = tweetMapper.requestDtoToEntity(tweetRequestDto);
    tweetToCreate.setAuthor(userService.getUserByCredentials(tweetRequestDto.getCredentials()));
    tweetRepository.saveAndFlush(tweetToCreate);

    registerHashtags(tweetToCreate);
    tweetRepository.saveAndFlush(tweetToCreate);
    registerMentions(tweetToCreate);
    return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToCreate));
  }

  @Override
  public TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto) {
    validateCredentials(getTweet(id).getAuthor(), credentialsDto);
    getTweet(id).setDeleted(true);
    return tweetMapper.entityToDto(tweetRepository.saveAndFlush(getTweet(id)));
  }

  @Override
  public void likeTweet(Long id, CredentialsDto credentialsDto) {
    Tweet tweetToBeLiked = getTweet(id);
    User user = userService.getUserByCredentials(credentialsDto);
//		If tweet alrdy liked by user, returns without doing anything
    if (tweetToBeLiked.getLikes().contains(user))
      return;
    validateCredentials(user, credentialsDto);
//		Adds user to "liked" list of the tweet
    tweetToBeLiked.getLikes().add(user);
//		Adds liked tweet to the user's list of liked tweets
    user.getUserLikes().add(tweetToBeLiked);
//		Saves and updates tweet and user obj 
    tweetRepository.saveAndFlush(tweetToBeLiked);
    userRepository.saveAndFlush(user);
  }

  @Override
  public TweetResponseDto replyToTweet(Long id, TweetRequestDto tweetRequestDto) {
    Tweet tweetResponseToExistingTweet = getTweet(createTweet(tweetRequestDto).getId());
    Tweet tweetBeingRepliedTo = getTweet(id);

    tweetResponseToExistingTweet.setInReplyTo(tweetBeingRepliedTo);
    tweetBeingRepliedTo.getReplies().add(tweetResponseToExistingTweet);

    tweetRepository.saveAndFlush(tweetBeingRepliedTo);
    return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetResponseToExistingTweet));
  }

  @Override
  public TweetResponseDto retweet(Long id, CredentialsDto credentialsDto) {
    Tweet tweetToRetweet = getTweet(id);
    Tweet tweetToSave = new Tweet();
    validateCredentials(userService.getUserByUsernameReturnUserEntity(credentialsDto.getUsername()), credentialsDto);

    tweetToSave.setAuthor(userService.getUserByUsernameReturnUserEntity(credentialsDto.getUsername()));
    tweetToSave.setContent(tweetToRetweet.getContent());
    tweetToSave.setRepostOf(tweetToRetweet);
    tweetToSave.setTimePosted(Timestamp.valueOf(LocalDateTime.now()));
    tweetToRetweet.getReposts().add(tweetRepository.saveAndFlush(tweetToSave));
    tweetRepository.saveAndFlush(tweetToRetweet);

    return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToRetweet));
  }

  @Override
  public List<HashtagDto> getTweetHashtags(Long id) {
    return hashtagMapper.entitiesToDtos(getTweet(id).getHashtags());
  }

  @Override
  public List<UserResponseDto> getTweetLikes(Long id) {
//		Get list of users who have liked the tweet
    List<User> likes = new ArrayList<>(getTweet(id).getLikes());
//		If deleted remove user from list
    likes.removeIf(User::isDeleted);
    return userMapper.entitiesToDtos(likes);
  }

  @Override
  public ContextDto getTweetContext(Long id) {

//		Holds context info
    ContextDto context = new ContextDto();
//		Gets tweet and parent/child tweet
    Tweet inReplyTo = getTweet(id).getInReplyTo();
    List<Tweet> replies = getTweet(id).getReplies();
    List<Tweet> listOfInReplyTo = new ArrayList<>();
//		Add tweet's parent tweet to list and continues to add to list until no more parent tweets 
    while (inReplyTo != null) {
      listOfInReplyTo.add(inReplyTo);
      inReplyTo = inReplyTo.getInReplyTo();
    }
//		Removes deleted tweets
    listOfInReplyTo.removeIf(Tweet::isDeleted);
    replies.removeIf(Tweet::isDeleted);
//		Set mapped Tweet as target, Parents as before, and List as after
    context.setTarget(tweetMapper.entityToDto(getTweet(id)));
    context.setBefore(tweetMapper.entitiesToDtos(listOfInReplyTo));
    context.setAfter(tweetMapper.entitiesToDtos(replies));

    return context;
  }

  @Override
  public List<TweetResponseDto> getTweetReplies(Long id) {
    List<Tweet> replies = getTweet(id).getReplies();
    Collections.sort(replies);
    replies.removeIf(Tweet::isDeleted);
    return tweetMapper.entitiesToDtos(replies);
  }

  @Override
  public List<TweetResponseDto> getTweetReposts(Long id) {
//		Get list of tweets that have been reposted
    List<Tweet> reposts = new ArrayList<>(getTweet(id).getReposts());
//		Sorts list in order based on compareTo method created in Tweet entity class
    Collections.sort(reposts);
//		If deleted remove tweet from list
    reposts.removeIf(Tweet::isDeleted);
    return tweetMapper.entitiesToDtos(reposts);
  }

  @Override
  public List<UserResponseDto> getTweetUsersMentioned(Long id) {
    return userMapper.entitiesToDtos(getTweet(id).getMentionedUsers());
  }

}
