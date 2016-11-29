const Backbone = require('backbone')

const GetUserModel = Backbone.Model.extend({
   urlRoot: "/check-auth",
   idAttribute: "id",

   initialize: function(){

   }
})


module.exports = GetUserModel
