const Backbone = require('backbone')
const STORE = require('./store.js')
const UserModel= require('./model-user.js')
const NewUserModel = require('./new-user-model.js')
const {ItemsModel, ItemsModelCollection, CategoryCollection, RentItemModel} = require('./models.js')
const GetUserModel =require('./get-user-model.js')
const AddItemModel = require('./add-item-model.js')
const $ = require('jquery')
const ACTIONS = {


  logOutUser: function(){
    let logOutUserInstance = new UserModel('/logout')
    logOutUserInstance.set({id: 'forDELETE'})
    logOutUserInstance.destroy()
      .then(function(serverRes){
        console.log('server RES', serverRes)
        STORE.setStore('currentUser', new UserModel() )
        ACTIONS.routeTo("#logout")
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

  createRentalTransaction: function(itemId){
    let rentItemMod = new RentItemModel(itemId)
    // rentItemMod.set()
    rentItemMod.save().then(function(serverRes){
      console.log(serverRes)
      let confirmedRentalModelInstance = new ItemsModel()
      confirmedRentalModelInstance.set(serverRes)
      STORE.setStore('confirmedListingRequest',confirmedRentalModelInstance )

      ACTIONS.routeTo("confirm-rentalview")
    }).fail(function(err){
      console.log(err);
    })

  },


  fetchCategoryCollection: function(catVal){

    const categoryColl = new CategoryCollection(catVal)
    categoryColl.fetch().then(function(){
      STORE.setStore('currentInventory', categoryColl.models)
    })
  },

  fetchItemsModelCollection: function(queryObj){

     const itemsColl = new ItemsModelCollection()
     itemsColl.fetch().then(function(){
        console.log("hey look right here this is what we need>>>>>>",itemsColl)
        STORE.setStore('currentInventory', itemsColl.models )

     })
  },

  fetchSingleItemModel: function(attributes){
    console.log('id arg', attributes);
     const singleMod = new ItemsModel()

     singleMod.url = `/get-item?itemId=` + attributes
     console.log(singleMod.url);
     singleMod.fetch().then(function(){

        console.log('returned single mod' , singleMod)
        STORE.setStore('singleListing', singleMod)
     })
  },
   clearConfirmedRequest: function(){
     STORE.setStore('confirmedListingRequest',{})
   },

  addItemModel: function(newItemDataObj, fileBlobParam){

    const addItemModelInstance = new AddItemModel()
    addItemModelInstance.set(newItemDataObj)
    addItemModelInstance.save().then(function(serverRes){
      console.log("ppp", serverRes)
      let reqConfig = {
        url: `/upload-photo/${serverRes.itemId}`,
        data: fileBlobParam,
        headers: {
          "Content-Type" : "text/plain"
        }
      }

      return (
        $.post(reqConfig)
      ).then(function(serverRes){
        console.log('image uploaded', serverRes)
        STORE.setStore('currentInventory', addItemModelInstance)
      })
    })
    console.log('new item data', newItemDataObj)
    alert("New Item Uploaded!")
    // window.location.hash = "/multiview"
  },

  addImageData: function(file) {

  }
}

module.exports = ACTIONS
