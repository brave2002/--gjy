<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="/static/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/static/h-ui/js/echarts.js" charset="UTF-8"></script>
</head>


<body>
<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div id="main" style="width: 90%;height:430px;"></div>
</body>

<script type="text/javascript">
    //获取到eharts的对象
    var myEchart = echarts.init(document.getElementById("main"));
    //准备option对象
    option = {
        title: {
          text: '前两名消费喜好'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['邹先锋', '刘延韬']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
            {
                type: 'value'
            }
        ],
        yAxis: [
            {
                type: 'category',
                axisTick: {
                    show: false
                },
                data: ['零食/茶酒/进口食品', '大家电/生活电器', '手机/数码/电脑办公', '腕表/眼镜/珠宝饰品', '男装/运动户外', '美妆/个人护理', '女鞋/男鞋/箱包','女装/大衣']
            }
        ],
        series: [
            {
                name: '邹先锋',
                type: 'bar',
                stack: 'Total',
                label: {
                    show: true
                },
                emphasis: {
                    focus: 'series'
                },
                data: [220, 108, 205, 374, 198, 450, 201,245]
            },
            {
                name: '刘延韬',
                type: 'bar',
                stack: 'Total',
                label: {
                    show: true,
                    position: 'left'
                },
                emphasis: {
                    focus: 'series'
                },
                data: [-80,-132, -301, -401, -204, -230, -320,-111]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myEchart.setOption(option);


</script>



</html>