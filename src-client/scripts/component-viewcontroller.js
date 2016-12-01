const React = require('react')

const ACTIONS = require('./actions.js')
const OopsView = require('./oops-view.js')
const AuthView = require('./auth-view.js')
const MultiView = require('./multi-view.js')
const SingleView = require('./single-view.js')
const ConfirmRentalView = require ('./confirm-rental.js')
const ProfileView = require('./profile-view.js')
const SignOutView = require('./signout-view.js')
const STORE = require('./store.js')
const {ItemsModel} = require('./models.js')
const AboutUsView = require('./about-us.js')

const AppViewController = React.createClass({
  getInitialState: function(){
      // console.log( "the retrieved data:" ,updateState.currentInventory)
      STORE.setStore('singleListing', new ItemsModel())
      STORE.setStore('currentUser', new ItemsModel())


      return STORE.getStoreData()
   },

  componentWillMount: function(){
    let component = this
    // // console.log( "the retrieved data:" ,updateState.currentInventory)
    ACTIONS.getCurrentUserInfo(this.props.routedFrom)
    STORE.onChange(function(){
        let updatedState = STORE.getStoreData()
        // console.log( "the retrieved data:" ,updateState.currentInventory)
        component.setState(updatedState)
    })
  },



  render: function(){

     console.log(this.state)

      switch(this.props.routedFrom) {
        case "AuthView":
          window.scrollTo(0, 0);

          return <AuthView/>
          break;

        case "MultiView":
          return <MultiView catName={this.props.catName} currentUser={this.state.currentUser}/>
          break;

        case "SingleView":
          window.scrollTo(0, 0);

          return <SingleView itemId={this.props.itemId} singleItem={this.state.singleListing} />
          break;

        case "ConfirmRentalView":
          if (typeof this.state.confirmedListingRequest.get === 'undefined'){
            ACTIONS.routeTo("")
            return
          }

          return <ConfirmRentalView confirmedItem={this.state.confirmedListingRequest}/>
          break;

        case "ProfileView":
          return <ProfileView navHistory={this.state.userNavHistory} currentUser={this.state.currentUser}/>
          break;

        case "OopsView":
          return <OopsView/>
          break;

        case "SignOutView":
          return <SignOutView/>
          break;

        case "AboutUsView":
          return <AboutUsView/>
          break;
      }
  }
})

module.exports = AppViewController
