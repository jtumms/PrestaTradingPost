const React = require('react')
const ReactDOM = require('react-dom')
const STORE = require('./store.js')
const UserModel = require('./model-user.js')


// STORE.setStore('currentUser', {})
const SignOutView = React.createClass({
  // 
  // let userModelInstance = new UserModel()
  //
  // userModelInstance.save().then(function(){
  //   ACTIONS.routeTo(`/logout`)
  // })

  render: function(){

    return(
      <div className="signout-container">
        <div className="multi-header">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <h1 className="thanks">Thank You</h1>
        <h1 className="thanks">For visiting</h1>
        <h2 className="come-again">Please come again</h2>
        <iframe src="//giphy.com/embed/uzPdj5NDahoI0" width="480" height="360" frameBorder="0" className="giphy-embed" allowFullScreen></iframe><p><a href="http://giphy.com/gifs/uzPdj5NDahoI0">via GIPHY</a></p>
      </div>
    )
  }
});

module.exports = SignOutView
