const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')
const {ItemsModel, ItemsModelCollection} = require("./models.js")
const MultiView = require('./multi-view.js')
const ConfirmRentalView = require ('./confirm-rental.js')
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')
import MapPage from './map-api.js'


const SingleView = React.createClass({

  componentWillMount: function(){
    ACTIONS.fetchSingleItemModel(this.props.itemId)

  },

  _routeToMessenger: function(evt){
    console.log('MESSENGER!!', this.props.itemId)

    ACTIONS.createRentalTransaction(this.props.itemId)

  },


  render:function(){
  console.log(this.props.singleItem);
    if(!this.props.singleItem.attributes.itemName){
      return(
        <p>loading...</p>
      )
    }
    // console.log('!!!!!', this.props.itemId)

    // let model = this.props.singleItem.attributes.latLng
    // pins: {
    //         latitude: model.get('latitude'),
    //         longitude: model.get('longitude'),
    //      }
    // })

    let gMapConfig = {
        center: {lat: 32.784618, lng: -79.940918},
        zoom: 13,
    }

    if (this.props.singleItem.attributes &&  this.props.singleItem.get('latLng')){
      gMapConfig.center.lat = this.props.singleItem.get('latLng').latitude
      gMapConfig.center.lng = this.props.singleItem.get('latLng').longitude

    }

    console.log('gmap config: ', gMapConfig)

    return(

      <div className="single-item-header text-center">
          <div className="single-home-icon-container">
            <a href=" "><i className="fa fa-home fa-4x single-home-icon" aria-hidden="true"></i></a>
          </div>
          <div className="single-header-container">
             <h1 className="single-header">Presta Trading Post</h1>
          </div>

        <div className="thumbnail single-thumbnail-container">
           <img className="single-image" src={"images/" + this.props.singleItem.get("images")[0].imageFileName}/>

          <div className="col-sm-6 caption">
            <h2><u>Description:</u></h2>
            <h3>{this.props.singleItem.get('itemDescription')}</h3>
            <h2><u>Item:</u></h2>
            <h3>{this.props.singleItem.get('itemName')}</h3>
            <h2><u>Price:</u></h2>
            <h3>{this.props.singleItem.get('askingPrice')}</h3>
            <button  onClick={this._routeToMessenger} type="button" className="btn btn-default btn-large confirm-rental-btn " data-id={this.props.itemId}>Rent Item / Contact Renter<br/>*must be logged in*</button>
          </div>

          <div className="col-sm-6 map-container" >
            <MapPage center={gMapConfig.center} zoom={gMapConfig.zoom}/>
          </div>


          <div className="col-sm-12">
              <a href="https://www.facebook.com/"><i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i></a>
              <a href="https://twitter.com/"><i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i></a>
              <a href="https://www.instagram.com/?hl=en"><i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i></a>
              <h1 className="copyright multi-icons">&#xa9; <strong><em>2016 Team SilverBack</em></strong></h1>
          </div>
        </div>
      </div>
    )
  }
})


module.exports = SingleView
// === >  goes in MapPage==>   pinsData={pins}
