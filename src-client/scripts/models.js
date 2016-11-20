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

console.log('this is', ItemsModelCollection.data)
module.exports = {
  ItemsModel,
  ItemsModelCollection,
}

//urlRoot: "/get-item",
