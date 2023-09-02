const userModel = require('../Models/User.model');
const postModel = require('../Models/Post.model')
const fs = require('fs')
const blob = require('based-blob');
const storage = require('../Storage/storage')
module.exports.findAll = async (req, res, next)=>{
    let UID = req.body.UID;
    let friends = [];

    try {
        await userModel.findOne({ _id: UID }).then(user=>{
            friends = user.friendlist
        })
        const users = await userModel.find({});
        let userList = []
        users.forEach(user => {
            if(user.id != UID){
                let fr = true
                if(!friends.includes(user.id)){
                    fr = false
                }
                userList.push({
                    _id: user.id,
                    username: user.username,
                    avatar: user.avatar,
                    friendship: fr 
                })
              
            }
        })
        res.send({
            status: "success",
            message:"successfull",
            friendlist: userList
        })
    } catch (error) {
        res.status(500).send(error);
    }
}

module.exports.getListContact = async (req, res, next)=>{
    let userID = req.body.userID;
    userModel.findOne({_id: userID}).then(async users => {
        try {
            if(users){
                return res.status(200).json({
                    status: 'success',
                    message:'Successful',
                    friendlist: await userModel.find({_id: {$in: users['friendlist']}}, {username: 1, avatar: 1})
                })
            }      
        } catch (error) {
            return res.json({
                status: 'error'
            })
        }  
    })
    
}
module.exports.uploadAvatar = async (req,res,next) => {
    try{
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
        let urlAvatar;
        await blob.getSignedUrl({
            action: 'read',
            expires: '03-09-2491'
        }).then(signedUrls => {
            urlAvatar = signedUrls.toString();
        })
        await userModel.findOneAndUpdate({_id: req.body.userID},{avatar: urlAvatar}, {new: true})
        await postModel.updateMany({userId: req.body.userID}, {avatar: urlAvatar}, {multi: true})
        blobWriter.end(req.file.buffer)
        res.send({
            status:"success",
            message:"File uploaded",
            user: {
                avatar: urlAvatar
            }
        })
    }catch(err){
        console.log(err)
    }
}
module.exports.updateProfile = async (req, res, next)=>{
    try {
        const {UID, username, email, birthdate} = req.body
        await userModel.findOneAndUpdate({_id: UID}, {
            username: username,
            email: email,
            birthdate: birthdate
        }, {new: true})
        await postModel.updateMany({userId: req.body.UID}, {username: username}, {multi: true})
        return res.send({
            status: "success",
            message: "update profile successful"
        })   
    } catch (error) {
        return res.send({
            status: "fail",
            message: "update profile fail"
        })
        console.log(err)
    }
   
}

module.exports.addFriend = async (req,res,next) => {
    let userID = req.body.userId;
    userModel.findOne({_id: userID}).then(async users => {
        if(users.friendlist.includes(req.body.friendId)) {
            users.friendlist.pull(req.body.friendId);
            await users.save();
            return res.status(200).json({
                status: 'success',
                message:'Friend removed'
            });
        }
        try{
            users.friendlist.push(req.body.friendId);
            await users.save();
            res.status(200).json({
                status: 'success',
                message: 'Success addfriend'
            })
        }catch(error){
            res.status(500).send(error)
        }
    })
}



