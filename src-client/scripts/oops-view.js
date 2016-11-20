const React = require('react')
const ReactDOM = require('react-dom')
const MultiView = require('./multi-view.js')

const OopsView = React.createClass({

  render: function(){

    return (
      <div>
        <div className="auth-home-icon-container">
          <a href=" "><i className="fa fa-home fa-4x auth-home-icon" aria-hidden="true"></i></a>
        </div>
        <div className="oops-header">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <div className="oops-message">
          <h1>OOPS!!!</h1>
          <div className="oops-confused-pic"></div>
          <h2>Sorry Bro.....</h2>
          <h2>Wrong credentials...Try Again.</h2>
        </div>
      </div>
    )
  }
})

module.exports = OopsView
