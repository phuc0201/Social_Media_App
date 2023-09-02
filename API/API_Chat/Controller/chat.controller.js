const chat = require('../Models/Chat.model');
const chatModel = require('../Models/Chat.model')

module.exports.loadMessages = async (req, res) => {
    const user1 = req.body.UID_1;
    const user2 = req.body.UID_2;
    await chatModel.find({}).then(chats => {
        chats.forEach(chat=>{
            try{
                if(chat["members"].includes(user1) && chat["members"].includes(user2)){
                    return res.send({
                            status: "success",
                            chat_id: chat["_id"],
                            messages: chat["messages"]
                        })
                }
            }catch(err){
                return res.send({
                    status: "error"
                })
            }
            
        })

    })
}

module.exports.send = async (req, res) => {
    const chat_id = req.body.chat_id;
    const data = {
        sender_id: req.body.sender_id,
        timestamp: req.body.timestamp,
        message: req.body.message,
        type: req.body.type
    }
    const new_message = await chatModel.findOne({_id: chat_id}).then(message => {
        message.messages.push(data)
        message.save()
        return res.json({
            status: "success"
        })
    })
}

