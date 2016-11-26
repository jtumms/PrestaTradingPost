const React = require('react')
const ReactDOM = require('react-dom')
const GetUserModel = require('./get-user-model.js')
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')
const AddItemModel = require('./add-item-model.js')


const ProfileView = React.createClass({

  componentDidlMount: function(obj){
    let getUserModelInstance = new GetUserModel()
    // console.log("profile auth", getUserModelInstance)
    console.log('why didnt that work??')
    console.log('?????///!!!!!!', STORE._data.userListing.attributes.userDetail)
    // let currentUserId = STORE.getStoreData()
    ACTIONS.getCurrentUserInfo()
    // console.log('actions current info', ACTIONS.getCurrentUserInfo())
  },

    // getUserDetails: function(userInfoObj) {
    // let getUserModelInstance = new GetUserModel()
    // // console.log(getUserModelInstance)
    // // ACTIONS.fetchgetUserInfo()
    // }
  // },
  // _addSubmisions: function(){
  //
  // }

  _submitNewItemInfo: function(evt) {
    let addItemModelInstance = new AddItemModel
    console.log(evt)
    console.log(evt.target)
    let newItemData = {
      itemName: evt.target.item.value,
      itemDescription: evt.target.description.value,
      askingPrice: evt.target.price.value,

    }
    console.log(newItemData)
  },


  render: function(){
    let myUsr = this.props.currentUser.attributes
    console.log('what up')
    // console.log('?????/?????', myUsr)

    if (!this.props.currentUser.get('username')) {
      console.log("????")
      return <p> loading... </p>
    }
    // console.log("!!!!!!!!!", theNewNew.userListing.attributes.username)
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
            <form>
              <h2>Add an item to rent</h2>
              <input className="profile-item-inputs" type="text" name="item" key="item" placeholder="Item to rent"/>
              <input className="profile-item-inputs" type="text" name="description" key="description" placeholder="Item description"/>
              <input className="profile-item-inputs" type="text" name="price" key="price" placeholder="Item rent price"/>

              <div className="input-group profile-category-input">
                <input type="text" className="form-control" placeholder="Category" aria-label="..."/>
                <div className="input-group-btn dropdown">
                  <button type="button" className="btn btn-default dropdown-toggle" data-toggle="dropdown" ><span className="caret"></span></button>
                  <ul className="dropdown-menu dropdown-menu-right">
                    <li>Sporting Goods</li>
                    <li>Tools</li>
                    <li>Electronics</li>
                    <li>Outdoors</li>
                  </ul>
                </div>
              </div>
            </form>

            <button className="btn btn-primary btn-lg profile-add-btn" onClick={this._submitNewItemInfo}>Add Item</button>
            <button className="btn btn-primary btn-lg profile-add-btn">Add Pic</button>
          </div>
          <div className="profile-item-pic col-sm-4"></div>

          <div className="profile-info col-sm-4">
            <h1><u>User Profile</u></h1>
            <h3>{myUsr.userDetail.firstName} {myUsr.userDetail.lastName}</h3>
            <h3>{myUsr.userDetail.street}</h3>
            <h3>{myUsr.userDetail.city}, {myUsr.userDetail.state}  {myUsr.userDetail.zipcode}</h3>
            <h3>{myUsr.username}</h3>
          </div>
        </div>
      </div>
    )
  }
})

module.exports = ProfileView


//aria-haspopup="true" aria-expanded="false"
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
