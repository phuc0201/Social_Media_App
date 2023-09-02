const admin = require('firebase-admin')
let serviceAccount = require("../service-account.json")
// Initialize firebase admin SDK
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  storageBucket: "chatapp-56014.appspot.com"
})
// Cloud storage
const bucket = admin.storage().bucket()
module.exports = {
  bucket
}