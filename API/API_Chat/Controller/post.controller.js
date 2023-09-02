const { promises } = require('fs');
const postModel = require('../Models/Post.model');
const userModel = require('../Models/User.model');
const storage = require('../Storage/storage')
const wait = (ms) => {
  return new Promise((res) => setTimeout(res, ms));
};

module.exports.getListComment = async(req, res, next)=>{
  let comments = []
  let posts = await postModel.findOne({_id: req.body.postId})
  posts.comments.forEach(async cmt=>{
    comments.push({
      _id: cmt.id,
      userId: cmt.userId,
      username: cmt.username,
      content: cmt.content,
      date: cmt.date,
      avatar: await userModel.findOne({_id: cmt.userId}).then(user=>user.avatar)
    })
   
  })
  await wait(500).then(()=>{
    res.send({
      status: "success",
      message:"get comments successful",
      comments
    })
  })
}
module.exports.addComment = async (req, res, next) => {
    try {
      const { username, postId, userId, content } = req.body
      const post = await postModel.findById(postId)
      
      if (!post) return res.status(404).json({ 
        status: 'err',
        message: 'Post not found' })
        const user = await userModel.findById(userId)
        if (!user) return res.status(404).json({ message: 'User not found' })
      const comment = {
        username: user.username,
        userId,
        content,
        date: new Date()
      }
      post.comments.push(comment)
      await post.save()
      return res.status(201).json({ 
        status: 'success',
        message: 'Comment added successfully' })
    } catch (err) {
        console.log(err)
      return res.status(500).json({ 
        status: 'err',
        message: 'Error adding comment' 
        })
    }
}


module.exports.updateComment = async (req, res, next) => {
    try {
      const { commentId, content,userId } = req.body
      const post = await postModel.findOne({ 'comments._id': commentId })
      if (!post) return res.status(404).json({ 
        status: 'err',
        message: 'Comment not found' })
  
      const comment = post.comments.find(comment => comment._id.toString() === commentId)
      if (!comment) return res.status(404).json({ 
        status: 'err',
        message: 'Comment not found' })
  
      // kiểm tra quyền truy cập của người dùng
      if (comment.userId !== userId) {
        console.log(req.userId)
        return res.status(401).json({ 
          status: 'err',
          message: 'Unauthorized' })
      }
  
      comment.content = content
      await post.save()
      return res.status(200).json({ 
        status: 'success',
        message: 'Comment updated successfully' })
    } catch (err) {
      console.log(err)
      return res.status(500).json({ 
        status: 'err',
        message: 'Error updating comment' })
    }
}
  
  module.exports.deleteComment = async (req, res, next) => {
    try {
      const { commentId, userId } = req.body
      const post = await postModel.findOne({ 'comments._id': commentId })
      if (!post) return res.status(404).json({ 
        status: 'err',
        message: 'Comment not found' })
  
      const comment = post.comments.find(comment => comment._id.toString() === commentId)
      if (!comment) return res.status(404).json({ 
        status: 'err',
        message: 'Comment not found' })
  
  
      // kiểm tra quyền truy cập của người dùng
      if (comment.userId !== userId) {
        return res.status(401).json({ 
          status: 'err',
          message: 'Unauthorized' })
      }
  
      await post.updateOne({ $pull: { comments: { _id: commentId } }} )
      return res.status(200).json({ 
        status: 'success',
        message: 'Comment deleted successfully' })
    } catch (err) {
      console.log(err)
      return res.status(500).json({ 
        status: 'err',
        message: 'Error deleting comment' })
    }
  }
  module.exports.toggleLike = async (req, res, next) => {
    try {
      const { postId, userId } = req.body;
      const post = await postModel.findById(postId);
      if (!post) return res.status(404).json({ message: 'Post not found' });
  
      const likeIndex = post.like.findIndex(like => like.userId === userId);
      if (likeIndex !== -1) {
        post.like.splice(likeIndex, 1);
      } else {
        post.like.push({ userId });
      }
      post.numLikes = post.like.length.toString();
      await post.save();
  
      return res.status(200).json(post);
    } catch (err) {
      console.log(err);
      return res.status(500).json({ 
        status: 'err',
        message: 'Error toggling like' });
    }
  };
module.exports.getListPost = async (req, res, next) => {
    const post = await postModel.find({});
    try {
        res.send({
            status:"success",
            message:"success",
            listPost: post
        });
    } catch (error) {
        res.status(500).send({
            message:"error",
            status:"error"
        });    
    }
}
module.exports.getPostByUserID = async(req, res, next) =>{
  await postModel.find({userId: req.body.UID}).then(post=>{
    try {
      return res.send({
        status: "success",
        message:"success",
        listPost:post
      })
    } catch (error) {
      res.status(500).send({
        message:"error",
        status:"error"
    });    
    }
  })
}
module.exports.createPost = async (req, res, next) => {
    try {
        if(!req.file) {
            return res.status(400).send("Error: No files found")
        } 
    
        const blob = storage.bucket.file(req.file.originalname)
        
        const blobWriter = blob.createWriteStream({
            metadata: {
                contentType: req.file.mimetype
            }
        })
        
        blobWriter.on('error', (err) => {
            console.log(err)
        })
        let urlFile;
        await blob.getSignedUrl({
            action: 'read',
            expires: '03-09-2491'
          }).then(signedUrls => {
            urlFile = signedUrls.toString();
          })
          let imageUrl = "";
          let videoUrl = "";
          if (req.file.mimetype.startsWith('image/')) {
            imageUrl = urlFile;
          } else if (req.file.mimetype.startsWith('video/')) {
            videoUrl = urlFile;
          }
        blobWriter.end(req.file.buffer)

        const { userId, title, status, content, image, video } = req.body
        const user = await userModel.findById(userId)
        if (!user) return res.status(404).json({ 
            status: 'err',
            message: 'User not found' })
        
        const post = new postModel({
          userId,
          title,
          status,
          content,
          image: imageUrl,
          video: videoUrl,
          avatar: user.avatar,
          username: user.username, // set username of the user who created the post
          datePublished: new Date(), // set datePublished to current date and time
        })
        await post.save()
        return res.status(201).json({
            status: 'success',
            message: 'Post created successfully', post 
          })
      
        } catch (err) {
          console.log(err)
          return res.status(500).json({
              status: 'err',
              message: 'Error creating post' })
      }
}
module.exports.updatePost = async (req, res, next) => {
    postModel.findOneAndUpdate({_id: req.body.postID},{$set:req.body}).then(async post => {
        if(post){
            return res.status(200).json({
                status: 'success',
                message:'Successful',
            })
        }        
        return res.json({
            status: 'error'
        })  
    })
}
module.exports.deletePost = async (req, res, next) => {
    postModel.findOneAndRemove({_id: req.body.postID}).then(async post => {
        if(post){
            return res.status(200).json({
                status: 'success',
                message:'Successful',
            })
        }        
        return res.json({
            status: 'error'
        })  
    })
}
module.exports.uploadFile = async (req,res,next) => {
    if(!req.file) {
        return res.status(400).send("Error: No files found")
    } 

    const blob = storage.bucket.file(req.file.originalname)
    
    const blobWriter = blob.createWriteStream({
        metadata: {
            contentType: req.file.mimetype
        }
    })
    
    blobWriter.on('error', (err) => {
        console.log(err)
    })
    let urlFile;
    await blob.getSignedUrl({
        action: 'read',
        expires: '03-09-2491'
      }).then(signedUrls => {
        urlFile = signedUrls.toString();
      })
      const updateFields = {};

      if (req.file.mimetype.startsWith('image/')) {
        updateFields.image = urlFile;
      } else if (req.file.mimetype.startsWith('video/')) {
        updateFields.video = urlFile;
      }
    blobWriter.on('finish', () => {
        res.status(200).send("File uploaded.")
    })
    blobWriter.end(req.file.buffer)
}