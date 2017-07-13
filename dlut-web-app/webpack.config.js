const path = require('path');
const webpack = require('webpack');
const htmlWebpackPlugin = require('html-webpack-plugin');
const CleanPlugin = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
// 设置输入和输出目录
const ROOT_PATH = path.resolve(__dirname);
const BUILD_PATH = path.resolve(ROOT_PATH, 'build');
// 获取环境
const env = process.env.NODE_ENV;


const PROJECT_ROOT = path.resolve(__dirname, '../')
let dotenv = require('dotenv').config()
let glob = require('glob');

// 获取指定路径下的入口文件
function getEntry(globPath) {
    var entries = {},
        basename, tmp, pathname;

    glob.sync(globPath).forEach(function (entry) {
        basename = path.basename(entry, path.extname(entry));
        tmp = entry.split('/').splice(-3);
        console.log(tmp);
        console.log(path.extname(entry))
        switch (path.extname(entry)) {
            case '.js':
                pathname = 'static/js/'+ basename;
                break;
            case '.html':
                pathname = 'templates/'+ basename;
                break;
            default:
                break;
        }
        //pathname = tmp.splice(1, 1) + '/' + basename; // 正确输出js和html的路径
        entries[pathname] = entry;
    });
    console.log("base-entrys:");
    console.log(entries);
    return entries;
}

let entries = getEntry(__dirname + '/src/main/webapp/js/**/*.js')

let htmlGenerator = function () {
    var entryHtml = glob.sync(__dirname +'/src/main/webapp/*.html');
    var htmlConfigs = [];

    entryHtml.forEach(function (entry) {
        var filename = path.basename(entry, path.extname(entry));
        htmlConfigs.push({template:entry, filename:filename+'.html'})
    })
    return htmlConfigs;
}

const IS_PRODUCTION = dotenv.profile === 'prod'

module.exports = {
    entry: entries,
    output: {
        path: __dirname + '/build/resources/main/',
        publicPath: '/',
        filename: IS_PRODUCTION ? '[name].[chunkhash:8].js' : '[name].js'
    },
    resolve: {
        extensions: ['.js', '.vue', '.css', 'less', '.html'],
        alias: {
            'src': __dirname +'/src/main/webapp',
            'js': __dirname +'/src/main/webapp/js',
            'less': __dirname +'/src/main/webapp/less',
            'img': __dirname +'/src/main/webapp/img',
            'Components': __dirname +'/src/main/webapp/components',
            'vue': 'vue/dist/vue.js'
        }
    },
    module: {
        //preLoaders: [{
        //    test: /\.(vue|js)$/,
        //    loader: 'eslint',
        //    include: PROJECT_ROOT,
        //    exclude: /node_modules/
        //}],
        loaders: [{
            test: /\.vue$/,
            loader: 'vue'
        //}, {
        //    test: /\.js$/,
        //    loader: 'babel',
        //    include: PROJECT_ROOT,
        //    exclude: /node_modules/
        }, {
            test: /\.less$/,
            loader: 'style!css!less'
        }, {
            test: /\.css$/,
            loader: 'style!css'
        //}, {
        //    test: /\.html$/,
        //    loader: 'vue-html'
        }, {
            test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
            loader: 'url',
            query: {
                limit: 10000,
                name: 'img/[name].[hash:8].[ext]'
            }
        }, {
            test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
            loader: 'url',
            query: {
                limit: 10000,
                name: 'fonts/[name].[hash:8].[ext]'
            }
        }]
    },
    //eslint: {
    //    formatter: require('eslint-friendly-formatter')
    //},
    plugins: [
        new webpack.optimize.UglifyJsPlugin({
            minimize: IS_PRODUCTION,
            compress: {
                warnings: false
            },
            comments: !IS_PRODUCTION
        }),
        new htmlWebpackPlugin(),
        new htmlWebpackPlugin({
            template: __dirname +'/src/main/webapp/login.html',
            filename: 'login.html',
        //    chunks: ['app']
        })
    ].concat(htmlGenerator()),
    babel: {
        "presets": ["es2015"],
        "plugins": ["transform-runtime"]
    },
    vue: {
        loaders: {
            js: 'babel',
            css: 'css-loader'
        }
    }
}
