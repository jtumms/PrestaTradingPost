const React = require('react')
const ReactDOM = require('react-dom')

const {ItemsModel, ItemsModelCollection} = require("./models.js")

// const ACTIONS = require('./actions.js')
// const STORE = require('./store.js')

const MultiView = React.createClass({

  render: function(){
    return (
      <div>
        <h4>SilverBack productions presents:  </h4>
        <h1>Presta Trading Post</h1>
      </div>
    )
  }
})

module.exports = MultiView
