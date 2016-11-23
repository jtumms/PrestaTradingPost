const Backbone = require('backbone')

const GetUserModel = Backbone.Model.extend({
   url: "/check-auth",
  //  idAttribute: "id",

   initialize: function(){
   }
})


module.exports = GetUserModel
