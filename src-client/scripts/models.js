const Backbone = require('backbone');



const ItemsModel = Backbone.Model.extend({
  urlRoot: "",

  initialize: function(){

  }
});

const ItemsModelCollection = Backbone.Collection.extend({
  model: ItemsModel,
  url: "",

  initialize: function(){

  }

});


module.exports = {
  ItemsModel,
  ItemsModelCollection,
}
