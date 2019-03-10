package minatest.example.com.minatest_android.json;

import com.alibaba.fastjson.JSON;
import minatest.example.com.minatest_android.entiti.ChatText;

/**
 *  将JSON数据与类的转换
 */
public class MyJson {
    public static String chatText2json(ChatText chatText) {
        return JSON.toJSONString(chatText);
    }

    public static ChatText json2chatText(String data) {
        ChatText chatText = JSON.parseObject(data, ChatText.class);
        if (chatText != null) {
            return chatText;
        } else {
            return new ChatText();
        }
    }
}
