const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')
// const AppViewController = require('./component-viewcontroller.js')
const {ItemsModel, ItemsModelCollection} = require("./models.js")
const MultiView = require('./multi-view.js')
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')

const SingleView = React.createClass({

  componentWillMount: function(){
    let id = this.props.itemId
    console.log('store data',STORE._data);
    console.log('trying to get the pics', this.props);


    ACTIONS.fetchSingleItemModel(this.props.itemId)

  },

  render:function(){

    // let self = this
    if(!this.props.singleItem.attributes.itemName){
          return(
            <p>loading...</p>

          )

    }
    console.log('!!!!!', this.props)


    return(

      <div className="single-item-header text-center">
          <div className="single-home-icon-container">
            <a href=" "><i className="fa fa-home fa-4x single-home-icon" aria-hidden="true"></i></a>
          </div>
          <div className="single-header-container">
                 <h2>LOGO</h2>
                 <h1 className="single-header">Presta Trading Post</h1>
          </div>

        <div className="thumbnail thumbnail-container">
           <img className="single-image" src={this.props.singleItem.get("images")}/>
          <div className="caption">
            <h3> Description:</h3> <h4>{this.props.singleItem.get('itemDescription')}</h4>
            <h3>Item:</h3> <h4>{this.props.singleItem.get('itemName')}</h4>
            <h3>Price:</h3> <h4>{this.props.singleItem.get('askingPrice')}</h4>
             {/* <p>{this.props.singleItem.get('itemName')}</p> */}
          </div>
        </div>
      </div>
    )
  }

})

module.exports = SingleView
// <p><i className="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
//  <span className="sr-only">Loading...</span></p>

// const SingleView = React.createClass({
//    componentDidMount: function(){
//      console.log('fetching....', this.props.pidVal)
//      ACTIONS.itemsModel(this.props.pidVal)
//
//    },
// })
