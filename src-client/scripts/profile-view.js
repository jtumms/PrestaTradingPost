const React = require('react')
const ReactDOM = require('react-dom')
const GetUserModel = require('./get-user-model.js')
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')
const ProfileView = React.createClass({

  componentWillMount: function(obj){
    let getUserModelInstance = new GetUserModel()

    // let currentUserId = STORE.getStoreData()
    ACTIONS.getCurrentUserInfo()
    },

    // getUserDetails: function(userInfoObj) {
    // let getUserModelInstance = new GetUserModel()
    // // console.log(getUserModelInstance)
    // // ACTIONS.fetchgetUserInfo()
    // }
  // },



  render: function(){
    console.log('what up')
    console.log(STORE._data.getUserInfo)

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
            <h2>Add an item to rent</h2>
            <input className="profile-item-inputs" type="text" name="item" placeholder="Item to rent"/>
            <input className="profile-item-inputs" type="text" name="description" placeholder="Item description"/>
            <input className="profile-item-inputs" type="text" name="price" placeholder="Item rent price"/>
            <button className="btn btn-primary btn-lg profile-add-btn">Add Item</button>
            <button className="btn btn-primary btn-lg profile-add-btn">Add Pic</button>
          </div>
          <div className="profile-item-pic col-sm-4"></div>
          <div className="profile-info col-sm-4">
            <h1>User Profile</h1>

          </div>
        </div>
      </div>
    )
  }
})

module.exports = ProfileView

// <form>
//   <h2>Profile</h2>
//   <h4 className="user-label"><label>Username</label></h4>
//   <input className="profile-inputs profile-email-holder" type="text" name="username" placeholder="UserName"/>
//   <h4 className="user-label"><label>Name</label></h4>
//   <input className="profile-inputs" type="text" name="firstName" placeholder="First Name"/>
//   <input className="profile-inputs" type="text" name="lastName" placeholder="Last Name"/>
//   <h4 className="user-label"><label>Address</label></h4>
//   <input className="profile-inputs" type="text" name="street" placeholder="Address"/>
//   <input className="profile-inputs" type="text" name="city" placeholder="City"/>
//   <input className="profile-inputs" type="text" name="state" placeholder="State"/>
//   <input className="profile-inputs" type="text" name="zipcode" placeholder="Zipcode"/>
// </form>
