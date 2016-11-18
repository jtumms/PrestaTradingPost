const Backbone = require('backbone')
const AuthView = require('./auth-view.js')
// const STORE = require('./store.js')
const UserModel= require('./model-user.js')
const OopsView = require('./oops-view.js')
const {ItemsModel, ItemsModelCollection} = require('./models.js')
const ProfileView = require('./profile-view.js')

const ACTIONS = {
  authenticateUser: function(userDataObj){
    //  console.log('user data obj', userDataObj)
     let userMod = new UserModel()

     userMod.set(userDataObj)
    //  console.log('user mod', userMod)

     userMod.save().then(function(serverRes){
      // console.log('serverres', serverRes)
      location.hash = "/multiview"
    }).fail(function(err){
      // console.log('wrong pw bro')
      location.hash = "/oops"

    })
  }
}
console.log(ACTIONS);
module.exports = ACTIONS
