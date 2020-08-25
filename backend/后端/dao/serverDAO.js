var mysql = require('mysql');
var mysqlConf = require('../conf/mysqlConf');
var serverSqlMap = require('./serverSqlMap');
var pool = mysql.createPool(mysqlConf.mysql);

module.exports = {

    //////////////////web端//////////////////

    user_select_name:function (user,callback) {
        pool.query(serverSqlMap.user_select_name,[user.username,user.password],function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    class_and_course_select:function (user,callback) {
        pool.query(serverSqlMap.class_and_course_select,user.selectKeyWord, function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    class_and_course_update:function(user,callback) {
        pool.query(serverSqlMap.class_and_course_update,user.selectKeyWord, function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    w_user_select: function (user, callback) {
        pool.query(serverSqlMap.w_user_select, [user.selectIdentity,user.selectNum,user.selectSchool,user.selectCollege], function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    p_sign: function (user,callback) {
        //console.log(user.selectSchool);
        pool.query(serverSqlMap.p_sign, [user.selectSchool,user.selectCollege,user.selectNum], function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    c_sign: function (user,callback) {
        //console.log(user.selectSchool);
        pool.query(serverSqlMap.c_sign, [user.selectSchool,user.selectCollege,user.selectCourse], function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    q_role_select:function (user,callback) {
        console.log(user);
        pool.query(serverSqlMap.q_role_select, user.selectSchool, function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    q_role_insert:function (user,callback) {
        //console.log(user);
        pool.query(serverSqlMap.q_role_insert,[user.number,user.name,user.identity,user.school], function (error, result) {
            if (error) throw error;
            callback(result);
        });
    },
    q_role_update:function (user,callback) {
        //console.log(user);
        pool.query(serverSqlMap.q_role_update,[user.number,user.identity,user.school,user.name], function (error, result) {
            if (error) throw error;
            callback(result);
        });
    }
};
