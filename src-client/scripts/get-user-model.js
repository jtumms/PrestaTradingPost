const Backbone = require('backbone')

const GetUserModel = Backbone.Model.extend({
   url: "/get-user",
   idAttribute: "id",

   initialize: function(){

   }
})


module.exports = GetUserModel
