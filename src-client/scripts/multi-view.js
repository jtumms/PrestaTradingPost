const React = require('react')
const ReactDOM = require('react-dom')

const {ItemsModel, ItemsModelCollection} = require("./models.js")

// const ACTIONS = require('./actions.js')
// const STORE = require('./store.js')

const MultiView = React.createClass({

  render: function(){

  // }
// })

    // var itemListings = this.props.payloadData.map(function(data, i){
    //   return(
    //
    //         <div className="col-xs-4  col-md-4" key={data.cid}>
    //             <div className="thumbnail thumbnail-container">
    //               <a href={"./#singleview/"+data.id} className="anchor-to-single">
    //                 <img src={"images/" + data.attributes.image} alt="" data-id={data.cid} />
    //               </a>
    //               <h4>{data.attributes.bootName}</h4>
    //               <p>Price: {data.attributes.price}</p>
    //               <p>Quantity: {data.attributes.quantity}</p>
    //             </div>
    //         </div>
    //
    //   )
    // })

    return (
      <div className="multi-container">
        <div className="sign-in-btn">
          <a href="#authview"><button type="button" className="btn btn-default btn-lg">Sign-in / Sign-up</button></a>
        </div>
        <div className="multi-header">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>
        <div className="multi-description">
          <h4>Presta Trading Post is the ultimate meet-up spot for people who have and people who don't. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</h4>
        </div>
        <div className="btn-group btn-group-lg multi-button-container" role="group" aria-label="...">
          <button type="button" className="btn btn-default">General</button>
          <button type="button" className="btn btn-default">Sporting Goods</button>
          <button type="button" className="btn btn-default">Tools</button>
          <button type="button" className="btn btn-default">Electronics</button>
          <button type="button" className="btn btn-default">Profile</button>
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
