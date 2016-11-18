const React = require('react')
// const STORE = require('./store.js')
const ACTIONS = require('./actions.js')

const OopsView = require('./oops-view.js')
const AuthView = require('./auth-view.js')
const MultiView = require('./multi-view.js')
const SingleView = require('./single-view.js')
const ProfileView = require('./profile-view.js')

const AppViewController = React.createClass({
  render: function(){
      switch(this.props.routedFrom) {
        case "AuthView":
          return <AuthView/>
          break;

        case "MultiView":
          return <MultiView/>
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
      }
  }
})

module.exports = AppViewController
// singleData={this.state.singleListing} pidVal={this.props.pidInRoute}
//MultiView payloadData={this.state.currentInventory
//<SingleView singleData={this.state.singleListing} pidVal={this.props.pidInRoute}
