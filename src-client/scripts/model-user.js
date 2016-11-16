const Backbone = require('backbone')

const UserModel = Backbone.Model.extend({
   url: "/user",

   initialize: function(){

   }
})


module.exports = UserModel
