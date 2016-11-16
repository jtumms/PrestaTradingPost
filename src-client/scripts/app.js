const ReactDOM = require('react-dom');
const React = require('react')
const Backbone = require('backbone');

<<<<<<< HEAD
document.querySelector('#app-container').innerHTML = `<h1>Ta-Dow</h1>`

new AppRouter()
=======
const AppViewController = require('./component-viewcontroller.js')
const AppRouter = Backbone.Router.extend({

    routes: {
      "profile" : "showProfileView",
      "authview" : "showAuthView",
      "singleview" : "showSingleView",
      "*path" : "showMultiView"
    },

    showMultiView: function(){
          ReactDOM.render( <AppViewController routedFrom="MultiView"/>, document.querySelector('#app-container') )
        },
  //
  //   showSingleView: function(pid){
  //     ReactDOM.render( <AppViewController routedFrom="SingleView" pidInRoute={pid} />, document.querySelector('#app-container') )
  //   },
  //
  //   showAuthView: function(){
  //   ReactDOM.render(<AppViewController routedFrom="AuthView"/>, document.querySelector('#app-container') )
  // },
  //
  //   showProfileView: function(){
  //     ReactDOM.render(<AppViewController routedFrom="ProfileView"/>, document.querySelector('#app-container') )
  //   }

    initialize: function() {
    Backbone.history.start()
  }
})

new AppRouter()





// document.querySelector('#app-container').innerHTML = `<h1>Yah okay</h1>`
>>>>>>> ad71392ee46ddf739209b2af0e380d1c48a18a75
