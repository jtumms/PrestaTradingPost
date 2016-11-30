const React = require('react')
const ReactDOM = require('react-dom')
const GetUserModel = require('./get-user-model.js')
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')
const AddItemModel = require('./add-item-model.js')



const ProfileView = React.createClass({
  getInitialState: function(){
    return {
      selectedImage: <img className="item-img" height="200" src="" alt="Image preview ..."/>,
      fileBlob: ""
    }
  },

  componentDidlMount: function(obj){
    let getUserModelInstance = new GetUserModel()
    // console.log("profile auth", getUserModelInstance)
    console.log('why didnt that work??', STORE._data)
    console.log('?????///!!!!!!', STORE._data.userListing.attributes.userDetail)
    // let currentUserId = STORE.getStoreData()
    ACTIONS.getCurrentUserInfo()
    // console.log('actions current info', ACTIONS.getCurrentUserInfo())
  },



  _submitNewItemInfo: function(evt) {
    evt.preventDefault()
    let addItemModelInstance = new AddItemModel
    // console.log("evt", this.refs.item.value)
    // console.log('EVT TARGET', this.refs.item.value)

    let newItemData = {
      username: this.refs.username.value,
      itemName: this.refs.item.value,
      itemDescription: this.refs.description.value,
      askingPrice: this.refs.price.value,
      category: this.refs.category.value,
      condition: "GOOD",
      user: {
        "id": this.props.currentUser.id
      },


    }
    this.refs.username.value = ''
    this.refs.item.value = ''
    this.refs.description.value = ''
    this.refs.price.value = ''
    console.log('new item data', newItemData)
    console.log("id", this.props.currentUser.id)
    ACTIONS.addItemModel(newItemData, this.state.fileBlob)

  },

  _seeChange: function(evt){
    console.log(evt.target.name.value)

  },

  _loadFile: function(evt){
    let self = this
    var reader  = new FileReader();
    console.log('evt target',[evt.target.files[0]])
    let fileOnDom = evt.target.files[0]
    reader.addEventListener("load", function () {
      console.log('???', reader.result)
      self.setState({
        selectedImage: <img className="item-img" height="200" src={reader.result} alt="Image preview ..."/>,
        fileBlob: reader.result

      })
    }, false);

    if (fileOnDom) {
     reader.readAsDataURL(fileOnDom);
    }
  },


  render: function(){
    let myUsr = this.props.currentUser.attributes
    console.log('what up',this.props)
    // console.log('?????/?????', this.props.currentUser.attributes.username)

    if (!this.props.currentUser.get('username')) {
      console.log("????")
      return <h1> loading... You need to sign-in or sign-up</h1>
    }



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
          <div className="profile-add-container col-sm-4">
            <form>
              <h2>Add an item to rent</h2>
              <input className="profile-item-inputs" type="text" ref="username" key="username" placeholder="Username"/>
              <input className="profile-item-inputs" type="text" ref="item" key="item" placeholder="Item to rent"/>
              <input className="profile-item-inputs" type="text" ref="description" key="description" placeholder="Item description"/>
              <input className="profile-item-inputs" type="text" ref="price" key="price" placeholder="Item rent price"/>
              <div className="form-group profile-dropdown-box">
                <select ref="category" className="profile-dropdown-select">
                  <option value="SPORTINGGOODS">Sporting Goods</option>
                  <option value="TOOLS">Tools</option>
                  <option value="ELECTRONICS">Electronics</option>
                  <option value="OUTDOORS">Outdoors</option>
                </select>
              </div>
              <button className="btn btn-primary btn-lg profile-add-btn" onClick={this._submitNewItemInfo}>Add Item</button>
            </form>
          </div>
          <div className="profile-item-pic col-sm-4">
            <input type="file" id="input" onChange={this._loadFile}/>
            {this.state.selectedImage}
          </div>
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

//              <input className="profile-item-inputs" type="text" ref="image" key="image" placeholder="Image Address"/>
//            <button className="btn btn-primary btn-lg profile-add-pic-btn">Add Pic</button>
