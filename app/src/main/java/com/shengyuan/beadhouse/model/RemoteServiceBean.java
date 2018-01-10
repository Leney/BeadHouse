package com.shengyuan.beadhouse.model;

/**
 * 远程看护bean
 * Created by dell on 2018/1/10.
 */

public class RemoteServiceBean {

    /**
     * m3u8_url : http://hls.kan1.live.anyan.com/live_60875_103875/m3u8?sign=1515600379-ab8019e059e0042c0a10c41330a3a5a1&device_sn=Ay0000000000002107UM&video_rate=700&channel_id=1&customer=60875_103875
     * pic_path : http://c2-hd-img.upy-img.static.ulucu.com/Ay0000000000002107UM_0_1515463199_4.jpg
     */

    private String m3u8_url;
    private String pic_path;

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }
}
