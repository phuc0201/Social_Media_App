const userModel = require('../Models/User.model')

module.exports.register = (req, res, next)=>{
    let username = req.body.username;
    let email = req.body.email;
    let password = req.body.password;
    try {
        userModel.findOne({ email: email }).then(user => {
            if(user){
                return res.json({
                    error: 'Email already exists'
                })
            }        
            user = new userModel({username, password, email})
            if(user.save()){
                return res.send(user)
            }
        })
    } catch (err) {
        console.log(err);
    }
}

module.exports.login = (req, res, next)=>{
    let email = req.body.email;
    let password = req.body.password;
    try {
        userModel.findOne({ email: email, password:password}).then(user => {
            if(user){
                return res.status(200).json({
                    status: 'success',
                    message:'Login successful',
                    user
                })
            }        
            return res.json({
                status: 'error'
            })
        })
    } catch (err) {
        console.log(err);
    }
}