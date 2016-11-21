const React = require('react')
const ReactDOM = require('react-dom')

const {ItemsModel, ItemsModelCollection, CategoryCollection} = require("./models.js")
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')

// const ACTIONS = require('./actions.js')
// const STORE = require('./store.js')
// console.log(data.attribute.itemimages[1])
const MultiView = React.createClass({

  componentWillMount: function(){
    console.log('this props', this.props.catName)
    if(!this.props.catName) {
      ACTIONS.fetchItemsModelCollection()
    } else{
      ACTIONS.fetchCategoryCollection(this.props.catName)
    }

  },

  componentWillReceiveProps: function(newProps){
    console.log('this props', newProps.catName)
    if(newProps.catName === this.props.catName){
      return;
    }
    if(!newProps.catName) {
      ACTIONS.fetchItemsModelCollection()
    } else{
      ACTIONS.fetchCategoryCollection(newProps.catName)
    }
  },

  _routeToCategory: function(evt){
    // STORE._data.currentInventory.filter('collection')
  ACTIONS.routeTo(`category/${evt.target.dataset.cathash}`)
  },


  _itemSelector: function() {


  },

  render: function(){


    // console.log('why wont this log', STORE._data.currentInventory)
    var itemListings = STORE._data.currentInventory.map(function(data, i){
      // console.log('data', data)
      if(i <= 11){
        return(

              <div className="col-xs-4  col-md-4" key={data.attributes.itemId}>
                  <div className="thumbnail thumbnail-container">
                    <a href={"./#singleview/"+ data.attributes.itemId} className="anchor-to-single">
                      <img className="multi-img" src={"images/" + data.attributes.images[0].imageFileName} alt="" data-id={data.attributes.itemId} />
                    </a>
                    <h4 className="multi-item-info multi-item-desc">{data.attributes.itemDescription}</h4>
                    <p className="multi-item-info">Item: {data.attributes.itemName}</p>
                    <p className="multi-item-info">Price: {data.attributes.askingPrice}</p>
                  </div>
              </div>
        )
      }
    })

    return (
      <div className="multi-container">
        <div className="sign-in-btn">
          <a href="#authview"><button type="button" className="btn btn-default btn-md">Sign-in / Sign-up</button></a>
          <a href="#signout"><button type="button" className="btn btn-default btn-md sign-out">Sign Out</button></a>
        </div>
        <div className="multi-header">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <div className="garage-pic"></div>
        <div className="multi-description">
          <h4>Presta Trading Post is the ultimate meet-up spot for the people who have and the people who don't. Have you ever wanted to try out a stand-up paddle board but didn't want to have to fork out the big money just to try it out for a weekend? How about, have you ever needed a special tool to fix something in an afternoon but didn't want to have to buy so that it can sit in your garage 99% of the time? Or maybe you want to have a jam session tonight but you don't have an amplifier.  Well here at Presta Trading Post we have people who have these kinds of things and more that are just sitting in their garages for 99% of the year and for a nominal price, which in most cases can be totally negotiable, you can use these items and not have to buy them.  All you have to do is register with Presta Trading Post today, and then you can begin to see the benefits. Or maybe you even have items sitting around in your garage that someone else might need to use.  Everybody wins. So, register today. </h4>
        </div>
        <div className="btn-group btn-group-lg multi-button-container" role="group" aria-label="...">
          <button type="button" className="btn btn-default" data-cathash="general" onClick={this._routeToCategory}>General</button>
          <button type="button" className="btn btn-default" data-cathash="sporting-goods" onClick={this._routeToCategory}>Sporting Goods</button>
          <button type="button" className="btn btn-default" data-cathash="tools" onClick={this._routeToCategory}>Tools</button>
          <button type="button" className="btn btn-default" data-cathash="electronics" onClick={this._routeToCategory}>Electronics</button>
          <button type="button" className="btn btn-default" data-cathash="outdoor" onClick={this._routeToCategory}>Outdoors</button>
        </div>



        <div className="row row-eq-height is-flex multi-pic-body">
          {itemListings}
        </div>
        <div className="multi-next-page">
          <h3>| 1 | 2 |</h3>
        </div>
        <div>
          <a href="https://www.facebook.com/"><i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://twitter.com/"><i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://www.instagram.com/?hl=en"><i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i></a>
          <h1 className="copyright multi-icons">&#xa9;  2016 Team SilverBack</h1>
        </div>
      </div>
    )

  }
})

module.exports = MultiView
//{itemListings}  this.props.payloadData  /" + data.attributes.images  <i class="fa fa-copyright" aria-hidden="true"></i>
