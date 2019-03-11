package minatest.example.com.minatest_android.socket;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import minatest.example.com.minatest_android.json.MyJson;
import minatest.example.com.minatest_android.entiti.ChatText;

/**
 * 会话信息编码解码
 */
public class ChatCodeFactory {
    public static ProtocolCodecFilter getCodeFactory() {
        TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue());
        textLineCodecFactory.setDecoderMaxLineLength(1024000);
        textLineCodecFactory.setEncoderMaxLineLength(1024000);
        return new ProtocolCodecFilter(textLineCodecFactory);
    }

    public static ChatText myDecode(Object message) {
        try {
            String data = URLDecoder.decode(message.toString(), "UTF-8").trim();
            return MyJson.json2chatText(data);
        } catch (UnsupportedEncodingException var4) {
            return null;
        }
    }

    public static String myEncode(ChatText chatText) {
        String datas = MyJson.chatText2json(chatText).trim();
        String code = "";
        try {
            code = URLEncoder.encode(datas, "UTF-8") + "\n";
            return code;
        } catch (UnsupportedEncodingException var4) {
            return null;
        }
    }

}
