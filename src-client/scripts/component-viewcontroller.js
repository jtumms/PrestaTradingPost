const React = require('react')

const ACTIONS = require('./actions.js')
const OopsView = require('./oops-view.js')
const AuthView = require('./auth-view.js')
const MultiView = require('./multi-view.js')
const SingleView = require('./single-view.js')
const ProfileView = require('./profile-view.js')
const SignOutView = require('./signout-view.js')
const STORE = require('./store.js')
const {ItemsModel} = require('./models.js')

const AppViewController = React.createClass({
  getInitialState: function(){
      let updateState = STORE.getStoreData()
      // console.log( "the retrieved data:" ,updateState.currentInventory)
      STORE.setStore('currentInventory', updateState.currentInventory)
      STORE.setStore('singleListing', new ItemsModel())

      return STORE.getStoreData()
   },

  componentWillMount: function(){
    let component = this
    

    // let updateState = STORE.getStoreData()
    // // console.log( "the retrieved data:" ,updateState.currentInventory)
    // self.setState({currentInventory: updateState.currentInventory})


    STORE.onChange(function(){
        let updatedState = STORE.getStoreData()
        // console.log( "the retrieved data:" ,updateState.currentInventory)
        component.setState(updatedState)


    })
  },

  render: function(){
      switch(this.props.routedFrom) {
        case "AuthView":
          return <AuthView/>
          break;

        case "MultiView":
          return <MultiView catName={this.props.catName}/>
          break;

        case "SingleView":
          return <SingleView />
          break;

        case "ProfileView":
          return <ProfileView/>
          break;

        case "OopsView":
          return <OopsView/>
          break;

        case "SignOutView":
          return <SignOutView/>
          break;
      }
  }
})

module.exports = AppViewController
// singleData={this.state.singleListing} pidVal={this.props.pidInRoute}
//MultiView payloadData={this.state.currentInventory
//<SingleView singleData={this.state.singleListing} pidVal={this.props.pidInRoute}
