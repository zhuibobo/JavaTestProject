<template>
    <svg id="d3-rect-demo02" width="960" height="500"></svg>
</template>

<script>
    let d3 = require("d3");

    export default {
        name: "d3-rect-demo02",
        mounted() {
            console.log(d3);

            let svg = d3.select("#d3-rect-demo02"),
                margin = {top: 20, right: 20, bottom: 30, left: 60},
                width = +svg.attr("width") - margin.left - margin.right,
                height = +svg.attr("height") - margin.top - margin.bottom;

            let dataset = [
                {
                    year:"2013",
                    value:50
                },
                {
                    year:"2014",
                    value:350
                },
                {
                    year:"2015",
                    value:250
                },
                {
                    year:"2016",
                    value:250
                },
                {
                    year:"2017",
                    value:60
                },
                {
                    year:"2018",
                    value:250
                },
                {
                    year:"2019",
                    value:20
                },
                {
                    year:"2020",
                    value:250
                },
                {
                    year:"2021",
                    value:250
                },
                {
                    year:"2022",
                    value:250
                },
                {
                    year:"2023",
                    value:250
                },
                {
                    year:"2024",
                    value:250
                },
                {
                    year:"2025",
                    value:250
                }
            ];

            let xScale=d3.scaleBand().domain(dataset.map(function (d) {
                return d.year;
            })).rangeRound([0, width]).padding(0.1);

            let yScale=d3.scaleLinear().domain([0,d3.max(dataset,function (d) {
                return d.value;
            })]).rangeRound([height, 0]);

            console.log(yScale.domain());
            console.log(yScale.range());

            let g = svg.append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

            g.selectAll(".d02-bar")
                .data(dataset)
                .enter().append("rect")
                .attr("class", "d02-bar")
                .attr("x", function (d) {
                    return xScale(d.year);
                })
                .attr("y", function (d) {
                    return yScale(d.value);
                })
                .attr("width", xScale.bandwidth())
                .attr("height", function (d) {
                    return height - yScale(d.value);
                });

            g.append("g")
                .attr("class", "axis axis--x")
                .attr("transform", "translate(0," + (height+10) + ")")
                .call(d3.axisBottom(xScale));

            console.log(d3.axisLeft(yScale).ticks(10));

            g.append("g")
                .attr("class", "axis axis--y")
                .call(d3.axisLeft(yScale).ticks(5));

            //var rectPadding = 4;
            //添加文字元素
            var texts = svg.selectAll(".d02-bar-my-text")
                .data(dataset)
                .enter()
                .append("text")
                .attr("class","d02-bar-my-text")
                .attr("transform","translate(" + margin.left + "," + margin.top + ")")
                .attr("x", function(d,i){
                    //console.log(xScale(d.year));
                    return xScale(d.year);
                } )
                .attr("y",function(d){
                    return yScale(d.value);
                })
                .attr("dx",function(){
                    return xScale.bandwidth()/2;
                })
                .attr("dy",function(d){
                    return 20;
                })
                .text(function(d){
                    return d.value;
                });
        }
    }
</script>

<style>
    .d02-bar {
        fill: steelblue;
    }

    .d02-bar:hover {
        fill: #6cc096;
    }

    .d02-bar-my-text{
        fill: aliceblue;
        text-anchor: middle;
    }
</style>