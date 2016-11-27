const React = require('react')
const ReactDOM = require('react-dom')
// const JQuery = require('jquery')
const SingleView = require('./single-view')
const {ItemsModel, ItemsModelCollection, CategoryCollection} = require("./models.js")
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')
// const matchHeight = require('jquery-match-height')


const MultiView = React.createClass({

  componentWillMount: function(){
    // console.log('this props', this.props.catName)
    if(!this.props.catName) {
      ACTIONS.fetchItemsModelCollection()
    } else{
      ACTIONS.fetchCategoryCollection(this.props.catName)
    }

  },

  componentWillReceiveProps: function(newProps){
    // console.log('this props', newProps.catName)
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

  _routeToItem: function(evt) {
    // ACTIONS.routeTo(`singleview/${evt.target.dataset.id}`)
    let singleItem = STORE._data.currentInventory.filter(function(obj, i){
      console.log(typeof parseInt(evt.target.dataset.id))
      console.log('???????', obj.attributes.itemId)
      if(parseInt(evt.target.dataset.id) === obj.attributes.itemId){
        return true
      }
      // ACTIONS.routeTo(`singleview/${evt.target.dataset.id}`)
      // ACTIONS.fetchSingleItemModel()
    // console.log(evt.target.dataset.id);

    })
    STORE.setStore('singleListing', singleItem[0])
    ACTIONS.routeTo(`singleview/${evt.target.dataset.id}`)
    console.log('route to',singleItem[0])


  },

  render: function(){
    let self = this
    console.log("what is going on?")
    // $(function() {
    //   $('multi-thumbnail-container').matchHeight(options);
    // });
    // console.log(data)
    // console.log('why wont this log', STORE._data.currentInventory)
    var itemListings = STORE._data.currentInventory.map(function(data, i){
      // console.log(data.attributes.images[0].imageFileName)

      // if(i <= 11){

        return(

              <div className="col-xs-4  col-md-4" key={data.attributes.itemId}>
                  <div className="thumbnail multi-thumbnail-container">
                    <a onClick={self._routeToItem} className="anchor-to-single" data-id={data.attributes.id}>
                      <img className="multi-img" src={"images/" + data.attributes.images[0].imageFileName} alt="" data-id={data.attributes.itemId} />
                    </a>
                    <h4 className="multi-item-info multi-item-desc">{data.attributes.itemDescription}</h4>
                    <p className="multi-item-info">Item: {data.attributes.itemName}</p>
                    <p className="multi-item-info">Price: {data.attributes.askingPrice}</p>
                  </div>
              </div>
        )
      // }
    })

    return (
      <div className="multi-container">
        <div className="sign-in-btn">
          <a href="#aboutus"><button type="button" className="btn btn-default btn-md ">About-Us</button></a>
          <a href="#authview"><button type="button" className="btn btn-default btn-md sign-in">Sign-in / Sign-up</button></a>
          <a href="#logout"><button type="button" className="btn btn-default btn-md sign-out">Sign Out</button></a>
        </div>
        <div className="multi-header text-center">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <div className="garage-pic"></div>
        <div className="multi-description text-center">
          <h1>The ultimate meet-up spot for those who have, and those who don't.</h1>

        </div>
        <div className="btn-group btn-group-lg multi-button-container" role="group" aria-label="...">
          <button type="button" className="btn btn-default" data-cathash="all-items" onClick={ACTIONS.routeHome}>General</button>
          <button type="button" className="btn btn-default" data-cathash="sporting-goods" onClick={self._routeToCategory}>Sporting Goods</button>
          <button type="button" className="btn btn-default" data-cathash="tools" onClick={self._routeToCategory}>Tools</button>
          <button type="button" className="btn btn-default" data-cathash="electronics" onClick={self._routeToCategory}>Electronics</button>
          <button type="button" className="btn btn-default" data-cathash="outdoor" onClick={self._routeToCategory}>Outdoors</button>
        </div>

        <div className="row row-eq-height is-flex multi-pic-body match-my-cols">
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
