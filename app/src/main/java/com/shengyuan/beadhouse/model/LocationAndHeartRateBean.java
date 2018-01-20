package com.shengyuan.beadhouse.model;

/**
 * 位置和心率信息bean
 * Created by dell on 2018/1/20.
 */

public class LocationAndHeartRateBean {

    /**
     * position : {"lat":"22.55724716","lon":"113.93560028"}
     * heart_rate : 84
     */

    private PositionBean position;
    private String heart_rate;

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public static class PositionBean {
        /**
         * lat : 22.55724716
         * lon : 113.93560028
         */

        private String lat;
        private String lon;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }
}
