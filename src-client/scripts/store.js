const Backbone = require('backbone')
const ACTIONS = require('./actions.js')



const STORE = {
   _data:  {
       currentViewSetting : '',
       currentInventory : [],
       singleListing: {} //Backbone Model instance
    },

   setStore: function(storeProp, payload){
      console.log('set store payload', payload)
      // console.log(payload);
      if(typeof this._data[storeProp] === 'undefined'){
         console.error(`Sorry, ${storeProp} is not a value on the store, you need to declare it first`)
         return
      }

      this._data[storeProp] = payload
      Backbone.Events.trigger('storeChange')
      // console.log('payload', payload.)
   },

   getStoreData: function(){
      console.log('get store data', this._data)
      return this._data

   },

   onChange: function(someFunc){
      Backbone.Events.on('storeChange', someFunc)
   }

}

module.exports = STORE
