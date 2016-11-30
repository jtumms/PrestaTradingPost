const React = require('react')
const ReactDOM = require('react-dom')
const SingleView = require('./single-view.js')
const {ItemsModel, ItemsModelCollection, CategoryCollection} = require("./models.js")
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')

// const Search = React.createClass({
//     getInitialState: function() {
//         return { showResults: false };
//     },
//     onClick: function() {
//         this.setState({ showResults: true });
//     },
//     if ()
//
//     render: function() {
//         return (
//             <div>
//                 <input type="submit" value="Search" onClick={this.onClick} />
//                 { this.state.showResults ? <Results /> : null }
//             </div>
//         );
//     }
// });
//
// var Results = React.createClass({
//     render: function() {
//         return (
//             <div id="results" className="search-results">
//                 Some Results
//             </div>
//         );
//     }

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

  _handleIfVerified: function(){
    ACTIONS.checkIfVerified()
  },

  _handleLogOut: function(){
    ACTIONS.logOutUser()
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


    })
    STORE.setStore('singleListing', singleItem[0])
    ACTIONS.routeTo(`singleview/${evt.target.dataset.id}`)
    console.log('route to',singleItem[0])


  },

  render: function(){
    let self = this
    console.log("what is going on?")
    // console.log(data)
    // console.log('why wont this log', STORE._data.currentInventory)
    var itemListings = STORE._data.currentInventory.map(function(data, i){
      // console.log(data.attributes.images[0].imageFileName)



        return(

              <div className="col-xs-4  col-md-4" key={data.attributes.itemId}>
                  <div className="thumbnail multi-thumbnail-container">
                    <a onClick={self._routeToItem} className="multi-anchor-to-single" data-id={data.attributes.id}>
                      <img className="multi-img" src={"images/" + data.attributes.images[0].imageFileName} alt="" data-id={data.attributes.itemId} />
                    </a>
                    <h4 className="multi-item-info multi-item-desc">{data.attributes.itemDescription}</h4>
                    <p className="multi-item-info">Item: {data.attributes.itemName}</p>
                    <p className="multi-item-info">Price: {data.attributes.askingPrice}</p>
                  </div>
              </div>
        )
    })



    return (
      <div className="multi-container">
        <div className="garage-pic">
          <div className="sign-in-btn">
            <a href="#aboutus"><button type="button" className="btn btn-default btn-md about-us "><h4>About-Us</h4></button></a>
            <a href="#authview"><button type="button" className="btn btn-default btn-md sign-in"><h4>Sign-in / Sign-up</h4></button></a>
            <a href="#profileview"><button type="button" className="btn btn-default btn-md add-item "><h4>Add Item</h4></button></a>
            <button type="button" className="btn btn-default btn-md sign-out" onClick={this._handleLogOut}><h4>Sign Out</h4></button>
          </div>
          <div className="multi-header text-center">
            <h1>Presta Trading Post</h1>
          </div>
        </div>
        <div className="multi-description text-center">
<<<<<<< HEAD
          <h1>The <strong><em>ultimate</em></strong> meet-up spot for those who have and those who don't.</h1>
        </div>
        <div className="btn-group btn-group-lg multi-button-container" role="group" aria-label="...">
          <button type="button" className="btn btn-default" data-cathash="all-items" onClick={ACTIONS.routeHome}><h2>General</h2></button>
          <button type="button" className="btn btn-default" data-cathash="sporting-goods" onClick={this._routeToCategory}><h2>Sporting Goods</h2></button>
          <button type="button" className="btn btn-default" data-cathash="tools" onClick={this._routeToCategory}><h2>Tools</h2></button>
          <button type="button" className="btn btn-default" data-cathash="electronics" onClick={this._routeToCategory}><h2>Electronics</h2></button>
          <button type="button" className="btn btn-default" data-cathash="outdoor" onClick={this._routeToCategory}><h2>Outdoors</h2></button>
        </div>
        <div className="row row-eq-height is-flex multi-pic-body">
           {itemListings}
         </div>

        <div>
          <a href="https://www.facebook.com/"><i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://twitter.com/"><i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://www.instagram.com/?hl=en"><i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i></a>
          <h1 className="copyright multi-icons">&#xa9; <strong><em>2016 Team SilverBack</em></strong></h1>
        </div>
      </div>
    )

  }
})

module.exports = MultiView
//            <a href="#profileview"><button type="button" className="btn btn-default btn-md about-us "><h4>Add Item</h4></button></a>
//            <h2>LOGO</h2>
=======
                 <h1>The <strong><em>ultimate</em></strong> meet-up spot for those who have and those who don't.</h1>
               </div>
               <div className="btn-group btn-group-lg multi-button-container" role="group" aria-label="...">
                 <button type="button" className="btn btn-default" data-cathash="all-items" onClick={ACTIONS.routeHome}><h2>General</h2></button>
                 <button type="button" className="btn btn-default" data-cathash="sporting-goods" onClick={this._routeToCategory}><h2>Sporting Goods</h2></button>
                 <button type="button" className="btn btn-default" data-cathash="tools" onClick={this._routeToCategory}><h2>Tools</h2></button>
                 <button type="button" className="btn btn-default" data-cathash="electronics" onClick={this._routeToCategory}><h2>Electronics</h2></button>
                 <button type="button" className="btn btn-default" data-cathash="outdoor" onClick={this._routeToCategory}><h2>Outdoors</h2></button>
               </div>
               <div className="row row-eq-height is-flex multi-pic-body">
                  {itemListings}
                </div>

               <div>
                 <a href="https://www.facebook.com/"><i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i></a>
                 <a href="https://twitter.com/"><i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i></a>
                 <a href="https://www.instagram.com/?hl=en"><i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i></a>
                 <h1 className="copyright multi-icons">&#xa9; <strong><em>2016 Team SilverBack</em></strong></h1>

               </div>
             </div>
           )

         }
        })

        module.exports = MultiView
>>>>>>> 9880246f92963a490a89ac58634e39ac054be4e7
