package com.example.swimminggo.view.swimmer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Time;
import com.example.swimminggo.presenter.ChartPresenter;
import com.example.swimminggo.presenter.presenterImpl.ChartPresenterImpl;
import com.example.swimminggo.singleton.CurrentDistance;
import com.example.swimminggo.singleton.CurrentStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class SwimmerQuarterChartFragment extends Fragment {
    Spinner spnQuarter, spnYear;
    Button btnViewChart;
    LineChartView lineChartView;
    ChartPresenter chartPresenter;
    View view;
    int swimmerId;

    public SwimmerQuarterChartFragment(int swimmerId){
        this.swimmerId = swimmerId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_swimmer_quarter_chart, container, false);
        initComponent();
        initDatabase();
        action();
        return view;
    }


    private void initDatabase() {
        ArrayList<String> months = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        spnQuarter.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, months));
        ArrayList<String> years = new ArrayList<>();
        for (int i = 2025; i >= 2000; i--)
            years.add(i + "");

        spnYear.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, years));
    }

    private void initComponent() {
        chartPresenter = new ChartPresenterImpl(this);
        spnQuarter = view.findViewById(R.id.spn_quarter);
        spnYear = view.findViewById(R.id.spn_year);
        btnViewChart = view.findViewById(R.id.btn_view_chart);
        lineChartView = view.findViewById(R.id.lineChart);
    }

    private void action() {
        btnViewChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartPresenter.onGetDataByQuarter(CurrentStyle.getInstance().getStyle().getId(),
                        CurrentDistance.getInstance().getDistance().getId(),
                        swimmerId,
                        Integer.parseInt(spnQuarter.getSelectedItem().toString()),
                        Integer.parseInt(spnYear.getSelectedItem().toString()));
            }
        });
    }

    public void setupLineChart(List<String> axisData, List<Float> yAxisData) {
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues);
        for (int i = 0; i < axisData.size(); i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData.get(i)));
        }

        for (int i = 0; i < yAxisData.size(); i++) {
            yAxisValues.add(new PointValue(i, yAxisData.get(i)));
        }
        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(12);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(12);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = maxTime(CurrentDistance.getInstance().getDistance().getValue());
        viewport.bottom = 0;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
    }

    private float maxTime(int distance){
        switch (distance){
            case 50 : return new Time(1).toSecond();
            case 100 : return new Time(2).toSecond();
            case 200 : return new Time(3).toSecond();
            case 400 : return new Time(4).toSecond();
            case 800 : return new Time(10).toSecond();
            case 1000 : return new Time(15).toSecond();
            case 1500 : return new Time(20).toSecond();
        }
        return new Time(30).toSecond();
    }
}
