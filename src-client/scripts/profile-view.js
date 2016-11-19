const React = require('react')
const ReactDOM = require('react-dom')



const ProfileView = React.createClass({

  render: function(){
    return(
      <div className="profile-container">
        <div className="profile-home-icon-container">
          <a href=" "><i className="fa fa-home fa-4x profile-home-icon" aria-hidden="true"></i></a>
        </div>
        <div className="profile-header">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <div className="row profile-item-row">
          <div className="profile-add-pic-btn col-sm-4">
            <button className="btn btn-primary btn-lg profile-add-btn">Add Pic</button>
          </div>
          <div className="profile-item-pic col-sm-4"></div>
          <div className="profile-info col-sm-4">
            <form>
              <h2>Profile</h2>
              <h4 className="user-label"><label>Username</label></h4>
              <input className="auth-inputs profile-email-holder" type="text" name="username" placeholder="UserName"/>
              <h4 className="user-label"><label>Name</label></h4>
              <input className="auth-inputs" type="text" name="firstName" placeholder="First Name"/>
              <input className="auth-inputs" type="text" name="lastName" placeholder="Last Name"/>
              <h4 className="user-label"><label>Address</label></h4>
              <input className="auth-inputs" type="text" name="street" placeholder="Address"/>
              <input className="auth-inputs" type="text" name="city" placeholder="City"/>
              <input className="auth-inputs" type="text" name="state" placeholder="State"/>
              <input className="auth-inputs" type="text" name="zipcode" placeholder="Zipcode"/>
            </form>
          </div>
        </div>
      </div>
    )
  }
})

module.exports = ProfileView
