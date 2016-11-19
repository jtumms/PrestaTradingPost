const React = require('react')
const ReactDOM = require('react-dom')

const {ItemsModel, ItemsModelCollection} = require("./models.js")
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')

// const ACTIONS = require('./actions.js')
// const STORE = require('./store.js')
// console.log(data.attribute.itemimages[1])
const MultiView = React.createClass({

  componentWillMount: function(){
    ACTIONS.fetchItemsModelCollection()
    console.log("fetch",ACTIONS.fetchItemsModelCollection())
  },


  _itemSelector: function() {


  },

  render: function(){

  // }
// })
    //
    // var itemListings = this.props.payloadData.map(function(data, i){
    //   console.log('data', data)
    //   return(
    //
    //         <div className="col-xs-4  col-md-4" key={data.cid}>
    //             <div className="thumbnail thumbnail-container">
    //               <a href={"./#singleview/"+data.id} className="anchor-to-single">
    //                 <img src={"images/" + data.attributes.itemImages} alt="" data-id={data.cid} />
    //               </a>
    //               <h4>{data.attributes.itemdescription}</h4>
    //               <p>Price: {data.attributes.askingprice}</p>
    //               <p>Quantity: {data.attributes.itemname}</p>
    //             </div>
    //         </div>
    //
    //   )
    // })

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
          <button type="button" className="btn btn-default">General</button>
          <button type="button" className="btn btn-default">Sporting Goods</button>
          <button type="button" className="btn btn-default">Tools</button>
          <button type="button" className="btn btn-default">Electronics</button>
          <button type="button" className="btn btn-default"><a href="#profileview">Profile</a></button>
        </div>
        <div className="row multi-pic-body">


        </div>
        <i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i>
        <i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i>
        <i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i>
      </div>
    )

  }
})

module.exports = MultiView
//{itemListings}
