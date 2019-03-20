package com.yhb.example.myeventbus.message;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * EventBus消息类
 */
public class MessageEvent {
    /**
     * 字符串消息
     */
    public class StringMessage{
        private String message;

        public StringMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * BitMap图片消息
     */
    public class BitMapMessage{
        private Bitmap bitmap;

        public BitMapMessage(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }

    /**
     * Drawable图片消息
     */
    public class DrawableMessage{
        private Drawable drawable;

        public DrawableMessage(Drawable drawable) {
            this.drawable = drawable;
        }

        public Drawable getDrawable() {
            return drawable;
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }
    }
}
