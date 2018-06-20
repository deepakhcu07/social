package com.spgroup.friend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spgroup.friend.api.dto.request.SubscribeRequestDto;
import com.spgroup.friend.api.dto.request.UpdateRequestDto;
import com.spgroup.friend.api.dto.response.RecipientResponseDto;
import com.spgroup.friend.api.util.ValidatorComponent;
import com.spgroup.friend.entity.SubscriptionEntity;
import com.spgroup.friend.entity.SubscriptionPk;
import com.spgroup.friend.exception.InvalidDataException;
import com.spgroup.friend.repository.SubscriptionRepository;
import com.spgroup.friend.util.EmailUtil;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ValidatorComponent validator;

	/**
	 * A user subscribes updates from another user.
	 * A requestor is subscribing target. 
	 * If requestor has already block the target, then it only updates the block column.
	 * @param subscriptionDto
	 */
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

	/**
	 * A user block updates from another user.
	 * @param blockDto
	 */
	public void block(SubscribeRequestDto blockDto) {
		validate(blockDto);

		SubscriptionPk pk = new SubscriptionPk();
		pk.setRequestorEmailId(blockDto.getRequestor());
		pk.setTargetEmailId(blockDto.getTarget());

		SubscriptionEntity entity = subscriptionRepository.findById(pk).orElse(null);

		if (entity != null) {
			entity.setBlock(true);
			subscriptionRepository.save(entity);
		} else {
			entity = new SubscriptionEntity();
			entity.setPk(pk);
			entity.setBlock(true);
			subscriptionRepository.save(entity);
		}
	}

	/**
	 * Returns true, if requestor has blocked the target
	 * @param requestor
	 * @param target
	 * @return
	 */
	public boolean isBlock(String requestor, String target) {
		SubscriptionPk subscriptionPk = new SubscriptionPk();
		subscriptionPk.setRequestorEmailId(requestor);
		subscriptionPk.setTargetEmailId(target);

		SubscriptionEntity entity = subscriptionRepository.findById(subscriptionPk).orElse(null);
		if (entity == null) {
			return false;
		}
		return entity.isBlock();
	}

	/**
	 * Returns all the Recipients who will get updates 
	 * @param updates
	 * @return
	 */
	public RecipientResponseDto getRecipients(UpdateRequestDto updates) {
		validator.validateEmail(updates.getSender());
		List<String> recipients = subscriptionRepository.getRecipients(updates.getSender());
		List<String> blockedUsers = subscriptionRepository.getBlockUser(updates.getSender());
		List<String> mentionedEmails = EmailUtil.getAllMentionedEmail(updates.getText());
		mentionedEmails.removeAll(blockedUsers);
		recipients.addAll(mentionedEmails);

		RecipientResponseDto result = new RecipientResponseDto();
		result.setSuccess(true);
		result.setRecipients(recipients);

		return result;
	}

	private void validate(SubscribeRequestDto subscriptionDto) {
		validator.validateEmail(subscriptionDto.getRequestor());
		validator.validateEmail(subscriptionDto.getTarget());

		if (subscriptionDto.getRequestor().equals(subscriptionDto.getTarget())) {
			throw new InvalidDataException("Requestor and Target Email Id cannot be same");
		}

		// Validate User Exist or Not
		userService.validateUser(subscriptionDto.getRequestor());
		userService.validateUser(subscriptionDto.getTarget());
	}

}
