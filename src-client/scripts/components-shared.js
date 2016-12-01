const React = require('react')
const ACTIONS = require('./actions.js')
const AddItemModel = require('./add-item-model.js')



const NavToHome = React.createClass({

  render: function(){

    return (
      <div className="profile-home-icon-container">
        <a onClick={ function(){ ACTIONS.routeTo('')} } href=""><i className="fa fa-home fa-4x profile-home-icon" aria-hidden="true"></i></a>
      </div>
    )
  }

})

module.exports = {
  NavToHome
}

//              <input className="profile-item-inputs" type="text" ref="image" key="image" placeholder="Image Address"/>
//            <button className="btn btn-primary btn-lg profile-add-pic-btn">Add Pic</button>
