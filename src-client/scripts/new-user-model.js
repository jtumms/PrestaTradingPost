const Backbone = require('backbone')

const NewUserModel = Backbone.Model.extend({
   url: "/create-user",

   initialize: function(){

   }
})


module.exports = NewUserModel
