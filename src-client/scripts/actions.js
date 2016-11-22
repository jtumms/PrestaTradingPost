const Backbone = require('backbone')
const STORE = require('./store.js')
const UserModel= require('./model-user.js')
const NewUserModel = require('./new-user-model.js')
const {ItemsModel, ItemsModelCollection, CategoryCollection} = require('./models.js')


const ACTIONS = {

  authenticateNewUser: function(newUserDataObj){
    let newUserMod = new NewUserModel()
    newUserMod.set(newUserDataObj)
    newUserMod.save().then(function(serverRes){
      location.hash = "/profileview"
    }).fail(function(err){
      location.hah = "/oops"
    })    
  },


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
    window.location.hash = path
  },

  fetchCategoryCollection: function(catVal){

    const categoryColl = new CategoryCollection(catVal)
    categoryColl.fetch().then(function(){
      STORE.setStore('currentInventory', categoryColl.models)
    })
  },

  fetchItemsModelCollection: function(queryObj){
    // console.log('queryObj', queryObj)
    console.log('am i even here????')
    console.log('another test')
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
