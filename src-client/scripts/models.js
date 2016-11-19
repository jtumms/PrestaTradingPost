const Backbone = require('backbone');




const ItemsModel = Backbone.Model.extend({
  urlRoot: "/get-item",
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

console.log('this is', ItemsModelCollection)
module.exports = {
  ItemsModel,
  ItemsModelCollection,
}
