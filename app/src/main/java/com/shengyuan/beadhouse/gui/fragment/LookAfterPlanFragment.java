package com.shengyuan.beadhouse.gui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ldf.calendar.Utils;
import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.adapter.ScheduleAdapter;
import com.shengyuan.beadhouse.gui.view.CustomDayView;
import com.shengyuan.beadhouse.model.ScheduleBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 照护计划Fragment
 * Created by dell on 2017/11/5.
 */

public class LookAfterPlanFragment extends BaseFragment implements View.OnClickListener {

    TextView textViewYearDisplay;
    TextView textViewMonthDisplay;
    MonthPager monthPager;

    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    /**
     * 日期Adapter
     */
    private CalendarViewAdapter calendarAdapter;
    private int mCurrentPage = MonthPager.CURRENT_DAY_INDEX;

    /**
     * 当前选中的日期对象
     */
    private CalendarDate currentDate;


    /**
     * 每天的日程列表控件
     */
    private RecyclerView scheduleRecyclerView;
    /**
     * 每天的日程adapter
     */
    private ScheduleAdapter scheduleAdapter;
    /**
     * 每天的日程数据列表
     */
    private List<ScheduleBean> scheduleBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_look_after_plan;
    }


    @Override
    protected void initView(View rootView) {

        scheduleBeanList = new ArrayList<>();
        // 为header占位
        scheduleBeanList.add(new ScheduleBean());
        //默认当天的日程数据
        for (int i = 0; i < 3; i++) {
            ScheduleBean bean = new ScheduleBean();
            bean.id = i;
            bean.name = "服务名称(" + i + ")";
            bean.beginTime = "09:20";
            bean.endTime = "15:30";
            scheduleBeanList.add(bean);
        }

        monthPager = rootView.findViewById(R.id.calendar_view);
        //此处强行setViewHeight，毕竟你知道你的日历牌的高度
        monthPager.setViewheight(Utils.dpi2px(getActivity(), 270));
        textViewYearDisplay = rootView.findViewById(R.id.show_year_view);
        textViewMonthDisplay = rootView.findViewById(R.id.show_month_view);

        // 下一月按钮
        rootView.findViewById(R.id.look_after_fragment_next_month).setOnClickListener(this);
        // 上一月按钮
        rootView.findViewById(R.id.look_after_fragment_last_month).setOnClickListener(this);

        scheduleRecyclerView = rootView.findViewById(R.id.look_after_fragment_schedule_recycler_view);
        scheduleRecyclerView.setHasFixedSize(true);
        scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        scheduleAdapter = new ScheduleAdapter(getActivity(), scheduleBeanList);
        scheduleAdapter.setmHeaderCount(1);

        scheduleRecyclerView.setAdapter(scheduleAdapter);
        initCurrentDate();
        initCalendarView();

        showCenterView();
    }


    /**
     * 初始化currentDate
     *
     * @return void
     */
    private void initCurrentDate() {
        currentDate = new CalendarDate();
        textViewYearDisplay.setText(currentDate.getYear() + "年");
        textViewMonthDisplay.setText(currentDate.getMonth() + "");
    }

    /**
     * 初始化CustomDayView，并作为CalendarViewAdapter的参数传入
     *
     * @return void
     */
    private void initCalendarView() {
        CustomDayView customDayView = new CustomDayView(getActivity(), R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(
                getActivity(),
                onSelectDateListener,
                CalendarAttr.CalendayType.MONTH,
                customDayView);
        initMarkData();
        initMonthPager();
    }


    /**
     * 初始化标记数据，HashMap的形式，可自定义
     *
     * @return void
     */
    private void initMarkData() {
        HashMap<String, String> markData = new HashMap<>();
        markData.put("2017-8-9", "1");
        markData.put("2017-7-9", "0");
        markData.put("2017-6-9", "1");
        markData.put("2017-6-10", "0");
        calendarAdapter.setMarkData(markData);
    }


    /**
     * 日历控件日期选择监听器
     */
    private OnSelectDateListener onSelectDateListener = new OnSelectDateListener() {
        @Override
        public void onSelectDate(CalendarDate date) {
            refreshClickDate(date);
        }

        @Override
        public void onSelectOtherMonth(int offset) {
            //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
            monthPager.selectOtherMonth(offset);
        }
    };


    private void refreshClickDate(CalendarDate date) {
        currentDate = date;
        textViewYearDisplay.setText(date.getYear() + "年");
        textViewMonthDisplay.setText(date.getMonth() + "");

        List<ScheduleBean> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ScheduleBean bean = new ScheduleBean();
            bean.id = i;
            bean.name = date.getDay() + "号的服务名称(" + i + ")";
            bean.beginTime = "09:20";
            bean.endTime = "15:30";
            list.add(bean);
        }
        scheduleBeanList.clear();
        // 为header占位
        scheduleBeanList.add(new ScheduleBean());
        scheduleBeanList.addAll(list);
        scheduleAdapter.notifyDataSetChanged();
    }


    /**
     * 初始化monthPager，MonthPager继承自ViewPager
     *
     * @return void
     */
    private void initMonthPager() {
        monthPager.setAdapter(calendarAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) instanceof Calendar) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    textViewYearDisplay.setText(date.getYear() + "年");
                    textViewMonthDisplay.setText(date.getMonth() + "");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.look_after_fragment_next_month:
                // 下一月
                monthPager.setCurrentItem(monthPager.getCurrentPosition() + 1);
                break;
            case R.id.look_after_fragment_last_month:
                // 上一月
                monthPager.setCurrentItem(monthPager.getCurrentPosition() - 1);
                break;
        }
    }


    //    /**
//     * onWindowFocusChanged回调时，将当前月的种子日期修改为今天
//     *
//     * @return void
//     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && !initiated) {
//            refreshMonthPager();
//            initiated = true;
//        }
//    }

//    private void refreshMonthPager() {
//        CalendarDate today = new CalendarDate();
//        calendarAdapter.notifyDataChanged(today);
//        textViewYearDisplay.setText(today.getYear() + "年");
//        textViewMonthDisplay.setText(today.getMonth() + "");
//    }

//    public void onClickBackToDayBtn() {
//        refreshMonthPager();
//    }

//    private void refreshSelectBackground() {
//        ThemeDayView themeDayView = new ThemeDayView(context, R.layout.custom_day_focus);
//        calendarAdapter.setCustomDayRenderer(themeDayView);
//        calendarAdapter.notifyDataSetChanged();
//        calendarAdapter.notifyDataChanged(new CalendarDate());
//    }


}
