const mongoose = require('mongoose')
const UserSchema = new mongoose.Schema({
    username: {
        type: String,
        require: true,
    },
    password: {
        type: String,
        require: true,
    },
    email: {
        type: String,
        require: true,
    },
    birthdate:{
        type: String,
        require: true
    },
    friendlist:{
        type: Array,
        require: true
    },
    avatar:{
        type: String,
        require: true
    },
    gender:{
        type: String,
        require: true
    }
})

const user = mongoose.model("users", UserSchema);

module.exports = user;