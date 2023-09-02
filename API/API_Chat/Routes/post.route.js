const express = require('express');
const router = express.Router();
const posts = require('../Controller/post.controller')
const bodyParser = require("body-parser");
var multer  =   require('multer');
router.use(bodyParser.urlencoded(
    {
        extended: true
    }
))
const upload = multer({
    storage: multer.memoryStorage()
})
router.get('/post/all',posts.getListPost)
router.post('/post/userid',posts.getPostByUserID)
router.post('/post/create',upload.single('file'),posts.createPost)
router.post('/post/update',posts.updatePost)
router.post('/post/delete',posts.deletePost)
router.post('/post/getListComment', posts.getListComment)
router.post('/post/addcomment', posts.addComment)
router.post('/post/updatecomment', posts.updateComment)
router.post('/post/deletecomment',posts.deleteComment)
router.post('/post/togglelike', posts.toggleLike)

router.post('/post/upload',upload.single('file'),posts.uploadFile)
module.exports = router