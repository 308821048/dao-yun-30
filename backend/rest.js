// var formatJson = (data) => {
//     try {
//         let count
//         if (data instanceof Array) {
//             count = data.length || -1;
//             if (count == -1) return data;
//             if (data[0].dataValues) {
//                 for (let i = 0; i < count; i++) {
//                     for (let temp in data[i].dataValues) {
//                         if (data[i].dataValues[temp] instanceof Array) {
//                             data[i].dataValues[temp] = formatJson(data[i].dataValues[temp])
//                         } else if (typeof data[i].dataValues[temp] != "string") {
//                             if (typeof data[i].dataValues[temp] == "object")
//                                 data[i].dataValues[temp] = formatJson(data[i].dataValues[temp])
//                             else
//                                 data[i].dataValues[temp] = String(data[i].dataValues[temp])
//                         }
//                     }
//                 }
//             } else {
//                 for (let i = 0; i < count; i++) {
//                     for (let temp in data[i]) {
//                         if (data[i][temp] instanceof Array) {
//                             data[i][temp] = formatJson(data[i][temp])
//                         } else if (typeof data[i][temp] != "string") {
//                             if (typeof data[i][temp] == "object") {
//                                 data[i][temp] = formatJson(data[i][temp])
//                             } else {
//                                 data[i][temp] = String(data[i][temp])
//                             }
//                         }
//                     }
//                 }
//             }
//         } else if (!(data instanceof String)) {
//             if (data.dataValues) {
//                 for (let temp in data.dataValues) {
//                     if (data.dataValues[temp] instanceof Array) {
//                         data.dataValues[temp] = formatJson(data.dataValues[temp])
//                     } else if (typeof data.dataValues[temp] != "string") {
//                         if (typeof data.dataValues[temp] == "object")
//                             data.dataValues[temp] = formatJson(data.dataValues[temp])
//                         else
//                             data.dataValues[temp] = String(data.dataValues[temp])
//                     }
//                 }
//             } else {
//                 for (let temp in data) {
//                     if (data[temp] instanceof Array) {
//                         data[temp] = formatJson(data[temp])
//                     } else if (typeof data[temp] != "string") {
//                         if (typeof data[temp] == "object") {
//                             data[temp] = formatJson(data[temp])
//                         } else {
//                             data[temp] = String(data[temp])
//                         }
//                     }
//                 }
//             }

//         }
//     } catch (err) {
//         console.log(err)
//         throw err
//     }
//     if ((data instanceof String) && data.length == 0) {
//         console.log("data==null")
//         data = null
//     }
//     return data
// }

const LogUtil = require('./log-util')

module.exports = {
    APIError: function (code, message) {
        this.code = code || 'internal:unknown_error';
        this.message = message || '';
    },
    restify: (pathPrefix) => {
        pathPrefix = pathPrefix || '/api/';
        return async (ctx, next) => {
            if (ctx.request.path.startsWith(pathPrefix)) {
                LogUtil.info((new Date()).toUTCString() + ":")
                LogUtil.info(`Process API ${ctx.request.method} ${ctx.request.url}...`);
                ctx.rest = (data) => {
                    ctx.response.type = 'application/json';
                    // data.data = data.data ? formatJson(data.data) : data.data
                    ctx.response.body = data;
                }
                try {
                    await next();
                } catch (e) {
                    var format_time = new Date().toLocaleString()
                    var now = Date.now()
                    LogUtil.error((new Date()).toUTCString() + ' Process API error...');
                    ctx.response.status = 400;
                    ctx.response.type = 'application/json';
                    ctx.response.body = {
                        data: null,
                        request_id: now,
                        result_code: e.code || '0',
                        result_desc: e.message || '未知错误',
                        timestamp: format_time
                    };
                    LogUtil.error(ctx.response.body.result_desc)
                }
            } else {
                await next();
            }
        };
    }
};



