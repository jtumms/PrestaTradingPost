const ReactDOM = require('react-dom');
const React = require('react')
const Backbone = require('backbone');
const SingleView = require ('./single-view.js')
const MultiView = require ('./multi-view.js')
const ProfileView = require('./profile-view.js')
const AppViewController = require('./component-viewcontroller.js')

const AppRouter = Backbone.Router.extend({

    routes: {
      "profileview" : "showProfileView",
      "authview" : "showAuthView",
      "singleview" : "showSingleView",
      "*path" : "showMultiView"
    },

    showMultiView: function(){
          ReactDOM.render( <AppViewController routedFrom="MultiView"/>, document.querySelector('#app-container') )
        },
  //
    showSingleView: function(pid){

      ReactDOM.render( <AppViewController routedFrom="SingleView" pidInRoute={pid} />, document.querySelector('#app-container') )
    },
  //
    showAuthView: function(){
    ReactDOM.render(<AppViewController routedFrom="AuthView"/>, document.querySelector('#app-container') )
    },
  //
    showProfileView: function(){
      ReactDOM.render(<AppViewController routedFrom="ProfileView"/>, document.querySelector('#app-container') )
    },

    initialize: function() {
    Backbone.history.start()
  }
})

new AppRouter()





// document.querySelector('#app-container').innerHTML = `<h1>Yah okay</h1>`
