const Backbone = require('backbone')

const LogoutModel = Backbone.Model.extend({
   url: "/logout",

   initialize: function(path){
     this.url = path
   }
})


module.exports = LogoutModel
