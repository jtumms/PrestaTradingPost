const Backbone = require('backbone');




const ItemsModel = Backbone.Model.extend({
  url: "",
  // idAttribute: "id",

  initialize: function(){

  }
});

const ItemsModelCollection = Backbone.Collection.extend({
  model: ItemsModel,
  url: "/all-items",

});

const CategoryCollection = Backbone.Collection.extend({
  model: ItemsModel,
  _serverCatQuery: {
    "tools" : "TOOLS",
    "sporting-goods" : "SPORTINGGOODS",
    "electronics" : "ELECTRONICS",
    "outdoor" : "OUTDOOR"
  },

  initialize: function(valueInHash){

    this.url=`/items?category=${this._serverCatQuery[valueInHash]}`
    console.log("here it is", this.url)
  }
});
// const CategoryCollection = Backbone.Collection.extend({
//   model: ItemsModel,
//   _getRoute: {
//     "tools" : "TOOLS",
//     "sporting-goods" :"SPORTINGGOODS",
//     "electronics" : "ELECTRONICS",
//     "outdoor" : "OUTDOOR",
//   },
//     initialize: function(valueInHash){
//
//       this.url=`/items?category=${this._serverCatQuery[valueInHash]}`
//
//   }
//
// });


// console.log('this is', ItemsModelCollection.data)
module.exports = {
  ItemsModel,
  ItemsModelCollection,
  CategoryCollection,
}

//urlRoot: "/get-item",
// SINGLE ITEM ROUTE =
//
// localhost:8080/get-item?itemId=1
//
// returns single item by itemId

// const InventoryModel = Backbone.Model.extend({
//   urlRoot: "/get-boot",
//
//   initialize: function(){
//
//   }
// });
