var express = require('express')
var router = express.Router()
const messenger = require('../Controller/chat.controller')
const chat = require('../Controller/chat.controller')
router.post('/user/chat', chat.loadMessages)
router.post('/user/chat/send', chat.send)
module.exports = router