//Package
const express = require("express")
const user = require('./Routes/user.route');
const messenger = require('./Routes/chat.route');
const auth = require('./Routes/auth.route');
const post = require('./Routes/post.route');
const chatModel = require('./Models/Chat.model')
const userModel = require('./Models/User.model')
const chat_controller = require('./Controller/chat.controller')
const app = express()
const http = require("http").createServer(app);
const io = require("socket.io")(http);
const cors = require("cors");
var bodyParser = require("body-parser");
const { timeStamp } = require("console");
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cors());
app.use(auth);
app.use(user);
app.use(post);
app.use(messenger);

io.on("connection", (socket) => {
    // socket.on("user online",async (user_id)=>{
    //     // userModel.findOne({_id: user_id}).then(user=>{
    //     //     user.active_status = true
    //     //     user.save();
    //     // })
    //     // await userModel.find({}).then(async users=>{
    //     //     users.forEach(async user=>{
    //     //         if(user["friendlist"].includes(user_id)){
    //     //             io.emit("list contact", {
    //     //                 userID: user["_id"],
    //     //                 friendlist: await userModel.find({_id: {$in: user['friendlist']}}, {username: 1, avatar: 1})
    //     //             });
    //     //         }
    //     //     })
    //     // })
    // })
    socket.on("new message",async (new_message)=>{
        io.emit("new message", new_message)
        let data = {
            sender_id: new_message.sender_id,
            timestamp: new_message.timestamp,
            message: new_message.message,
            type: new_message.type
        }
        await chatModel.findOne({_id: new_message.chat_id}).then(message => {
            message.messages.push(data)
            message.save()
        })
    })
    socket.on("get list contact", async (userId)=>{
        userModel.findOne({_id: userId}).then(async users => {
            if(users){
                io.emit("list contact", {
                    userID: userId,
                    friendlist: await userModel.find({_id: {$in: users['friendlist']}}, {username: 1, avatar: 1, active_status: 1})
                })
            }        
        })
    })
    socket.on("new notification", async (notification)=>{
        if(notification.receiver_id !== notification.sender_id){
            await userModel.findOne({_id: notification.sender_id}).then(user=>{
                io.emit("new notification", {
                    receiver_id: notification.receiver_id,
                    UID: user.id,
                    username: user.username,
                    avatar: user.avatar,
                    content: notification.content
                })
            })
        }
    })
});

http.listen(3000, async() => {
    try {
        require('./database/connection')
        console.log("Listening on port :%s...", http.address().port);
    } catch (e) {
        console.error(e);
    }
});
  