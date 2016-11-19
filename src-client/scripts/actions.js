const Backbone = require('backbone')
// const STORE = require('./store.js')
const UserModel= require('./model-user.js')
const {ItemsModel, ItemsModelCollection} = require('./models.js')


const ACTIONS = {

  authenticateUser: function(userDataObj){
    //  console.log('user data obj', userDataObj)
     let userMod = new UserModel()

     userMod.set(userDataObj)
    //  console.log('user mod', userMod)

     userMod.save().then(function(serverRes){
      // console.log('serverres', serverRes)
      location.hash = ""
    }).fail(function(err){
      // console.log('wrong pw bro')
      location.hash = "/oops"

    })
  }

}

module.exports = ACTIONS
