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
        url: "/charts/getChargeData1",
        dataType: 'json',
        success: function(data) {
            //准备option对象
            var option = {
                //标题
                title: {
                    text: '疯狂购物前十人'
                },
                //提示框组件  默认值
                tooltip: {
                    //formatter: '{a}<br>{b}:{c}'
                },

                //工具栏
                toolbox: {
                    feature: {
                        saveAsImage: {},
                        dataView: {},
                        dataZoom: {},
                        magicType: {
                            type: ['line', 'bar', 'stack']
                        },
                        restore: {}
                    }
                },
                //图例组件
                legend: {
                    data: ['购买次数'],
                    icon: 'circle'
                },
                //x轴最表
                xAxis: {
                    data: data.names
                },
                //y轴坐标   不建议写  利用series的data值去自动填充
                yAxis: {},
                //核心配置
                series: [{
                    type: 'bar',
                    data: data.values,
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
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myEchart.setOption(option);
        }
    })

</script>



</html>