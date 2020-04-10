// 导入koa，和koa 1.x不同，在koa2中，我们导入的是一个class，因此用大写的Koa表示:
const Koa = require('koa');

// 注意require('koa-router')返回的是函数:
const router = require('koa-router')();

// 导入controller middleware:
const controller = require('./controller');

const bodyParser = require('koa-body');

const cors = require('koa2-cors');

const isProduction = process.env.NODE_ENV === 'production';

const rest = require('./rest');

const port = require('./config').server_port

const tokenFilter = require('./token-interceptor')

const log_util = require("./log-util");

// 创建一个Koa对象表示web app本身:
const app = new Koa();

app.use(async (ctx, next) => {
    log_util.addlog()
    await next()
});

app.use(cors())

//1、log request URL:
app.use(async (ctx, next) => {
    // console.log(`Process ${ctx.request.method} ${ctx.request.url}...`);
    var
        start = new Date().getTime(),
        execTime;
    await next();
    execTime = new Date().getTime() - start;
    ctx.response.set('X-Response-Time', `${execTime}ms`);
});




let staticFiles = require('./static-files');
app.use(staticFiles('/static/', __dirname + '/static'));

//3、处理post参数
app.use(bodyParser({
    multipart: true,
    strict: false,
    formidable: {
        uploadDir: __dirname + "/static/img",
        maxFieldsSize: 20 * 1024 * 1024
    }
}));

// bind .rest() for ctx: 可选 RESTApi使用
app.use(rest.restify());

app.use(tokenFilter)

//5、使用middleware:添加controller
app.use(controller());

//监听端口
app.listen(port);
log_util.info('app started at port ' + port + '...');