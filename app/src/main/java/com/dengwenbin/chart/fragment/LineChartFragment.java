package com.dengwenbin.chart.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dengwenbin.chart.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 40284 on 2017/4/16.
 */

public class LineChartFragment extends Fragment {

    private LineChart mLineChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View lineChartView = inflater.inflate(R.layout.layout_line_chart, container, false);
        mLineChart = (LineChart) lineChartView.findViewById(R.id.lineChart);
        lineCharConfig("ceshi");
        axisConfig();
        legendConfig();
        List<Float> list = new ArrayList<>();
        list.add(5f);
        list.add(12f);
        list.add(4f);
        list.add(6f);
        list.add(1f);
        list.add(5f);
        list.add(8f);
        list.add(12f);
        setData(list);
        mLineChart.invalidate();
        return lineChartView;
    }

    public void lineCharConfig(String name) {
        Description mDescription = new Description();
        mDescription.setText("");
        mLineChart.setDescription(mDescription);
        mLineChart.setNoDataText("暂无数据");
        mLineChart.setDrawGridBackground(false);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
    }

    public void legendConfig(){
        Legend mLegend = mLineChart.getLegend();
        mLegend.setForm(Legend.LegendForm.LINE);
        mLegend.setFormSize(15f);
        mLegend.setTextColor(Color.BLACK);
    }

    public void axisConfig(){
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxis = mLineChart.getAxisLeft();
        yAxis.setEnabled(true);
        yAxis.enableGridDashedLine(10f, 10f, 10f);
        yAxis.setDrawZeroLine(false);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.animateY(5000);
    }

    public void setData(List<Float> yList){
        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < yList.size(); i++){
            yValues.add(new Entry(i, yList.get(i)));
        }
        LineDataSet mLineDataSet;

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            mLineDataSet = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            mLineDataSet.setValues(yValues);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            mLineDataSet = new LineDataSet(yValues, "ceshi");
            mLineDataSet.setDrawValues(true);
            mLineDataSet.setLineWidth(2f);
            mLineDataSet.setCircleRadius(4f);

            //设置点击高亮与颜色
            mLineDataSet.setHighlightEnabled(true);
            mLineDataSet.setHighLightColor(Color.RED);
            mLineDataSet.setValueTextColor(Color.GREEN);


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(mLineDataSet);
            LineData data = new LineData(dataSets);
            mLineChart.setData(data);
        }
    }
}
