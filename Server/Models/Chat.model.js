var mongoose = require('mongoose');
const ChatSchema = new mongoose.Schema({
    messages:{
        type: Array,
        default: []
    },
    members:{
        type: Array,
        default: []
    }
})
const chat = mongoose.model("chat", ChatSchema)
module.exports = chat