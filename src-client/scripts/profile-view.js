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
      </div>
    )
  }
})

module.exports = ProfileView
