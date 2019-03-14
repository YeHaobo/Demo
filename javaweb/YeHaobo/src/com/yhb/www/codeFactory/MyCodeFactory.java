package com.yhb.www.codeFactory;

import com.yhb.www.entity.ChatText;
import com.yhb.www.util.Utils;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class MyCodeFactory {

    public static ProtocolCodecFilter getCodeFactory() {
        TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue());
        textLineCodecFactory.setDecoderMaxLineLength(1024000);
        textLineCodecFactory.setEncoderMaxLineLength(1024000);
        return new ProtocolCodecFilter(textLineCodecFactory);
    }

    public static ChatText myDecode(Object message) {
        try {
            String data = URLDecoder.decode(message.toString(), "UTF-8").trim();
            Utils.logger.info(data);
            return Utils.json2chatText(data);
        } catch (UnsupportedEncodingException var4) {
            Utils.logger.error("消息解码转换错误，消息为：" + message);
            return null;
        }
    }

    public static String myEncode(ChatText chatText) {
        String datas = Utils.chatText2json(chatText).trim();
        String code = "";
        try {
            code = URLEncoder.encode(datas, "UTF-8") + "\n";
            return code;
        } catch (UnsupportedEncodingException var4) {
            Utils.logger.error("消息编码错误" + datas);
            return null;
        }
    }

}