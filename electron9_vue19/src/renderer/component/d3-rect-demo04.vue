<template>
    <svg id="d3-rect-demo04" width="960" height="500"></svg>
</template>

<script>
    let d3 = require("d3");

    export default {
        name: "d3-rect-demo04",
        mounted() {
            var models = [
                {
                    "model_name":"f1",
                    "field1":19,
                    "field2":83
                },
                {
                    "model_name":"f2",
                    "field1":67,
                    "field2":93
                },
                {
                    "model_name":"f3",
                    "field1":10,
                    "field2":56
                },
            ];

            var container = d3.select('#d3-rect-demo04'),
                width = 520,
                height = 220,
                margin = {top: 30, right: 20, bottom: 30, left: 50},
                barPadding = .2,
                axisTicks = {qty: 5, outerSize: 20, dateFormat: '%m-%d'};
            var svg = container
                .append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", `translate(${margin.left},${margin.top})`);

            var xScale0 = d3.scaleBand().domain(models.map(d => d.model_name)).range([0, width - margin.left - margin.right]).padding(barPadding)
            var xScale1 = d3.scaleBand().domain(['field1', 'field2']).range([0, xScale0.bandwidth()])
            var yScale = d3.scaleLinear().range([height - margin.top - margin.bottom, 0])

            var xAxis = d3.axisBottom(xScale0).tickSizeOuter(axisTicks.outerSize);
            var yAxis = d3.axisLeft(yScale).ticks(axisTicks.qty).tickSizeOuter(axisTicks.outerSize);

            yScale.domain([0, d3.max(models, d => d.field1 > d.field2 ? d.field1 : d.field2)])

            var model_name = svg.selectAll(".model_name")
                .data(models)
                .enter().append("g")
                .attr("class", "model_name")
                .attr("transform", d => `translate(${xScale0(d.model_name)},0)`);

            console.log(model_name);
            /* Add field1 bars */
            model_name.selectAll(".bar.field1")
                .data(d => [d])
                //.data(function(d){
                //console.log([d]);
                //    return [d];
                //})
                //.data(models)
                //.data(d => [d])
                .join("rect")
                .attr("class", "bar field1")
                .style("fill","blue")
                .attr("x", d => xScale1('field1'))
                .attr("y", d => { console.log(d);console.log(d.field1) ;return  yScale(d.field1)})
                .attr("width", xScale1.bandwidth())
                .attr("height", d => {
                    return height - margin.top - margin.bottom - yScale(d.field1)
                });

            //console.log(model_name.selectAll(".bar.field12").data(d=>[d]));


            /* Add field2 bars */
            model_name.selectAll(".bar.field2")
                .data(d => [d])
                .join("rect")
                .attr("class", "bar field2")
                .style("fill","red")
                .attr("x", d => xScale1('field2'))
                .attr("y", d => yScale(d.field2))
                .attr("width", xScale1.bandwidth())
                .attr("height", d => {
                    return height - margin.top - margin.bottom - yScale(d.field2)
                });

            // Add the X Axis
            svg.append("g")
                .attr("class", "x axis")
                .attr("transform", `translate(0,${height - margin.top-margin.bottom})`)
                .call(xAxis);
            // Add the Y Axis
            svg.append("g")
                .attr("class", "y axis")
                .call(yAxis);
        }
    }
</script>

<style>

</style>