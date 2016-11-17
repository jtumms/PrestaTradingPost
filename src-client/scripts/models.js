const Backbone = require('backbone');




const ItemsModel = Backbone.Model.extend({
  urlRoot: "/get-item",

  initialize: function(){

  }
});

const ItemsModelCollection = Backbone.Collection.extend({
  model: ItemsModel,
  url: "/all-items",

  initialize: function(){

  }

});


module.exports = {
  ItemsModel,
  ItemsModelCollection,
}
