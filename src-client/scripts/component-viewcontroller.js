const React = require('react')
// const STORE = require('./store.js')
// const ACTIONS = require('./actions.js')


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
          return <MultiView />
          break;

        case "SingleView":
          return <SingleView singleData={this.state.singleListing} pidVal={this.props.pidInRoute} />
          break;

          case "ProfileView":
            return <ProfileView/>
            break;
      }
  }
})

module.exports = AppViewController

//MultiView payloadData={this.state.currentInventory
