const Backbone = require('backbone')
const React = require('react')
const ACTIONS = require('./actions.js')
const ProfileView = require("./profile-view.js")
const STORE = require('./store.js')
// console.log('action??', ACTIONS);

const AuthView = React.createClass({
  _handleNewUserAuth: function(evt){
    evt.preventDefault()

    let newUserData = {
      username: evt.target.username.value,
      password: evt.target.password.value,
      userDetail: {
        firstName: evt.target.firstName.value,
        lastName: evt.target.lastName.value,
        street: evt.target.street.value,
        city: evt.target.city.value,
        state: evt.target.state.value,
        zipcode: evt.target.zipcode.value
      }
    }
    console.log('new user data', newUserData)
    ACTIONS.authenticateNewUser(newUserData)
  },



  _handleUserAuth: function(evt){
    evt.preventDefault()
    // console.log('evt', evt)
    let newUserData = {
      username: evt.target.username.value,
      password: evt.target.password.value

    }
      console.log('user data', newUserData)
      console.log('new user data', newUserData)

    ACTIONS.authenticateUser(newUserData)
    // STORE.setStore(newUserData)
  },

  render: function() {
    return (

          <div className="auth-container">
            <div className="auth-home-icon-container">
              <a href=" "><i className="fa fa-home fa-4x auth-home-icon" aria-hidden="true"></i></a>
            </div>
            <div className="auth-header-container">
                <h2>LOGO</h2>
                <h1 className="auth-header">Presta Trading Post</h1>
            </div>
            <div className="row auth-container-row">
              <div className="col-sm-6 auth-container">
                <form className="form-group auth-grid-container" onSubmit={this._handleUserAuth}>
                   <h1 className="auth-sign-in">Sign-in</h1>
                   <div className="form-field auth-user-container">
                       <h4 className="user-label"><label><u>Username</u></label></h4>
                       <input className="auth-inputs" type="text" name="username" placeholder="Enter Email"/>
                   </div>
                   <div className="form-field auth-pass-container ">
                       <h4 className="pass-label"><label><u>Password</u></label></h4>
                       <input className="auth-inputs" type="password" name="password" placeholder="Enter Password"/>
                   </div>
                   <div className="form-field auth-btn-container">
                     <input type="submit" className="btn primary auth-button" placeholder="Submit" />
                   </div>
                 </form>
              </div>
              <div className="col-sm-6 auth-container">
                <form className="form-group auth-grid-container" onSubmit={this._handleNewUserAuth}>
                  <div className="form-field auth-user-container">
                      <h1 className="auth-sign-in">Sign-up</h1>


                      <h4 className="user-label auth-input-label"><label><u>Username</u></label></h4>
                      <input className="auth-inputs" type="text" name="username" placeholder="Enter Email"/>
                      <h4 className="user-label auth-input-label"><label><u>Name</u></label></h4>
                      <input className="auth-inputs" type="text" name="firstName" placeholder="Enter First Name"/>
                      <input className="auth-inputs" type="text" name="lastName" placeholder="Enter Last Name"/>
                      <h4 className="user-label auth-input-label"><label><u>Address</u></label></h4>


                      <input className="auth-inputs" type="text" name="street" placeholder="Enter Address"/>
                      <input className="auth-inputs" type="text" name="city" placeholder="Enter City"/>
                      <input className="auth-inputs" type="text" name="state" placeholder="Enter State"/>
                      <input className="auth-inputs" type="text" name="zipcode" placeholder="Enter Zipcode"/>
                      <h4 className="user-label auth-input-label"><label><u>Password</u></label></h4>
                      <input className="auth-inputs" type="password" name="password" placeholder="Enter Password"/>
                      <div className="form-field auth-btn-container">
                        <input type="submit" className="btn primary auth-button" placeholder="Submit" />
                      </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
    )
  }
})

module.exports = AuthView

//            <div className="row auth-container-row">
//            </div>
