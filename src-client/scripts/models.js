const Backbone = require('backbone');




const ItemsModel = Backbone.Model.extend({
  url: "",
  idAttribute: "id",

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

module.exports = {
  ItemsModel,
  ItemsModelCollection,
  CategoryCollection,
}
