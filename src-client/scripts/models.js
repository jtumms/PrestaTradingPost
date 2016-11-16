const Backbone = require('backbone');
<<<<<<< HEAD
=======



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
>>>>>>> ad71392ee46ddf739209b2af0e380d1c48a18a75
