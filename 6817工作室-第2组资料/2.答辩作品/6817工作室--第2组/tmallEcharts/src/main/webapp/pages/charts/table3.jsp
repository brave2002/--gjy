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
    var option = {
        title: {
            text: '男女每天消费比较'
        },
        tooltip: {
            trigger: 'axis', axisPointer: {
                type: 'shadow'
            }
        },
        legend: {},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        xAxis: {
            type: 'category',
            data: ['2022-10-13', '2022-10-14', '2022-10-15', '2022-10-16', '2022-10-17', '2022-10-18','2022-10-19']
        },
        series: [
            {
                name: '男',
                type: 'line' ,
                smooth: true,
                data: [28469, 35407, 40181, 30725, 57660, 36796,22005],
                markLine: {
                    data: [{
                        name: '平均线',
                        type: 'average'
                    }]
                },
                markPoint: {
                    data: [{
                        name: '最大值',
                        type: 'max'
                    }, {
                        name: '最小值',
                        type: 'min'
                    }]
                }
            },

            {
                name: '女',
                type: 'line',
                smooth: true,
                data: [39100, 24756, 49000, 21594, 34141, 61807,46456],
                markLine: {
                    data: [{
                        name: '平均线',
                        type: 'average'
                    }]
                },
                markPoint: {
                    data: [{
                        name: '最大值',
                        type: 'max'
                    }, {
                        name: '最小值',
                        type: 'min'
                    }]
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myEchart.setOption(option);


</script>



</html>