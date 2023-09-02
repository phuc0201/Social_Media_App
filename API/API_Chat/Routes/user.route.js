const express = require('express');
const router = express.Router();
const bodyParser = require("body-parser");
const users = require('../Controller/user.controller')
var multer  =   require('multer');
const user = require('../Models/User.model');
router.use(bodyParser.urlencoded(
    {
        extended: true
    }
))

router.post('/user', users.findAll)

router.post(['/user/friends', '/user/contacts'], users.getListContact)

const upload = multer({
    storage: multer.memoryStorage()
})
router.post('/user/profile/avatar',upload.single('file'),users.uploadAvatar)
router.post('/user/profile/infor', users.updateProfile)
router.post('/user/addfriend',users.addFriend)
module.exports = router

