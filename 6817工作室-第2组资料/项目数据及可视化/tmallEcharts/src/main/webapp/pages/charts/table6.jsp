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
    $.ajax({
        type: "GET",
        url: "/charts/getChargeData6",
        dataType: 'json',
        success: function(data) {
            //准备option对象
            option = {
                title: {
                    text:'商品类型分布',
                    left: 'center'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: { show: true },
                        dataView: { show: true, readOnly: false },
                        restore: { show: true },
                        saveAsImage: { show: true }
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{b}:{c}<br>百分比：{d}%'
                },
                series: [
                    {
                        name: 'Nightingale Chart',
                        type: 'pie',
                        radius: [30, 160],
                        roseType: 'area',
                        itemStyle: {
                            borderRadius: 8
                        },
                        data:data
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myEchart.setOption(option);
        }
    })

</script>



</html>