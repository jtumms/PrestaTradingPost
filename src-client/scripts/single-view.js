const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')
// const AppViewController = require('./component-viewcontroller.js')
const {ItemsModel, ItemsModelCollection} = require("./models.js")
const MultiView = require('./multi-view.js')
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')

// console.log('store data!!!', STORE._data)
// console.log('store data!!!', STORE._data.attributes.itemId)

const SingleView = React.createClass({

  componentWillMount: function(){
    let id = this.props.itemId
    console.log('store data',STORE._data);


  },


  render:function(){
    let self = this



    return(
      <div className="single-item-container text-center">
          <div className="single-home-icon-container">
            <a href=" "><i className="fa fa-home fa-4x single-home-icon" aria-hidden="true"></i></a>
          </div>
          <div className="single-header-container">
                 <h2>LOGO</h2>
                 <h1 className="single-header">Presta Trading Post</h1>
          </div>

        <div className="thumbnail thumbnail-container text-center">
               <img src={this.props.imageFileName}></img>
              <div className="caption">
                 <h4>Description: {this.props.itemDescription}</h4>
                 <h4>Item: {this.props.itemName}</h4>
                 <h4>Price: {this.props.askingPrice}</h4>
              </div>
        </div>
      </div>
    )
  }

})

module.exports = SingleView

// const SingleView = React.createClass({
//    componentDidMount: function(){
//      console.log('fetching....', this.props.pidVal)
//      ACTIONS.itemsModel(this.props.pidVal)
//
//    },
// })
