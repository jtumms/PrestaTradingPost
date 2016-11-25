const Backbone = require('backbone')

const AddItemModel = Backbone.Model.extend({
   url: "/add-item",

   initialize: function(path){
     this.url = path
   }
})


module.exports = AddItemModel
