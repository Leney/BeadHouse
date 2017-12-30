package com.shengyuan.beadhouse.model;

import java.util.List;

/**
 * 获取老人生理数据返回bean
 * Created by dell on 2017/12/30.
 */

public class PhysicBean {

    private List<PhysicsBean> Physics;

    public List<PhysicsBean> getPhysics() {
        return Physics;
    }

    public void setPhysics(List<PhysicsBean> Physics) {
        this.Physics = Physics;
    }

    public static class PhysicsBean {
        /**
         * ID_number : 350303199307132114
         * blood_fat : 700
         * blood_pressure : 500
         * blood_sugar : 600
         * core : 100
         * date : 2017-02-02
         * daytime : 08:00
         * heart_rate : 128333....
         * temperature : 35.5度
         * weight : 123kg
         */

        private String ID_number;
        private String blood_fat;
        private String blood_pressure;
        private String blood_sugar;
        private String core;
        private String date;
        private String daytime;
        private String heart_rate;
        private String temperature;
        private String weight;

        public String getID_number() {
            return ID_number;
        }

        public void setID_number(String ID_number) {
            this.ID_number = ID_number;
        }

        public String getBlood_fat() {
            return blood_fat;
        }

        public void setBlood_fat(String blood_fat) {
            this.blood_fat = blood_fat;
        }

        public String getBlood_pressure() {
            return blood_pressure;
        }

        public void setBlood_pressure(String blood_pressure) {
            this.blood_pressure = blood_pressure;
        }

        public String getBlood_sugar() {
            return blood_sugar;
        }

        public void setBlood_sugar(String blood_sugar) {
            this.blood_sugar = blood_sugar;
        }

        public String getCore() {
            return core;
        }

        public void setCore(String core) {
            this.core = core;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDaytime() {
            return daytime;
        }

        public void setDaytime(String daytime) {
            this.daytime = daytime;
        }

        public String getHeart_rate() {
            return heart_rate;
        }

        public void setHeart_rate(String heart_rate) {
            this.heart_rate = heart_rate;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
