package com.pratik.BloggingApp.service;

import com.pratik.BloggingApp.model.Follow;
import com.pratik.BloggingApp.model.User;
import com.pratik.BloggingApp.repository.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    IFollowRepo followRepo;

    public boolean isFollowAllowed(User targetUser, User follower) {
        List<Follow> followList =  followRepo.findByCurrentUserAndCurrentUserFollower(targetUser,follower);

        return followList!=null && followList.isEmpty() && !targetUser.equals(follower);
    }

    public void startFollowing(Follow follow, User follower) {
        follow.setCurrentUserFollower(follower);
        followRepo.save(follow);

    }

    public Follow findFollow(Integer followId) {
        return followRepo.findById(followId).orElse(null);
    }

    public void unfollow(Follow follow) {
        followRepo.delete(follow);
    }
}
