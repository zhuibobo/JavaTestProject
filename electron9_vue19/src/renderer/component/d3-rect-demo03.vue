<template>
    <svg id="d3-rect-demo03" width="960" height="500"></svg>
</template>

<script>
    let d3 = require("d3");

    export default {
        name: "d3-rect-demo03",
        mounted() {
            var svg = d3.select("svg"),
                margin = {top: 20, right: 20, bottom: 30, left: 40},
                width = +svg.attr("width") - margin.left - margin.right,
                height = +svg.attr("height") - margin.top - margin.bottom;

            var x = d3.scaleBand().rangeRound([0, width]).padding(0.1),
                y = d3.scaleLinear().rangeRound([height, 0]);

            var g = svg.append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

            var data = [
                {"letter": "A", "frequency": .08167},
                {"letter": "B", "frequency": .18167},
                {"letter": "C", "frequency": .08167},
                {"letter": "D", "frequency": .08167},
                {"letter": "E", "frequency": .07427},
                {"letter": "F", "frequency": .08167},
                {"letter": "G", "frequency": .08167},
                {"letter": "H", "frequency": .08167},
                {"letter": "I", "frequency": .09167},
                {"letter": "J", "frequency": .08167},
                {"letter": "K", "frequency": .08167},
                {"letter": "L", "frequency": .08167},
                {"letter": "M", "frequency": .08167},
                {"letter": "N", "frequency": .28167},
                {"letter": "O", "frequency": .38167},
                {"letter": "P", "frequency": .58167},
                {"letter": "Q", "frequency": .08167},
                {"letter": "R", "frequency": .08167},
                {"letter": "S", "frequency": .08167},
                {"letter": "T", "frequency": .08167},
                {"letter": "U", "frequency": .08167},
                {"letter": "V", "frequency": .08167},
                {"letter": "W", "frequency": .08167},
                {"letter": "X", "frequency": .10167},
                {"letter": "Y", "frequency": .48167},
                {"letter": "Z", "frequency": .18167}
            ]

            x.domain(data.map(function (d) {
                return d.letter;
            }));
            y.domain([0, d3.max(data, function (d) {
                return d.frequency;
            })]);

            g.append("g")
                .attr("class", "axis d03-axis-x")
                .attr("transform", "translate(0," + height + ")")
                .call(d3.axisBottom(x));

            g.append("g")
                .attr("class", "axis axis--y")
                .call(d3.axisLeft(y).ticks(10, "%"))
                .append("text")
                .attr("transform", "rotate(-90)")
                .attr("y", 6)
                .attr("dy", "0.71em")
                .attr("text-anchor", "end")
                .text("Frequency");

            g.selectAll(".d03-bar")
                .data(data)
                .enter().append("rect")
                .attr("class", "d03-bar")
                .attr("x", function (d) {
                    return x(d.letter);
                })
                .attr("y", function (d) {
                    return y(d.frequency);
                })
                .attr("width", x.bandwidth())
                .attr("height", function (d) {
                    return height - y(d.frequency);
                });
        }
    }
</script>

<style>
    .d03-bar {
        fill: steelblue;
    }

    .d03-bar:hover {
        fill: brown;
    }

    .d03-axis-x path {
        /*display: none;*/
    }
</style>