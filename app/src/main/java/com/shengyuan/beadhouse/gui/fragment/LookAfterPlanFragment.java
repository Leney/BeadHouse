package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.view.DateTableView;

import java.util.ArrayList;
import java.util.List;

/**
 * 照护计划Fragment
 * Created by dell on 2017/11/5.
 */

public class LookAfterPlanFragment extends BaseFragment {
    private DateTableView dateTableView;
    private String dateToday;
    //现在的年月
    private String nowYearMonth;

    private List<List<String>> showString = new ArrayList<>();

    private String tomorrow;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_look_after_plan;
    }

    @Override
    protected void initView(View rootView) {
        dateTableView = rootView.findViewById(R.id.date_tab);
        dateTableView.addData("2017-11");

//        init();

        showCenterView();
    }

    private void init(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        Date date = Calendar.getInstance().getTime();
//        nowYearMonth = sdf.format(date);
//        dateToday = sdf2.format(date);

//        try {
//            showString.add(getGroupShow(dateToday));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Logger.e("日期转换字符失败");
//        }

//        Calendar calendar = Calendar.getInstance();
//        int day = date.getDate();
//        Logger.e("day----"+day);
//        calendar.set(Calendar.DAY_OF_MONTH,day+1);
//        Date date2 = calendar.getTime();
//        tomorrow = sdf2.format(date2);
//        Logger.e("tomorrow----"+ tomorrow);

//        dateTableView.addData("2017-10");
    }

//    private DateTableView.ItemClickListener DateTableItemClick = new DateTableView.ItemClickListener() {
//        @Override
//        public void clickDate(String clickDate) {
//            if (clickDate.equals(dateToday)) {
////                presenter.getEventSchedule(clickDate,true,false);
//            }  else if (clickDate.equals(dateToday)) {
////                presenter.getEventSchedule(clickDate,false,true);
//            } else {
////                presenter.getEventSchedule(clickDate,false,false);
//            }
////            firstRequest = false;
//            showString.clear();
//            try {
//                showString.add(getGroupShow(clickDate));
//            } catch (ParseException e) {
//                e.printStackTrace();
//                Logger.e("日期转换字符失败");
//            }
//        }
//    };

//    private List<String> getGroupShow(String dateStr) throws ParseException {
//        List<String> stringList = new ArrayList<>();
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        Date date =sdf.parse(dateStr);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        int month = calendar.get(Calendar.MONTH) + 1;
//        Logger.e("month----"+month);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        Logger.e("day----"+day);
//        int weeknum = calendar.get(Calendar.DAY_OF_WEEK);
//        String weekDay = weekStr(weeknum);
//
//        //几月
//        stringList.add(String.valueOf(month));
//        //几日
//        stringList.add(String.valueOf(day));
//        //星期几
//        stringList.add(weekDay);
//        return stringList;
//    }

//    private String weekStr(int day_of_week) {
//        if (day_of_week == 1) {
//            return "星期天";
//        } else if (day_of_week == 2) {
//            return "星期一";
//        } else if (day_of_week == 3) {
//            return "星期二";
//        } else if (day_of_week == 4) {
//            return "星期三";
//        } else if (day_of_week == 5) {
//            return "星期四";
//        } else if (day_of_week == 6) {
//            return "星期五";
//        } else if (day_of_week == 7) {
//            return "星期六";
//        }
//        return "";
//    }
}
