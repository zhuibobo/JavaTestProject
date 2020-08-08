const HtmlWebpackPlugin = require('html-webpack-plugin');

const configuration = {
    plugins: [],
    entry: {}
};

const addChunk = (entry, renderer, addHtmlFile) => {
    configuration.entry[entry] = [
        "css-hot-loader/hotModuleReplacement",
        `.\\src\\renderer\\${renderer}`
    ];

    if (addHtmlFile) {
        configuration.plugins.push(new HtmlWebpackPlugin({
            "template": "!!html-loader?minimize=false&url=false!dist\\.renderer-index-template.html",
            "filename": `${entry}.html`,
            "hash": false,
            "inject": true,
            "compile": true,
            "favicon": false,
            "minify": false,
            "cache": true,
            "showErrors": true,
            "chunks": ["theme", entry],
            "excludeChunks": [],
            "chunksSortMode": "auto",
            "meta": {},
            "title": `Chunk ${entry}`,
            "xhtml": false,
            "nodeModules": "node_modules"
        }));
    }
}

//addChunk("theme", "theme.ts", false);
addChunk("mainComponentIFrame", "mainComponentIFrame.html", true);

module.exports = configuration;