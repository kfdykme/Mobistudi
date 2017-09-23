package cat.mobistudi;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import chinaykc.mobistudi.R;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MyLevel extends AppCompatActivity {

    private LineChartView abilityChart;
    private LineChartView myRankChart;

    String[] date = {"5-23","5-24","5-25","5-26","5-27","5-28","5-29","5-30","5-31","6-1","6-2","6-3","6-4","6-5","6-6","6-7","6-8","6-9","6-10","6-11","6-12","6-13","6-14","6-15","6-16","6-17","6-18","6-19"};//X轴的标注
    double[] score= {1.56,1.59,1.63,1.68,1.72,1.73,1.79,1.74,1.42,1.90,1.74,1.42,1.90,1.50,1.42,1.90,1.33,2.10,1.74,2.22,2.18,1.79,2.20,1.74,2.22,2.18,1.79,2.20};//图表的数据
    private List<PointValue> mPointValuesList = new ArrayList<>();
    private List<AxisValue> mAxisXValuesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_level);

        abilityChart = (LineChartView) findViewById(R.id.abilityChart);
        myRankChart = (LineChartView) findViewById(R.id.myRankChart);

        getAxisXLables();//获取X轴标注
        getAxisPoints();//获取坐标点
        Log.d("测试","折线图");
        initLineChart();
    }

    //初始化LineChart的设置
    private void initLineChart() {
        Line line = new Line(mPointValuesList).setColor(Color.parseColor("#336699")).setCubic(true);//折线的颜色
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状
        //line.setCubic(true);//曲线是否平滑
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setHasLines(true);//是否用直线显示
        line.setHasPoints(true);//是否显示原点
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis();//x轴
        axisX.setHasTiltedLabels(false);//X轴下面坐标轴字体是斜的显示还是直的显示，true是斜的显示
        axisX.setTextColor(Color.WHITE);
        axisX.setTextColor(Color.parseColor("#336699"));//灰色

        axisX.setName("曲线");//表格名称
        axisX.setTextSize(20);//设置字体的大小
        axisX.setMaxLabelChars(9);//最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValuesList);//填充X轴的坐标名称
        data.setAxisXBottom(axisX);//X轴在底部

        Axis axisY = new Axis();//Y轴
        axisY.setName("");//Y轴标注
        axisY.setTextSize(11);
        axisY.setTextColor(Color.parseColor("#336699"));
        data.setAxisYLeft(axisY);//Y轴设置在左边

        abilityChart.setInteractive(true);
        abilityChart.setZoomType(ZoomType.HORIZONTAL);//缩放类型
        abilityChart.setMaxZoom((float)3);//缩放比例
        abilityChart.setLineChartData(data);
        myRankChart.setInteractive(true);
        myRankChart.setZoomType(ZoomType.HORIZONTAL);//缩放类型
        myRankChart.setMaxZoom((float)3);//缩放比例
        myRankChart.setLineChartData(data);
        //abilityChart.setVisibility(View.VISIBLE);

        Viewport v = new Viewport(abilityChart.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        abilityChart.setCurrentViewport(v);
    }

    //图标的每个点的显示
    private void getAxisPoints() {
        for (int i=0;i<score.length;i++){
            mPointValuesList.add(new PointValue(i,(float)score[i]));
        }
    }

    //x轴的显示
    private void getAxisXLables(){
        for (int i=0;i<date.length;i++){
            mAxisXValuesList.add(new AxisValue(i).setLabel(date[i]));
        }
    }

}
