const Backbone = require('backbone')

const GetUserModel = Backbone.Model.extend({
   url: "/get-user",
  //  idAttribute: "id",

   initialize: function(){
     console.log("/?????")
   }
})


module.exports = GetUserModel
