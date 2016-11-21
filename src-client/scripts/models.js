const Backbone = require('backbone');




const ItemsModel = Backbone.Model.extend({
  url: "/get-item",
  idAttribute: "id",

  initialize: function(){

  }
});

const ItemsModelCollection = Backbone.Collection.extend({
  model: ItemsModel,
  url: "/all-items",

  initialize: function(){

  }

});

// const CategoryCollection = Backbone.Collection.extend({
//   model: ItemsModel,
//   url: "/items?category=",
//
//   initialize: function(){
//
//   }
// });

console.log('this is', ItemsModelCollection.data)
module.exports = {
  ItemsModel,
  ItemsModelCollection,
  // CategoryCollection
}

//urlRoot: "/get-item",
