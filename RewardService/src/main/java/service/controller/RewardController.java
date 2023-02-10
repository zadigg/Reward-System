package service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;
import service.domain.Reward;
import service.service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {

		@Autowired
		RewardService rewardService;

		@PostMapping("/addReward")
		public ResponseEntity<Reward> addReward(@RequestBody Reward reward) {
			rewardService.addReward(reward);
			return new ResponseEntity<Reward>(reward, HttpStatus.CREATED);
		}

		@GetMapping("/{rewardId}")
		public ResponseEntity<?> getReward(@PathVariable String rewardId){
			Reward reward = rewardService.getReward(rewardId);
			if(rewardId==null){
				return new ResponseEntity<String>("Reward with rewardId= "+rewardId+" not found in the database.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Reward>(reward, HttpStatus.OK);
		}

		@PutMapping("/{rewardId}/update")
		public ResponseEntity<?> updateReward(@RequestBody Reward reward, @PathVariable String rewardId) {
			Boolean rewardExist = rewardService.updateReward(rewardId, reward);
			if(rewardExist) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No reward found with " + reward.getRewardId() + " id", HttpStatus.NOT_FOUND);
			}
		}

		@DeleteMapping("/{rewardId}")
		public ResponseEntity<?> removeReward( @PathVariable String rewardId ) {
			Boolean isExist =  rewardService.deleteReward(rewardId);
			if(isExist) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No rewardID " + rewardId + "found", HttpStatus.NOT_FOUND);
			}

		}

	}