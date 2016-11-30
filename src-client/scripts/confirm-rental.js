const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')
const SingleView = require('./single-view.js')
const ACTIONS = require('./actions.js')

const ConfirmRentalView = React.createClass({
  componentWillUnmount: function(){
    ACTIONS.clearConfirmedRequest()
  },
  render: function(){

    return (
      <div className="confirm-rental-container">
          <div className="confirm-home-icon-container">
            <a href=" "><i className="fa fa-home fa-4x confirm-home-icon" aria-hidden="true"></i></a>
          </div>
          <div className="confirm-header-container text-center">
             <h1 className="rental-header">Presta Trading Post</h1>
          </div>
          <div className="rental-body-container text-center">
             <h1>Thank You!</h1>
             <h2>  Your request has been sent to the renter. You will be contacted
                 via email to confirm the terms of your rental request.
                 Thanks for using <strong><em>Presta Trading Post</em></strong>. We look forward to helping you
                 with your next project or your next adventure. Happy Trading!</h2>
              <h2> For item: {this.props.confirmedItem.get('item').itemName}</h2>
              <a href="https://www.facebook.com/"><i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i></a>
              <a href="https://twitter.com/"><i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i></a>
              <a href="https://www.instagram.com/?hl=en"><i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i></a>
              <h1 className="copyright multi-icons">&#xa9;  2016 Team SilverBack</h1>
            </div>
      </div>


    )
  }

})

module.exports = ConfirmRentalView

// </div>
//    <p class="clear">&nbsp;</p>
//    <p class="clear">&nbsp;</p>
//    <p class="clear">&nbsp;</p>
//    <p class="clear">&nbsp;</p>
// <div>
