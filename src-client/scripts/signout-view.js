const React = require('react')
const ReactDOM = require('react-dom')
const STORE = require('./store.js')
const UserModel = require('./model-user.js')
const ACTIONS = require('./actions.js')

// STORE.setStore('currentUser', {})
const SignOutView = React.createClass({
  //
  // let logoutModelInstance = new LogoutModel()
  //
  // logoutModelInstance.get('/logout').then(function(){
    // ACTIONS.logOutUser('/logout')
  // })

  render: function(){

    return(
      <div className="signout-container">
        <div className="multi-header">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <h1 className="signout-thanks">Thank You For Visiting!</h1>

        <h2 className="signout-come-again">Please come again</h2>
        <iframe src="//giphy.com/embed/uzPdj5NDahoI0" width="480" height="360" frameBorder="0" className="giphy-embed" allowFullScreen></iframe><p><a href="http://giphy.com/gifs/uzPdj5NDahoI0">via GIPHY</a></p>
        <div>
          <a href="https://www.facebook.com/"><i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://twitter.com/"><i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://www.instagram.com/?hl=en"><i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i></a>
          <h1 className="copyright multi-icons">&#xa9; <strong><em>2016 Team SilverBack</em></strong></h1>
        </div>
      </div>

    )
  }
});

module.exports = SignOutView
