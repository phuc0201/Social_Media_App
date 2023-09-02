const mongoose = require('mongoose')

const CommentSchema = new mongoose.Schema({
    userId: {
      type: String,
      required: true
    },
    username:{
      type: String,
      required: true
    },
    content: {
      type: String,
      required: true
    },
    date: {
      type: Date,
      default: Date.now
    }
  })
  
  const PostSchema = new mongoose.Schema({
    userId: {
      type: String,
  
    },
    title: {
      type: String,
  
    },
    status: {
      type: String,
  
    },
    content: {
      type: String,
  
    },
    datePublished: {
      type: Date,
  
    },
    image: {
      type: String,
  
    },
    avatar:{
      type:String
    },
    username:{
      type:String
    },
    video: {
      type: String,
  
    },
    comments: [CommentSchema],
    numLikes: {
        type: String,
        require: true,
    },
    like: [
      {
        userId: {
          type: String,
          required: true
        }
      }
    ]
  })

const Post = mongoose.model('Post', PostSchema)
module.exports = Post