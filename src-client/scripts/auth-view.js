const Backbone = require('backbone')
const React = require('react')

const ProfileView = require("./profile-view.js")
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
            <div className="auth-home-icon-container">
              <a href=" "><i className="fa fa-home fa-4x auth-home-icon" aria-hidden="true"></i></a>
            </div>
              <div className="auth-header-container">
                <h2>LOGO</h2>
                <h1 className="auth-header">Presta Trading Post</h1>
              </div>

              <div className="row auth-container-row">
                <div className="col-sm-6 auth-container">
                   <h2 className="auth-sign-in">Sign-in</h2>
                   <div className="form-field auth-user-container">
                       <h4 className="user-label"><label>Username</label></h4>
                       <input className="auth-inputs" type="text" name="name" placeholder="Enter Email"/>
                   </div>

                   <div className="form-field auth-pass-container ">
                       <h4 className="pass-label"><label>Password </label></h4>
                       <input className="auth-inputs" type="password" name="password" placeholder="Enter Password"/>
                   </div>

                   <div className="form-field auth-btn-container">
                      <a href="#profileview"><input type="text" className="btn primary auth-button" placeholder="Submit" /></a>
                   </div>
                </div>
                <div className="col-sm-6">
                  <div className="form-field auth-user-container">
                      <h2 className="auth-sign-in">Sign-up</h2>
                      <h4 className="user-label"><label>Username</label></h4>
                      <input className="auth-inputs" type="text" name="name" placeholder="Enter Email"/>
                      <h4 className="user-label"><label>Name</label></h4>
                      <input className="auth-inputs" type="text" name="name" placeholder="Enter Name"/>
                      <h4 className="user-label"><label>Address</label></h4>
                      <input className="auth-inputs" type="text" name="name" placeholder="Enter Address"/>
                      <h4 className="user-label"><label>Password</label></h4>
                      <input className="auth-inputs" type="text" name="name" placeholder="Enter Password"/>
                      <div className="form-field auth-btn-container">
                         <a href="#profileview"><input type="text" className="btn primary auth-button" placeholder="Submit" /></a>
                      </div>
                  </div>
                </div>

              </div>
          </form>
      </div>
    )
  }
})

module.exports = AuthView
