const Backbone = require('backbone')
const ACTIONS = require('./actions.js')
const UserModel = require('./model-user.js')




const STORE = {
   _data:  {
       currentViewSetting : '',
       currentInventory : [],
       userListing: [],
       categoryListing: [],
       currentUser: new UserModel(),
       singleListing: {},
       confirmedListingRequest: {},
       userNavHistory: []
    },

   setStore: function(storeProp, payload){
      // console.log('set store payload', payload)
      if(typeof this._data[storeProp] === 'undefined'){
         console.error(`Sorry, ${storeProp} is not a value on the store, you need to declare it first`)
         return
      }

      this._data[storeProp] = payload
      Backbone.Events.trigger('storeChange')
   },

   getStoreData: function(){
      // console.log('get store data', this._data)
      return this._data

   },



   onChange: function(someFunc){
      Backbone.Events.on('storeChange', someFunc)
   }

}

module.exports = STORE
