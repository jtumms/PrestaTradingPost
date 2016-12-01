const React = require('react')
const ReactDOM = require('react-dom')
const MultiView = require('./multi-view.js')
const AuthView = require("./auth-view.js")
const {NavToHome} = require('./components-shared.js')


const OopsView = React.createClass({

  render: function(){

    return (
      <div className="oops-container">
        <NavToHome/>
        <div className="oops-header">
          <h1>Presta Trading Post</h1>
        </div>
        <div className="oops-message">
          <h1>OOPS!!!</h1>
          <div className="oops-confused-pic"></div>
          <h2>Sorry Bro.....</h2>
          <h2>Wrong credentials...Try Again.</h2>
          <div className="btn-group">
            <a href="/#authview"><button type="button" className="btn btn-primary btn-lg oops-auth-btn">Again?</button></a>
          </div>
      </div>
      </div>
    )
  }
})

module.exports = OopsView
