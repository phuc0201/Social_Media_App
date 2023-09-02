const mongoose = require('mongoose')
const ConnectionString = 'mongodb+srv://cluster0.cmeuabb.mongodb.net/?retryWrites=true&w=majority'
mongoose.connect(ConnectionString,
  {
    dbName: 'ChatApp_DB',
    user: 'tuanbmt123',
    pass: 'tuanbmt123',
    useNewUrlParser: true,
    useUnifiedTopology: true
  }
);
const db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error: "));
db.once("open", function () {
  console.log("Connected successfully");
});
module.exports = db