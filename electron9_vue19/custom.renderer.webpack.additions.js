const HtmlWebpackPlugin = require('html-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const path=require("path");
const devMode = process.env.NODE_ENV === 'development';
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = function(config) {

    //console.log(JSON.stringify(config, null, 2));

    const stylePlugins = config.plugins.filter(plugin => {
        if(plugin.options){
            if(plugin.options.filename=="styles.css"){
                return true;
            }
        }
        return false;
    })
    //console.log(stylePlugins);
    stylePlugins[0].options.filename="[name]styles.css";

    const htmlPlugins = config.plugins.filter(plugin => {
        if(plugin.options){
            if(plugin.options.filename=="index.html"){
                return true;
            }
        }
        return false;
    })
    console.log(htmlPlugins);
    htmlPlugins[0].options.chunks=['renderer'];

    config.entry.mainComponentWebView="./src/renderer/webview/main-component-webview/mainComponentWebView.js";
    config.plugins.push(
        new HtmlWebpackPlugin({
            filename: 'webview/main-component-webview/mainComponentWebView.html',
            template: './src/renderer/webview/main-component-webview/mainComponentWebView.html',
            chunks: ['mainComponentWebView']
        })
    );

    console.log(JSON.stringify(config, null, 2));
    return config
}

/*module.exports = {
    mode: 'development',
    entry:{
        index:"./src/renderer/index.js",
        mainComponentIFrame:"./src/renderer/iframe/main-component-iframe/mainComponentIFrame.js"
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].[hash:8].js',
        sourceMapFilename: '[name].[hash:8].map',
        chunkFilename: '[id].[hash:8].js'
    },
    module: {
    },
    plugins: [
        // 请确保引入这个插件来施展魔法
        //new VueLoaderPlugin()
        new MiniCssExtractPlugin({
            // Options similar to the same options in webpackOptions.output
            // both options are optional
            filename: '[name].css',
            chunkFilename: '[id].css',
        }),
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: 'src/renderer/index.html',
            chunks: ['index'],
            hash:true
        }),
        /!*new HtmlWebpackPlugin({
            filename: 'iframe/main-component-iframe/mainComponentIFrame.html',
            template: './src/renderer/iframe/main-component-iframe/mainComponentIFrame.html',
            chunks: ['mainComponentIFrame']
        })*!/
    ]
}*/

