package com.shengyuan.beadhouse.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by llj on 2017/11/14.
 */

public class RoomInfoBean2 {
    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/kwJyArP85SF8sTsCXPd5DrBrc1509971467902.jpg","resGrade":2,"planCount":45,"joinCount":10,"organizer":1,"id":119,"beginTime":"2017-11-09 20:31:18","endTime":"2019-02-22 20:31:20","title":"成语接龙","resKind":1,"thumbUpCount":null,"orgId":1},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/yZzcT2HnYy2fCEmYYpbJxnMGk1510022123050.gif","resGrade":1,"planCount":20,"joinCount":1,"organizer":90,"id":120,"beginTime":"2017-11-07 10:36:50","endTime":"2019-11-07 10:36:52","title":"唱歌比赛","resKind":1,"thumbUpCount":null,"orgId":1},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sm44hr3fKtN44SnREBCNxnf4B1510033486291.jpg","resGrade":1,"planCount":20,"joinCount":0,"organizer":90,"id":121,"beginTime":"2017-11-07 10:36:50","endTime":"2019-11-07 10:36:52","title":"游泳比赛","resKind":1,"thumbUpCount":null,"orgId":1},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/yZzcT2HnYy2fCEmYYpbJxnMGk1510022123050.gif","resGrade":1,"planCount":20,"joinCount":0,"organizer":90,"id":124,"beginTime":"2017-11-07 10:36:50","endTime":"2019-11-07 10:36:52","title":"朗诵比赛","resKind":1,"thumbUpCount":null,"orgId":1},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/yZzcT2HnYy2fCEmYYpbJxnMGk1510022123050.gif","resGrade":1,"planCount":20,"joinCount":0,"organizer":90,"id":130,"beginTime":"2017-11-07 10:36:50","endTime":"2019-11-07 10:36:52","title":"演讲比赛","resKind":1,"thumbUpCount":null,"orgId":1}]
     * recordTotal : 5
     */

    private int pageTotal;
    private int pageIndex;
    private int recordTotal;
    private List<RecordsBean> records;

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/kwJyArP85SF8sTsCXPd5DrBrc1509971467902.jpg
         * resGrade : 2
         * planCount : 45
         * joinCount : 10
         * organizer : 1
         * id : 119
         * beginTime : 2017-11-09 20:31:18
         * endTime : 2019-02-22 20:31:20
         * title : 成语接龙
         * resKind : 1
         * thumbUpCount : null
         * orgId : 1
         */

        private String coverUrl;
        private int resGrade;
        private int planCount;
        private int joinCount;
        private int organizer;
        private int id;
        private String beginTime;
        private String endTime;
        private String title;
        private int resKind;
        private Object thumbUpCount;
        private int orgId;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getResGrade() {
            return resGrade;
        }

        public void setResGrade(int resGrade) {
            this.resGrade = resGrade;
        }

        public int getPlanCount() {
            return planCount;
        }

        public void setPlanCount(int planCount) {
            this.planCount = planCount;
        }

        public int getJoinCount() {
            return joinCount;
        }

        public void setJoinCount(int joinCount) {
            this.joinCount = joinCount;
        }

        public int getOrganizer() {
            return organizer;
        }

        public void setOrganizer(int organizer) {
            this.organizer = organizer;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getResKind() {
            return resKind;
        }

        public void setResKind(int resKind) {
            this.resKind = resKind;
        }

        public Object getThumbUpCount() {
            return thumbUpCount;
        }

        public void setThumbUpCount(Object thumbUpCount) {
            this.thumbUpCount = thumbUpCount;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }
    }
}
