package com.xmy.service;

import com.xmy.bean.Friend;
import com.xmy.dao.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 16:45 2017/12/27
 */
@Service
public class FriendService {

    @Autowired
    FriendRepository friendRepository;

    /**
     * 请求添加好友
     */
    public void addFriend(int user_id, int friend_id, String information){
        Friend friend = new Friend();

        friendRepository.save(friend);
    }

    /**
     * 同意添加
     */
    public void approve(){

    }

    /**
     * 拒绝添加
     */
    public void refuse(){

    }

}
