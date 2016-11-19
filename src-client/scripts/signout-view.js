const React = require('react')
const ReactDOM = require('react-dom')

const SignOutView = React.createClass({

  render: function(){

    return(
      <div className="signout-container">
        <h1>Thank You</h1>
        <h1>For visiting</h1>
        <h2>Please come again</h2>
      </div>
    )
  }
});

module.exports = SignOutView
