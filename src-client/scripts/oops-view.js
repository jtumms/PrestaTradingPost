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
        <h1>OOPS!!!</h1>
        <h3>Sorry Bro.....</h3>
        <h3>Wrong credentials...Try Again.</h3>
      </div>
    )
  }
})

module.exports = OopsView
