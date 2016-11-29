const React = require('react')
const ReactDOM = require('react-dom')
const STORE = require('./store.js')
const UserModel = require('./model-user.js')
const ACTIONS = require('./actions.js')

// STORE.setStore('currentUser', {})
const SignOutView = React.createClass({

  // console.log('this',this)
  render: function(){
    console.log('this', STORE._data)
    return(
      <div className="signout-container">
        <div className="multi-header">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <h1 className="signout-thanks">Thank You For Visiting</h1>

        <h2 className="signout-come-again">Please come again</h2>
        <iframe src="//giphy.com/embed/uzPdj5NDahoI0" width="480" height="360" frameBorder="0" className="giphy-embed" allowFullScreen></iframe><p><a href="http://giphy.com/gifs/uzPdj5NDahoI0">via GIPHY</a></p>
      </div>
    )
  }
});

module.exports = SignOutView
