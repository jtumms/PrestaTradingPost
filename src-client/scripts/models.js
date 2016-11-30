const Backbone = require('backbone');

const RentItemModel = Backbone.Model.extend({
  urlRoot: "/rent-item?itemId=",
  idAttribute: "id",
  initialize: function(id){
    this.url = "/rent-item?itemId=" + id

  }
});

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
    console.log("here it is, the category/cathash", this.url)
  }
});


// console.log('this is', ItemsModelCollection.data)
module.exports = {
  ItemsModel,
  ItemsModelCollection,
  CategoryCollection,
  RentItemModel,
}
