package com.spgroup.friend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spgroup.friend.api.dto.request.SubscribeRequestDto;
import com.spgroup.friend.api.util.ValidatorComponent;
import com.spgroup.friend.entity.SubscriptionEntity;
import com.spgroup.friend.entity.SubscriptionPk;
import com.spgroup.friend.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ValidatorComponent validator;

	public void subscribe(SubscribeRequestDto subscriptionDto) {

		validate(subscriptionDto);

		SubscriptionPk pk = new SubscriptionPk();
		pk.setRequestorEmailId(subscriptionDto.getRequestor());
		pk.setTargetEmailId(subscriptionDto.getTarget());

		SubscriptionEntity entity = subscriptionRepository.findById(pk).orElse(null);

		if (entity != null) {
			entity.setBlock(false);
			subscriptionRepository.save(entity);
		} else {
			entity = new SubscriptionEntity();
			entity.setPk(pk);
			entity.setBlock(false);
			subscriptionRepository.save(entity);
		}
		subscriptionRepository.save(entity);

	}

	private void validate(SubscribeRequestDto subscriptionDto) {
		validator.validateEmail(subscriptionDto.getRequestor());
		validator.validateEmail(subscriptionDto.getTarget());

		// Validate User Exist or Not
		userService.validateUser(subscriptionDto.getRequestor());
		userService.validateUser(subscriptionDto.getTarget());
	}

}
