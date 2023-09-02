const express = require('express');
const router = express.Router();

router.use(express.json())
router.use(express.urlencoded({extended:true}))

const auth = require('../Controller/auth.controller')

router.post('/auth/register', auth.register)

router.post('/auth/login', auth.login)

module.exports = router
