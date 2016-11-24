const Backbone = require('backbone')

const UserModel = Backbone.Model.extend({
   url: "/login",

   initialize: function(path){
     this.url = path
   }
})


module.exports = UserModel
