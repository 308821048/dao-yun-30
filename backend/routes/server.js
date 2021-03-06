var express = require('express');
var router = express.Router();
var serverDAO = require('../dao/serverDAO');
var result = require('../model/result');

var jsonWrite = function(res, ret) {
    if(typeof ret === 'undefined') {
        res.send('err');
    } else {
        res.send(ret);
    }
};




/////////////////////////////web端/////////////////////////////

//////////登陆查询接口//////////
router.get('/login', function(req,res) {
    console.log('get login called');
    let server = req.query.data;
    console.log(server);
    serverDAO.user_select_name(server,function (success) {
        console.log(success);
         for(let le in success){
             console.log(success[le].username);
             if ((success[le].username)==(server.username)){
                 if((success[le].password)==(server.password)){
                     return res.sendStatus('200');
                 }else{
                     return res.sendStatus('0');
                 }
             }else{
                 res.sendStatus('-1');
             }
         }
    })
});

//////////课程管理接口//////////
router.get('/s_course', function (req,res) {
    console.log('get s_course called');
    let server = req.query.data;
    console.log(server);
    serverDAO.class_and_course_select(server,function (success) {
            jsonWrite(res,success);
    })

});

////////教师和学生账号管理////////
router.get('/s_ts_account',function (req,res) {
    console.log('get s_ts_account called');
    let server = req.query.data;
    console.log(server);
    serverDAO.w_user_select(server,function (success) {
        jsonWrite(res,success);
    })
});

//////////学生签到情况表//////////
router.get('/s_sign_student',function (req,res) {
    console.log('get s_sign_student called');
    let server = req.query.data;
    console.log(server);
    serverDAO.p_sign(server,function (success) {
        jsonWrite(res,success);
    })
});
//////////课程签到情况表//////////
router.get('/s_sign_situation',function (req,res) {
    console.log('get s_sign_situation called');
    let server = req.query.data;
    console.log(server);
    serverDAO.c_sign(server,function (success) {
        jsonWrite(res,success);
    })
});
//////////人员角色管理//////////
router.get('/q_role_select',function (req,res) {
    console.log('get q_role_select called');
    let server = req.query.data;
    console.log(server);
    serverDAO.q_role_select(server,function (success) {
        jsonWrite(res,success);
    })
});
router.put('/q_role_insert',function (req,res) {
    console.log('get q_role_insert called');
    let server = req.query.data;
    console.log(server);
    serverDAO.q_role_insert(server,function (success) {
        return res.sendStatus('201');//表示插入成功
    })
});
router.put('/q_role_update',function (req,res) {
    console.log('get q_role_update called');
    let server = req.query.data;
    console.log(server);
    serverDAO.q_role_update(server,function (success) {
        return res.sendStatus('201');//表示更新成功
    })
});

module.exports = router;
