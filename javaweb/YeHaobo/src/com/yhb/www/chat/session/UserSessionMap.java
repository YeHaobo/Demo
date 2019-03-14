package com.yhb.www.chat.session;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 当前连接的用户的SessionMap
 */

public class UserSessionMap extends ConcurrentHashMap<String, IoSession> {

    private static UserSessionMap sessionMap;

    private UserSessionMap() {
        super(500);
    }

    public static UserSessionMap getInstance() {
        if (sessionMap == null) {
            sessionMap = new UserSessionMap();
        }
        return sessionMap;
    }

    //获取单个用户Session
    public static IoSession GetAnSession(UserSessionMap sessionMap, String userId){
        return sessionMap.get(userId);
    }

    //获取多个用户Session
    public static IoSession[] getSessions(UserSessionMap sessionMap, String[] userId) {
        IoSession[] sessions = new IoSession[userId.length];
        for(int i = 0; i < userId.length; ++i) {
            sessions[i] = sessionMap.get(userId[i]);
        }
        return sessions;
    }
}