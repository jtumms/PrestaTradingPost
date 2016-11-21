const Backbone = require('backbone');




const ItemsModel = Backbone.Model.extend({
  url: "/get-item",
  idAttribute: "id",

  initialize: function(){

  }
});

const ItemsModelCollection = Backbone.Collection.extend({
  model: ItemsModel,
  url: "/random-items",

  initialize: function(){

  }

});
const CategoryCollection = Backbone.Collection.extend({
  model: ItemsModel,
  _getRoute: {
    "tools" : "TOOLS",
    "sporting-goods" :"SPORTINGGOODS",
    "electronics" : "ELECTRONICS",
    "outdoor" : "OUTDOOR",
  },
    initialize: function(valueInHash){

      this.url=`/items?category=${this._serverCatQuery[valueInHash]}`

  }

});


console.log('this is', ItemsModelCollection.data)
module.exports = {
  ItemsModel,
  ItemsModelCollection,
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
