const Backbone = require('backbone')

const UserModel = Backbone.Model.extend({
   url: "/login",

   initialize: function(){

   }
})


module.exports = UserModel
