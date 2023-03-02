package com.cooksys.socialmediaprojectteam4.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.repositories.HashtagRepository;
import com.cooksys.socialmediaprojectteam4.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final HashtagRepository hashtagRepository;
	
	@Override
	public boolean hashtagExists(String label) {
		return hashtagRepository.findByLabel(label).isPresent();
	}
}
