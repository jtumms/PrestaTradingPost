const Backbone = require('backbone')
const STORE = require('./store.js')
const UserModel= require('./model-user.js')
const NewUserModel = require('./new-user-model.js')
const {ItemsModel, ItemsModelCollection, CategoryCollection} = require('./models.js')
const GetUserModel =require('./get-user-model.js')

const ACTIONS = {

  logOutUser: function(){
    let logOutUserInstance = new UserModel('/logout')
    logOutUserInstance.fetch()
      .then(function(){
        STORE.setStore('currentUser', new UserModel() )
      })
  },

  getCurrentUserInfo: function(){
    let getUserModelInstance = new UserModel('/check-auth')
    getUserModelInstance.fetch()
      .then(function(){
        console.log('server res for user info', getUserModelInstance)
        STORE.setStore('currentUser', getUserModelInstance)
        console.log('something here',getUserModelInstance)
      })
      .fail(function(errRes){
        console.log('?????', errRes)
        ACTIONS.routeTo('authview')
      })
  },


  authenticateNewUser: function(newUserDataObj){
    console.log("actions", newUserDataObj)
    let newUserMod = new UserModel('/create-user')
    newUserMod.set(newUserDataObj)
    newUserMod.save().then(function(serverRes){
      STORE.setStore('currentUser', newUserMod)
      location.hash = "/profileview"
    }).fail(function(err){
      location.hash = "/authview"
    })
  },


  authenticateUser: function(userDataObj){

    //  console.log('user data obj', userDataObj)
     let userMod = new UserModel('/login')
     userMod.set(userDataObj)
       console.log('user mod', userMod)
     userMod.save().then(function(serverRes){
      // console.log('serverres', serverRes)
      console.log(userMod)
      STORE.setStore('currentUser', userMod)
      location.hash = "/profileview"
    }).fail(function(err){
      // console.log('wrong pw bro')
      location.hash = "/oops"

    })
  },


  routeTo: function(path){
    window.location.hash = path
  },

  routeHome: function(){
    window.location.hash = '/'
  },


  fetchCategoryCollection: function(catVal){

    const categoryColl = new CategoryCollection(catVal)
    categoryColl.fetch().then(function(){
      STORE.setStore('currentInventory', categoryColl.models)
    })
  },

  fetchItemsModelCollection: function(queryObj){
    // console.log('queryObj', queryObj)
    // console.log('am i even here????')
    // console.log('another test')
     const itemsColl = new ItemsModelCollection()
     itemsColl.fetch().then(function(){
        // console.log("hey look right here this is what we need>>>>>>",itemsColl)
        STORE.setStore('currentInventory', itemsColl.models )

     })
  },

  fetchItemsModel: function(pid){
     const singleMod = new ItemsModel()
     singleMod.set({id:pid})
     singleMod.fetch().then(function(){
        // console.log('returned single mod' ,singleMod)
        STORE.setStore('singleListing', singleMod)
     })
 },

}

module.exports = ACTIONS
