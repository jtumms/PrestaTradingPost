const Backbone = require('backbone')
const React = require('react')


// const ACTIONS = require('./actions.js')

const AuthView = React.createClass({

  _handleUserAuth: function(evt){
    evt.preventDefault()

    let newUserData = {
      username: evt.target.name.value,
      password: evt.target.password.value
    }

    ACTIONS.authenticateUser(newUserData)
  },

  render: function() {
    return (
      <div>
            <form className="form-group auth-grid-container" onSubmit={this._handleUserAuth}>
              <div className="auth-header-container">
                <h2>LOGO</h2>
                <h1 className="auth-header">Presta Trading Post</h1>
              </div>
              <h2 className="auth-sign-in">Sign-in or Sign-up</h2>
              <div className="row auth-container-row">
                 <div className="form-field auth-user-container col-sm-12 col-md-12">
                     <h2 className="user-label"><label>Username</label></h2>
                     <input className="auth-inputs" type="text" name="name" placeholder="Enter Email"/>
                 </div>

                 <div className="form-field auth-pass-container col-sm-12 col-md-12">
                     <h2 className="pass-label"><label>Password </label></h2>
                     <input className="auth-inputs" type="password" name="password" placeholder="Enter Password"/>
                 </div>

                 <div className="form-field auth-btn-container col-sm-12 col-md-12">
                     <input type="submit" className="btn primary auth-button" value="+" />
                 </div>
              </div>
            </form>
      </div>
    )
  }
})

module.exports = AuthView
