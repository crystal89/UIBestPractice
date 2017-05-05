package com.crystal.hq.uibestpractice;

/**
 * Created by Crystal on 2017/5/5.
 */

public class Msg {
    //标记接收一条消息
    public static final int TYPE_RECIVED = 0;
    //标记发送一条消息
    public static final int TYPE_SEND = 1;

    private String _content;
    private int _type;

    public Msg(String content, int type) {
        _content = content;
        _type = type;
    }

    public String getContent() {
        return _content;
    }

    public int getType() {
        return _type;
    }
}
