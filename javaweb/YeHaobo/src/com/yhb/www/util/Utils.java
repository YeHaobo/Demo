package com.yhb.www.util;

import com.alibaba.fastjson.JSON;
import com.yhb.www.entity.ChatText;
import com.yhb.www.chat.server.Server;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.UUID;

/**
 * 工具类
 */
public class Utils {

    //日志打印工具
    public static final Logger logger = Logger.getLogger(Server.class);

    //得到当前时间
    public static String getMyTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    //获得时间戳
    public static long getTimeMillis(){
        return System.currentTimeMillis();
    }

    //得到唯一标识
    public static String[] getUUID(int number){
        String[] uuids=new String[number];
        for(int i=0;i < number;i++){
            uuids[i] = UUID.randomUUID().toString().replaceAll("-","");
        }
        return uuids;
    }

    public static String chatText2json(ChatText chatText) {
        return JSON.toJSONString(chatText);
    }

    public static ChatText json2chatText(String data) {
        ChatText chatText = JSON.parseObject(data, ChatText.class);
        if (chatText != null) {
            return chatText;
        } else {
            logger.error("json转换异常：" + data);
            return new ChatText();
        }
    }

}
