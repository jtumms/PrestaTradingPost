const Backbone = require('backbone')
const STORE = require('./store.js')
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
      location.hash = "/profileview"
    }).fail(function(err){
      // console.log('wrong pw bro')
      location.hash = "/oops"

    })
  },
    routeTo: function(path){
      window.location.hash = routeTo
    }

    routeTo: function(path){
      window.location.hash = routeTo

    }

  fetchItemsModelCollection: function(queryObj){
     const itemsColl = new ItemsModelCollection()
     itemsColl.fetch().then(function(){
        console.log("hey look right here this is what we need>>>>>>",itemsColl)
        STORE.setStore('currentInventory', itemsColl.models )

     })
  },

  fetchItemsModel: function(pid){
     const singleMod = new ItemsModel()
     singleMod.set({id:pid})
     singleMod.fetch().then(function(){
        console.log('returned single mod' ,singleMod)
        STORE.setStore('singleListing', singleMod)
     })
 },

}

module.exports = ACTIONS
